package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy(id = "inputUsername")
	private WebElement inputUname;
	
	@FindBy(id = "inputPassword")
	private WebElement inputPassword;
	
	@FindBy(id="login-btn")
	private WebElement login;
	
	@FindBy(id = "loginError")
	private WebElement loginErrorMsg;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void login(String uname, String pass) {
		inputUname.clear();
		inputPassword.clear();
		inputUname.sendKeys(uname);
		inputPassword.sendKeys(pass);
		login.submit();	
	}
	
	public String loginError() {
		return loginErrorMsg.getText();
	}
	
}
