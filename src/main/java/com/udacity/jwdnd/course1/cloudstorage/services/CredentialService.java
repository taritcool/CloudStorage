package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

@Service
public class CredentialService {

	private CredentialMapper credentialMapper;
	private EncryptionService encryptionService;
	String key;
	
	public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
		super();
		this.credentialMapper = credentialMapper;
		this.encryptionService = encryptionService;
		SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        key = Base64.getEncoder().encodeToString(salt);
	}
	
	public int createCredential(Credential credential) {
		
		
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), key);
		credential.setKey(key);
		credential.setPassword(encryptedPassword);
        return credentialMapper.insert(credential);
	}
	
	public void updateCredential(Credential credential) {
		Credential oldCredential = credentialMapper.getCredential(credential.getCredentialId());
        String hashedPassword = encryptionService.encryptValue(credential.getPassword(), oldCredential.getKey());
		credential.setKey(oldCredential.getKey());
		credential.setPassword(hashedPassword);
        credentialMapper.updateCredential(credential);
	}
	
	public void deleteCredential(int credentialId) {
		credentialMapper.deleteCredential(credentialId);
	}
	
	public List<Credential> getCredentials(int userId){
		return credentialMapper.getCredentials(userId);
	}
	
	
}
