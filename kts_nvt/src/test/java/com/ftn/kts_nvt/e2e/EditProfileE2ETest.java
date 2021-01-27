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

import com.ftn.kts_nvt.pages.EditProfilePage;
import com.ftn.kts_nvt.pages.HomePageRegisteredUser;
import com.ftn.kts_nvt.pages.LoginPage;


public class EditProfileE2ETest {

	private EditProfilePage editProfilePage;

	private LoginPage loginPage;

	private HomePageRegisteredUser homePage;

	private WebDriver driver;
	
	//u bazi mora biti korisnik haha@maidrop.cc, pass=123456789b, Ana Maric

	@Before
	public void setup() throws InterruptedException {

		ChromeOptions option= new ChromeOptions();
        option.addArguments("ignore-certificate-errors");
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver(option);

		this.driver.manage().window().maximize();
		this.editProfilePage = PageFactory.initElements(driver, EditProfilePage.class);
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
		this.homePage = PageFactory.initElements(driver, HomePageRegisteredUser.class);

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
	public void editProfileTestSuccess() throws InterruptedException {

		assertEquals("https://localhost:4200/home-page", driver.getCurrentUrl());

		homePage.getUserBtn().click();
		homePage.getProfileLink().click();
		justWait();
		assertEquals("https://localhost:4200/user/profile", driver.getCurrentUrl());

		// Edit profile
		editProfilePage.getFirstName().clear();
		editProfilePage.getFirstName().sendKeys("Test");
		editProfilePage.getLastName().clear();
		editProfilePage.getLastName().sendKeys("Test");
		editProfilePage.getEditProfileBtn().click();

		driver.get("https://localhost:4200/user/profile");

		assertEquals(editProfilePage.getFirstName().getAttribute("value"), "Test");
		assertEquals(editProfilePage.getLastName().getAttribute("value"), "Test");

		// rollback
		editProfilePage.getFirstName().clear();
		editProfilePage.getFirstName().sendKeys("Ivana");
		editProfilePage.getLastName().clear();
		editProfilePage.getLastName().sendKeys("Vlaisavljevic");
		editProfilePage.getEditProfileBtn().click();

	}

	@Test
	public void editProfileTestCancel() throws InterruptedException {

		assertEquals("https://localhost:4200/home-page", driver.getCurrentUrl());

		homePage.getUserBtn().click();
		homePage.getProfileLink().click();
		justWait();
		assertEquals("https://localhost:4200/user/profile", driver.getCurrentUrl());

		editProfilePage.getCancelEditProfileBtn().click();
		assertEquals("https://localhost:4200/home-page", driver.getCurrentUrl());
	}

	@Test
	public void editProfileTestGoToChangePasswordPage() throws InterruptedException {

		assertEquals("https://localhost:4200/home-page", driver.getCurrentUrl());

		homePage.getUserBtn().click();
		homePage.getProfileLink().click();
		justWait();
		assertEquals("https://localhost:4200/user/profile", driver.getCurrentUrl());

		editProfilePage.getChangePassLink().click();
		assertEquals("https://localhost:4200/user/change-password", driver.getCurrentUrl());

	}

	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}

}
