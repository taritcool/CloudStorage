package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests3 {

	@LocalServerPort
	private int port;

	private static WebDriver driver;
	public String baseUrl;
	private static LoginPage loginPage;
	private static SignupPage signupPage;
	private static HomePage homePage;
	private static CredentialPage credentialPage;
	
	
	@BeforeAll
	static void beforeAll() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        homePage = new HomePage(driver);
        credentialPage = new CredentialPage(driver);
		
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
	public void addCredentialTest() throws InterruptedException {
		
		String fname = "Tarit";
		String lname = "Rez";
		String uname = "tarit";
		String pass = "123";
		
		driver.get(baseUrl +"signup");
		signupPage.signUp(fname, lname, uname, pass);
		driver.get(baseUrl +"login");
		loginPage.login(uname, pass);
		Assertions.assertEquals("Home",driver.getTitle());
		homePage.gotoCredentialTab();
		String url = "https://onlinesbi.com";
		String username = "TARIT18";
		String password = "123456789";
		credentialPage.addCredential(url,username,password);
		Assertions.assertEquals(url,credentialPage.getUrl());
		Assertions.assertEquals(username, credentialPage.getUsername());
		Assertions.assertNotEquals(password, credentialPage.getPassword());
	}
	
	@Test 
	public void editCredential() throws InterruptedException {
		String url = "https://www.fb.com";
		String username = "TARIT1801";
		String password = "fkljsdklsdkl";
		String oldPassword = credentialPage.editCredential(url, username, password);
		Assertions.assertEquals("123456789",oldPassword);		
		Assertions.assertEquals(url,credentialPage.getUrl());
		Assertions.assertEquals(username, credentialPage.getUsername());
		Assertions.assertNotEquals(password, credentialPage.getPassword());
	}
	
	@Test
	public void deleteCredential() throws InterruptedException {
		
		credentialPage.deleteCredential();
		Assertions.assertEquals("No Credential found",credentialPage.getUrl());
	}
	

	
}
