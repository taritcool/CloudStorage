package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	@FindBy(id = "logout")
	WebElement logoutButton;
	
	@FindBy(id = "nav-files-tab")
	WebElement filesTab;
	
	@FindBy(id = "nav-notes-tab")
	WebElement notesTab;
	
	@FindBy(id = "nav-credentials-tab")
	WebElement credentialsTab;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public void logout() {
		logoutButton.submit();
	}
	
	public void gotoFileTab() {
		filesTab.click();
	}
	
	public void gotoNoteTab() throws InterruptedException {
		
		Thread.sleep(2000);
		notesTab.click();
	}
	
	public void gotoCredentialTab() throws InterruptedException {
		
		Thread.sleep(2000);
		credentialsTab.click();
	}

}
