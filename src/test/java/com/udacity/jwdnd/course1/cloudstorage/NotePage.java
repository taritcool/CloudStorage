package com.udacity.jwdnd.course1.cloudstorage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotePage {	

	@FindBy(id = "addNote")
	WebElement addNoteButton;
	
	@FindBy(id = "note-title")
	WebElement inputTitle;
	
	@FindBy(id = "note-description")
	WebElement inputDescription;
	
	@FindBy(id = "save-note" )
	WebElement submitSave;
	
	@FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr/th")
	List<WebElement> title;
	
	@FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr/td[2]")
	List<WebElement> description;
	
	@FindBy(id = "edit-note")
	WebElement editNoteBtn;
	
	@FindBy(id = "delete-note")
	WebElement deleteNoteBtn;
	
	private WebDriver driver;
	
	public NotePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void newNote(String title, String description) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(addNoteButton)).click();   
		
		wait.until(ExpectedConditions.elementToBeClickable(inputTitle)).sendKeys(title);
		wait.until(ExpectedConditions.elementToBeClickable(inputDescription)).sendKeys(description);
		
		
		
		wait.until(ExpectedConditions.elementToBeClickable(submitSave)).click();		
	}
	
	public String getTitle() {		
		return title.get(0).getText();
	}
	
	public String getDescription() {		
		return description.get(0).getText();
	}
	
	public void editNote(String title, String description) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(editNoteBtn)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(inputTitle)).sendKeys(title);
		wait.until(ExpectedConditions.elementToBeClickable(inputDescription)).sendKeys(description);
		
		wait.until(ExpectedConditions.elementToBeClickable(submitSave)).click();
		
	}
	
	public void deleteNote() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(deleteNoteBtn)).submit();
	}
	
}
