package com.udacity.jwdnd.course1.cloudstorage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialPage {
	
	@FindBy(id = "addCredential")
	WebElement addCredentialBtn;
	
	@FindBy(name = "url")
	WebElement inputUrl;
	
	@FindBy(name = "username")
	WebElement inputUsername;
	
	@FindBy(name = "password")
	WebElement inputPassword;
	
	@FindBy(id = "save-credential")
	WebElement saveCredentialBtn;
	
	@FindBy(id = "delete-credential")
	WebElement deleteCredentialBtn;
	
	@FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr/th")
	List<WebElement> url;
	
	@FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr/td[2]")
	List<WebElement> username;
	
	@FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr/td[3]")
	List<WebElement> password;
	
	@FindBy(id = "edit-credential")
	WebElement editCredentialBtn;
	
	private WebDriver driver;
	
	public CredentialPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void addCredential(String url,String username, String password) {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(addCredentialBtn)).click();   
		
		wait.until(ExpectedConditions.elementToBeClickable(inputUrl)).sendKeys(url);
		wait.until(ExpectedConditions.elementToBeClickable(inputUsername)).sendKeys(username);
		wait.until(ExpectedConditions.elementToBeClickable(inputPassword)).sendKeys(password);
		
		
		
		wait.until(ExpectedConditions.elementToBeClickable(saveCredentialBtn)).click();
		
	}
	
	public String editCredential(String url, String username, String password) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(editCredentialBtn)).click();
		wait.until(ExpectedConditions.elementToBeClickable(saveCredentialBtn));
		String oldPassword = currPassword();
		inputUrl.clear();
		inputUsername.clear();
		inputPassword.clear();
		wait.until(ExpectedConditions.elementToBeClickable(inputUrl)).sendKeys(url);
		wait.until(ExpectedConditions.elementToBeClickable(inputUsername)).sendKeys(username);
		wait.until(ExpectedConditions.elementToBeClickable(inputPassword)).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(saveCredentialBtn)).click();
		return oldPassword;
	}
	
	public void deleteCredential() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(deleteCredentialBtn)).submit();
	}
	public String getUrl() {
		return url.get(0).getText();
	}
	
	public String getUsername() {
		return username.get(0).getText();
	}
	
	public String getPassword() {
		return password.get(0).getText();
	}
	
	public String currPassword() throws InterruptedException {
		Thread.sleep(2000);
		return inputPassword.getAttribute("value");
	}

}
