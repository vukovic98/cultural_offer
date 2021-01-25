package com.ftn.kts_nvt.e2e;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.ftn.kts_nvt.pages.HomePageAdminPage;
import com.ftn.kts_nvt.pages.HomePageRegisteredUser;
import com.ftn.kts_nvt.pages.LoginPage;
import com.ftn.kts_nvt.pages.SubscribedItemsPage;

public class SubscribedItemsE2ETest {
	
	public static final String HOME_PAGE = "https://localhost:4200/";

	private WebDriver driver;
	
	private SubscribedItemsPage itemsPage;
	
	private HomePageRegisteredUser homePage;
	
	private LoginPage loginPage;
	
	@Before
	public void setup() throws InterruptedException {

		ChromeOptions option= new ChromeOptions();
        option.addArguments("ignore-certificate-errors");
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver(option);

		this.driver.manage().window().maximize();
		this.itemsPage = PageFactory.initElements(this.driver, SubscribedItemsPage.class);
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
		this.homePage = PageFactory.initElements(driver, HomePageRegisteredUser.class);

		driver.get(HOME_PAGE + "login");

		this.loginPage.getEmail().sendKeys("vladimirvukovic98@maildrop.cc");
		this.loginPage.getPassword().sendKeys("vukovic");

		this.loginPage.getLoginBtn().click();

		justWait();

	}
	
	@Test
	public void testShowSubscribedItemsPage() throws InterruptedException {
		driver.get(HOME_PAGE + "subscribed-items");
		
		justWait();
		
		this.itemsPage.ensureIsPageDisplayed();
		
		assertTrue(this.itemsPage.getTableBody().isDisplayed());
		assertEquals(HOME_PAGE + "subscribed-items", this.driver.getCurrentUrl());
	}
	
	@Test
	public void testUnsubscribeFromOffer() throws InterruptedException {
		driver.get(HOME_PAGE + "subscribed-items");
		
		justWait();
		
		this.itemsPage.ensureIsPageDisplayed();
		
		assertTrue(this.itemsPage.getTableBody().isDisplayed());
		
		int numberOfOffers = this.itemsPage.getTableRows().size();
		
		assertEquals("Sonsing", this.itemsPage.getOfferTitle().getText());
		
		assertTrue(this.itemsPage.getUnsubscribeButton().isDisplayed());
		
		this.itemsPage.getUnsubscribeButton().click();
		
		justWait();
		
		assertTrue(this.itemsPage.getSwalSuccess().isDisplayed());
		assertTrue(this.itemsPage.getSwalBtn().isDisplayed());
		
		this.itemsPage.getSwalBtn().click();
		
		justWait();
		
		int reducedNumberOfOffers = this.itemsPage.getTableRows().size();
		
		assertEquals(numberOfOffers - 1, reducedNumberOfOffers);
		
		//REVERT TO PREVIOUS STATE
		
		driver.get(HOME_PAGE);
		
		justWait();
		
		this.homePage.ensureIsPageDisplayed();
		
		assertTrue(this.homePage.getSonsingSubscribeToggle().isDisplayed());
		
		this.homePage.getSonsingSubscribeToggle().click();
		
		justWait();
		
		assertTrue(this.homePage.getSwalSuccess().isDisplayed());
		assertTrue(this.homePage.getSwalBtn().isDisplayed());
		
		this.homePage.getSwalBtn().click();
		
	}

	@After
	public void tearDown() {
		this.driver.quit();
	}
	
	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}
}
