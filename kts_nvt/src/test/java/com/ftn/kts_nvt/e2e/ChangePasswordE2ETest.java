package com.ftn.kts_nvt.e2e;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.ftn.kts_nvt.pages.ChangePasswordPage;
import com.ftn.kts_nvt.pages.HomePageRegisteredUser;
import com.ftn.kts_nvt.pages.HomePageUnregisteredUser;
import com.ftn.kts_nvt.pages.LoginPage;

public class ChangePasswordE2ETest {

	private WebDriver driver;

	private ChangePasswordPage changePasswordPage;

	private LoginPage loginPage;

	private HomePageRegisteredUser homePage;

	private HomePageUnregisteredUser homePageUnregistered;

	//u bazi mora biti korisnik haha@maidrop.cc, pass=123456789b
	
	@Before
	public void setup() throws InterruptedException {

		ChromeOptions option= new ChromeOptions();
        option.addArguments("ignore-certificate-errors");
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver(option);

		this.driver.manage().window().maximize();
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
		this.homePage = PageFactory.initElements(driver, HomePageRegisteredUser.class);
		this.changePasswordPage = PageFactory.initElements(driver, ChangePasswordPage.class);
		this.homePageUnregistered = PageFactory.initElements(driver, HomePageUnregisteredUser.class);

		driver.get("https://localhost:4200/auth/login");

		justWait();

		assertFalse(loginPage.getLoginBtn().isEnabled());

		loginPage.getEmail().sendKeys("haha@maildrop.cc");

		loginPage.getPassword().sendKeys("123456789b");

		loginPage.ensureIsButtonEnabled();

		loginPage.getLoginBtn().click();

		justWait();

		loginPage.ensureIsNotVisibleButton();
	}

	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}

	@Test
	public void changePasswordTestSuccess() throws InterruptedException {
		assertEquals("https://localhost:4200/home-page", driver.getCurrentUrl());

		// change password
		homePage.getUserBtn().click();
		homePage.getChangePasswordLink().click();
		changePasswordPage.ensureIsPageDisplayed();

		assertEquals("https://localhost:4200/user/change-password", driver.getCurrentUrl());

		changePasswordPage.getOldPassword().sendKeys("123456789b");
		changePasswordPage.getNewPassword().sendKeys("novaLozinka1");
		changePasswordPage.getConfirmedPassword().sendKeys("novaLozinka1");
		changePasswordPage.getSaveChangesBtn().click();

		homePage.ensureIsPageDisplayed();

		assertEquals("https://localhost:4200/home-page", driver.getCurrentUrl());

		homePage.getUserBtn().click();
		homePage.getLogoutLink().click();

		homePageUnregistered.ensureIsPageDisplayed();
		homePageUnregistered.getLoginBtn().click();

		loginPage.getEmail().sendKeys("haha@maildrop.cc");
		loginPage.getPassword().sendKeys("novaLozinka1");
		loginPage.getLoginBtn().click();

		homePage.ensureIsPageDisplayed();
		assertEquals("https://localhost:4200/home-page", driver.getCurrentUrl());

		// rollback
		homePage.getUserBtn().click();
		homePage.getChangePasswordLink().click();

		changePasswordPage.ensureIsPageDisplayed();
		assertEquals("https://localhost:4200/user/change-password", driver.getCurrentUrl());

		changePasswordPage.getOldPassword().sendKeys("novaLozinka1");
		changePasswordPage.getNewPassword().sendKeys("123456789b");
		changePasswordPage.getConfirmedPassword().sendKeys("123456789b");
		changePasswordPage.getSaveChangesBtn().click();

		homePage.ensureIsPageDisplayed();
		assertEquals("https://localhost:4200/home-page", driver.getCurrentUrl());

		// check rollback
		homePage.getUserBtn().click();
		homePage.getLogoutLink().click();
		homePage.getLoginBtn().click();
		justWait();

		assertEquals("https://localhost:4200/auth/login", driver.getCurrentUrl());

		loginPage.getEmail().sendKeys("haha@maildrop.cc");
		loginPage.getPassword().sendKeys("123456789b");
		loginPage.getLoginBtn().click();

		homePage.ensureIsPageDisplayed();
		assertEquals("https://localhost:4200/home-page", driver.getCurrentUrl());
	}

	@Test
	public void changePasswordCancelTest() {
		homePage.ensureIsPageDisplayed();
		homePage.getUserBtn().click();
		homePage.getChangePasswordLink().click();
		changePasswordPage.ensureIsPageDisplayed();
		assertEquals("https://localhost:4200/user/change-password", driver.getCurrentUrl());
		
		changePasswordPage.getCancelChangesBtn().click();
		homePage.ensureIsPageDisplayed();
		assertEquals("https://localhost:4200/home-page", driver.getCurrentUrl());

	}

	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}

}
