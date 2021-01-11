package com.ftn.kts_nvt.e2e;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.ftn.kts_nvt.pages.LoginPage;

public class LoginE2ETest {

	private WebDriver driver;

	private LoginPage loginPage;

	@Before
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver();

		this.driver.manage().window().maximize();
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
		// categoryPage = PageFactory.initElements(driver, CategoryPage.class);
	}

	@After
	public void tearDown() {
		this.driver.quit();
	}
	
	@Test
	public void loginTestSuccess() {
		driver.get("http://localhost:4200/login");
	}

}
