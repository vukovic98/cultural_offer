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

import com.ftn.kts_nvt.pages.ApprovingCommentsPage;
import com.ftn.kts_nvt.pages.HomePageAdminPage;
import com.ftn.kts_nvt.pages.LoginPage;

public class ApprovingCommentsE2ETest {
	
	private WebDriver driver;
	
	private LoginPage loginPage;
	
	private HomePageAdminPage homePageAdmin;
	
	private ApprovingCommentsPage approvingCommentsPage;
	
	@Before
	public void setup() throws InterruptedException {

		ChromeOptions option= new ChromeOptions();
        option.addArguments("ignore-certificate-errors");
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver(option);

		this.driver.manage().window().maximize();
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
		this.homePageAdmin = PageFactory.initElements(driver, HomePageAdminPage.class);
		this.approvingCommentsPage = PageFactory.initElements(driver, ApprovingCommentsPage.class);

		driver.get("https://localhost:4200/login");

		justWait();

		assertFalse(loginPage.getLoginBtn().isEnabled());

		loginPage.getEmail().sendKeys("adminko@maildrop.cc");

		loginPage.getPassword().sendKeys("123456");

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
	public void approveComment() throws InterruptedException {
		
		homePageAdmin.ensureIsPageDisplayed();
		homePageAdmin.getApprovingCommentsLink().click();
		approvingCommentsPage.ensureIsPageDisplayed();
		justWait();
		approvingCommentsPage.getExpandComment().click();
		justWait();
		approvingCommentsPage.getApproveBtn().click();
		justWait();
		assertEquals("Comment approved!", approvingCommentsPage.getSwalSuccessContent().getText());
		approvingCommentsPage.ensureIsPageDisplayed();
		
	}
	

	@Test
	public void rejectComment() throws InterruptedException {
		
		homePageAdmin.ensureIsPageDisplayed();
		homePageAdmin.getApprovingCommentsLink().click();
		approvingCommentsPage.ensureIsPageDisplayed();
		justWait();
		approvingCommentsPage.getExpandComment().click();
		justWait();
		approvingCommentsPage.getRejectBtn().click();
		justWait();
		assertEquals("Comment sucessfully denied!", approvingCommentsPage.getSwalSuccessContent().getText());
		approvingCommentsPage.ensureIsPageDisplayed();
		
	}
	
	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}

}
