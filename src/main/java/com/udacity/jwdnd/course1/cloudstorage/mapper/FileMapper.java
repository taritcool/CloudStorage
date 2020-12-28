package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.File;

@Mapper
public interface FileMapper {


	@Select("SELECT * FROM files WHERE userid = #{userId}")
	List<File> getFiles(int userId);
	
	@Insert("INSERT INTO files (fileid,filename,contenttype,filesize,userid,filedata) VALUES (null,#{fileName},#{contentType},#{fileSize},#{userId},#{fileData})")
	@Options(useGeneratedKeys=true,keyProperty= "fileId")
	int insert(File file);
	
	@Select("SELECT * FROM files WHERE userid = #{userId} AND filename = #{fileName}")
	List<File> getFileName(String fileName, int userId);
	
	@Delete("DELETE FROM files WHERE fileid = #{fileId}")
	void deleteFile(int fileId);
	
	@Select("SELECT * FROM files WHERE fileid = #{fileId}")
	File getFileById(int fileId);
}