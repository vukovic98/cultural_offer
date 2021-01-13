package com.ftn.kts_nvt.e2e;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.ftn.kts_nvt.pages.EditProfilePage;
import com.ftn.kts_nvt.pages.LoginPage;
import com.ftn.kts_nvt.pages.SignUpPage;
import com.ftn.kts_nvt.pages.VerifyPage;


public class EditProfileE2ETest {
	
	private EditProfilePage editProfilePage;
	
	private LoginPage loginPage;
	
	private WebDriver driver;


	@Before
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver();

		this.driver.manage().window().maximize();
		this.editProfilePage = PageFactory.initElements(driver, EditProfilePage.class);
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
	}

	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}
	
	@Test
	public void editProfileTestSuccess() throws InterruptedException {
		//Log in
		driver.get("http://localhost:4200/login");

		justWait();
		
		assertFalse(loginPage.getLoginBtn().isEnabled());

        loginPage.getEmail().sendKeys("haha@maildrop.cc");

        loginPage.getPassword().sendKeys("123456");
        
        loginPage.ensureIsButtonEnabled();

        loginPage.getLoginBtn().click();

        justWait();
        
        loginPage.ensureIsNotVisibleButton();

        assertEquals("http://localhost:4200/home-page", driver.getCurrentUrl());
	
        //class="mat-focus-indicator mat-menu-trigger dropdown mat-mini-fab mat-button-base mat-primary ng-star-inserted"
        //ng-reflect-router-link = "/profile"
        
        
	}
	
	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}

}
