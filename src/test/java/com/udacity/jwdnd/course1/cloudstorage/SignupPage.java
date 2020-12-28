package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

	@FindBy(id="inputFirstName")
	private WebElement inputFirstName;
	
	@FindBy(id="inputLastName")
	private WebElement inputLastName;
	
	@FindBy(id="inputUsername")
	private WebElement inputUsername;
	
	@FindBy(id="inputPassword")
	private WebElement inputPassword;
	
	@FindBy(id="signupSuccess")
	private WebElement signupSuccess;
	
	@FindBy(id="signupError")
	private WebElement signupError;
	
	@FindBy(id = "signup")
	private WebElement signup;
	
	@FindBy(id="login")
	private WebElement login;
	
	public SignupPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void signUp(String fname, String lname, String uname, String pass) {
		inputFirstName.clear();
		inputLastName.clear();
		inputUsername.clear();
		inputPassword.clear();
		inputFirstName.sendKeys(fname);
		inputLastName.sendKeys(lname);
		inputUsername.sendKeys(uname);
		inputPassword.sendKeys(pass);
		signup.submit();
	}
	
	public void loginPage() {
		login.submit();
	}
	
	public String getSignupMsg() {
		return signupSuccess.getText();
	}
	
	
	
	
}
