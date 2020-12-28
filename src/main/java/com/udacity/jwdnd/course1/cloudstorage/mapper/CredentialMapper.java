package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;



@Mapper
public interface CredentialMapper {

	@Insert("INSERT INTO credentials (credentialid, url, username, key, password, userid) VALUES (null, #{url}, #{username}, #{key}, #{password}, #{userId})")
	@Options(useGeneratedKeys=true,keyProperty= "credentialId")
	int insert(Credential credentials);
	
	@Select("SELECT * FROM credentials WHERE userid = #{userId}")
	List<Credential> getCredentials(int userId);
	
	@Delete("DELETE FROM credentials WHERE credentialid = #{credentialId}")
	void deleteCredential(int credentialId);
	
	@Update("UPDATE credentials SET url = #{url}, username = #{username}, password = #{password} WHERE credentialid = #{credentialId}")
	void updateCredential(Credential credential);
	
	@Select("SELECT * FROM credentials WHERE credentialid = #{credentialId}")
	Credential getCredential(int credentialId);
}
