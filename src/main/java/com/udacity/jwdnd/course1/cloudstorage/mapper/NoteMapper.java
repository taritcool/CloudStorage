package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Mapper
public interface NoteMapper {
	
	@Select("Select * from Notes where userid = #{userId}")
	List<Note> getNotes(int userId);
	
	@Insert("INSERT INTO notes (noteid,notetitle,notedescription,userid) VALUES (null,#{noteTitle},#{noteDescription},#{userId})")
	@Options(useGeneratedKeys=true,keyProperty= "noteId")
	Integer insert(Note note);
	
	@Delete("DELETE FROM notes WHERE noteid = #{noteId}")
	void deleteNote(int noteId);
	
	@Update("UPDATE notes SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
	void updateNote(Note note);
}
