package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private static WebDriver driver;
	public String baseUrl;
	private static LoginPage loginPage;
	private static SignupPage signupPage;
	private static HomePage homePage;
	
	
	@BeforeAll
	static void beforeAll() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        homePage = new HomePage(driver);
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
	public void unauthorizedAccessLogin() {
		driver.get(baseUrl + "home");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get(baseUrl + "home/note");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get(baseUrl+ "signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}
	
	@Test
	public void signupLoginLogoutTest() {
		
		
		String fname = "Tarit";
		String lname = "Rez";
		String uname = "tarit";
		String pass = "123";
		
		// Signup Test		
		
		driver.get(baseUrl + "signup");
		
		signupPage.signUp(fname, lname, uname, pass);
		
		Assertions.assertEquals("You successfully signed up! Please continue to the login page.",signupPage.getSignupMsg());
		
		
		//Login Test
		
		driver.get(baseUrl + "login");
		
		loginPage.login(uname, pass);
		
		Assertions.assertEquals("Home",driver.getTitle());
		
		//Logout Test
		
		homePage.logout();
		
		Assertions.assertEquals("Login",driver.getTitle());
	}

}
