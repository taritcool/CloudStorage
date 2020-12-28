package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Service
public class NoteService {
	
	NoteMapper noteMapper;

	public NoteService(NoteMapper noteMapper) {
		super();
		this.noteMapper = noteMapper;
	}
	
	public int createNote(Note note) {
		return noteMapper.insert(note);
	}
	
	public void updateNote(Note note) {
		noteMapper.updateNote(note);
	}
	
	public void deleteNote(int noteId) {
		noteMapper.deleteNote(noteId);
	}
	
	public List<Note> getNotes(int userId){
		return noteMapper.getNotes(userId);
	}	

}
