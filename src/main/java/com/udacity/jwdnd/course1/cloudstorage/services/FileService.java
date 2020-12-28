package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;

@Service
public class FileService {

	FileMapper fileMapper;

	public FileService(FileMapper fileMapper) {
		super();
		this.fileMapper = fileMapper;
	}
	
	public int createFile(File file) {
		return fileMapper.insert(file);
	}
	
	public List<File> getFiles(int userId){
		return fileMapper.getFiles(userId);
	}
	
	public boolean isFileNameAvailable(String fileName, int userId) {
		List<File> files = fileMapper.getFileName(fileName,userId);
		
		return  files.size() != 0;
	}
	
	public void deleteFile(int fileId) {
		fileMapper.deleteFile(fileId);
	}
	
	public File getFileById(int fileId) {		
		return fileMapper.getFileById(fileId);
	}
}
