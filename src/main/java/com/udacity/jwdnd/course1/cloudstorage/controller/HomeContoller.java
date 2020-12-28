package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/home")
public class HomeContoller {

	NoteService noteService;
	UserService userService;
	FileService fileService;
	CredentialService credentialService;
	EncryptionService encryptionService;
	User user;
	int userId;
	
	public HomeContoller(NoteService noteService, UserService userService, FileService fileService, CredentialService credentialService, EncryptionService encryptionService) {
		super();
		this.noteService = noteService;
		this.userService = userService;
		this.fileService = fileService;
		this.credentialService = credentialService;
		this.encryptionService = encryptionService;
	}

	@GetMapping()
	public String getHomeView(Authentication auth,@ModelAttribute Note note, Model model) {
		
		user = userService.getUser(auth.getName());
		userId = user.getUserId();		
		model.addAttribute("activeTab", "file");
		setModel(model);
		return "home";
	}
	
	@PostMapping("/note")
	public String saveNote(@ModelAttribute Note note, Model model) {			
		 
		if (note.getNoteId() == 0) {
			note.setUserId(userId);
			int row = noteService.createNote(note);			
			model.addAttribute("updateNote", "Note Successfully Added");
			
		}else {
			
			noteService.updateNote(note);			
			model.addAttribute("updateNote", "Note Successfully Updated");
			
		}
		setModel(model);
		model.addAttribute("activeTab", "note");
		return "home" ;
	}
	
	@PostMapping("/note-delete")
	public String deleteNote(@RequestParam ("noteId") Integer noteId, Model model) {		
		
		noteService.deleteNote(noteId);
		setModel(model);
		model.addAttribute("activeTab", "note");
		model.addAttribute("updateNote", "Note Successfully Deleted");
		return "home" ;
	}
	
	@PostMapping("/file-upload")
	public String fileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {
		
		try {
		if (fileUpload.getOriginalFilename().isEmpty()) {
			model.addAttribute("uploadError", "Please Select a File to Upload.");
			setModel(model);
			model.addAttribute("activeTab", "file");
			return "home";
		}
		
		if (fileUpload.getSize() > 1048576) {
            
            model.addAttribute("uploadError","File size larger than size limit( < 1MB)");
            throw new IOException("Error: File size larger than size limit");
        }
		
		if (!fileService.isFileNameAvailable(fileUpload.getOriginalFilename(), userId)) {
			File file = new File();
			file.setFileData(fileUpload.getBytes());
            file.setContentType(fileUpload.getContentType());
            file.setFileName(fileUpload.getOriginalFilename());
            file.setFileSize(fileUpload.getSize() + "");
            file.setUserId(userId);
            int row = fileService.createFile(file);
            if (row > 0 ) {
            	model.addAttribute("updateStatus", "Sucessfully Uploaded");
            }else {
            	model.addAttribute("updateStatus", "File Didn't Uploaded.Please Try Again");
            }
		}else {
			model.addAttribute("uploadError", "Please Select a unique File Name.");
		}
		
		} catch (IOException e) {
		}
		setModel(model);
		model.addAttribute("activeTab", "file");
		return "home";
	}
	
	@GetMapping("/file-view")
	public void fileView(@RequestParam("fileId") Integer fileId, HttpServletResponse response) throws IOException {
		
		File file = fileService.getFileById(fileId);
		response.setContentType(file.getContentType());
        response.setContentLength(Integer.parseInt(file.getFileSize()));
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getFileName());

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(file.getFileData());

        response.flushBuffer();
	}
	
	@PostMapping("/file-delete")
	public String fileDelete(@RequestParam("fileId") Integer fileId, Model model) {
		
		fileService.deleteFile(fileId);
		
		model.addAttribute("updateStatus", "Sucessfully Deleted");
		model.addAttribute("activeTab", "file");
		setModel(model);
		return "home";
	}
	
	@PostMapping("/credential")
	public String addCredentials(@ModelAttribute Credential credential, Model model) {
		
		if (credential.getCredentialId() == 0) {
			credential.setUserId(userId);
			int row = credentialService.createCredential(credential);
			model.addAttribute("updateCredential", "Credential successfully added");			
		}else {
			credentialService.updateCredential(credential);
			model.addAttribute("updateCredential", "Credential successfully updated");
		}
		setModel(model);
		model.addAttribute("activeTab", "credential");
		model.addAttribute("encryptionService",encryptionService);
		return "home";
	}
	
	@PostMapping("/credential-delete")
	public String deleteCredential(@RequestParam("credentialId") Integer credentialId, Model model) {
		credentialService.deleteCredential(credentialId);
		setModel(model);
		model.addAttribute("activeTab", "credential");
		model.addAttribute("updateCredential","Credential successfully deleted");
		return "home";
	}
	
	void setModel(Model model){
		model.addAttribute("notes", noteService.getNotes(userId));
		model.addAttribute("files", fileService.getFiles(userId));
		List<Credential> credentials = credentialService.getCredentials(userId);		
		for (Credential credential:credentials) {
			credential.setDecryptedPassword(encryptionService.decryptValue(credential.getPassword(),credential.getKey()));
		}
		model.addAttribute("credentials",credentials);
	}
}
