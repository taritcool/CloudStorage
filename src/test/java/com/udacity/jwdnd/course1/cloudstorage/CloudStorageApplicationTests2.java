package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests2 {

	@LocalServerPort
	private int port;

	private static WebDriver driver;
	public String baseUrl;
	private static LoginPage loginPage;
	private static SignupPage signupPage;
	private static HomePage homePage;
	private static NotePage notePage;
	
	
	@BeforeAll
	static void beforeAll() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        homePage = new HomePage(driver);
        notePage = new NotePage(driver);
		
	}
	
	@AfterAll
	static void afterAll() {
		driver.quit();
		driver = null;
	}

	@BeforeEach
	public void beforeEach() {
		baseUrl = "http://localhost:" + port + "/";
	}

	@AfterEach
	public void afterEach() {
		
	}
	
	@Test
	public void addNoteTest() throws InterruptedException {
		
		String fname = "Tarit";
		String lname = "Rez";
		String uname = "tarit";
		String pass = "123";
		
		driver.get(baseUrl +"signup");
		signupPage.signUp(fname, lname, uname, pass);
		driver.get(baseUrl +"login");
		loginPage.login(uname, pass);
		homePage.gotoNoteTab();
		notePage.newNote("To Do", "-Grocery -Cook -Clean");
		Assertions.assertEquals("To Do",notePage.getTitle());
		Assertions.assertEquals("-Grocery -Cook -Clean",notePage.getDescription());
	}
	
	@Test
	public void editNote() throws InterruptedException {
		
		String title = "To Do -edit";
		String description = "-Grocery -Cook -Clean - edit";
		notePage.editNote(title,description);
		Assertions.assertEquals("To Do" + title,notePage.getTitle());
		Assertions.assertEquals("-Grocery -Cook -Clean" + description,notePage.getDescription());
		
	}
	
	@Test
	public void deleteTest() throws InterruptedException{
		
		notePage.deleteNote();
		Assertions.assertEquals("No Note found",notePage.getTitle());
	}
	
	

	
}
