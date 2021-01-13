package com.ftn.kts_nvt.e2e;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.ftn.kts_nvt.pages.HomePageSubscribedUser;
import com.ftn.kts_nvt.pages.LoginPage;

public class HomePageSubscribedUserE2ETest {

	public static final String HOME_PAGE = "http://localhost:4200/";

	private WebDriver driver;

	private HomePageSubscribedUser homePage;

	private LoginPage loginPage;

	@Before
	public void setup() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver();

		this.driver.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.driver, HomePageSubscribedUser.class);
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);

		driver.get(HOME_PAGE + "login");

		this.loginPage.getEmail().sendKeys("vladimirvukovic98@maildrop.cc");
		this.loginPage.getPassword().sendKeys("vukovic");

		this.loginPage.getLoginBtn().click();

		justWait();

	}

	@After
	public void tearDown() {
		this.driver.quit();
	}

	@Test
	public void testShowHomePage() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertEquals(HOME_PAGE + "home-page", this.driver.getCurrentUrl());

		assertTrue(this.homePage.getUserBtn().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());

		assertEquals(8, this.homePage.getOffers().size());

	}
	
	@Test
	public void testUserMenu() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertEquals(HOME_PAGE + "home-page", this.driver.getCurrentUrl());
		
		assertTrue(this.homePage.getUserBtn().isDisplayed());
		
		this.homePage.getUserBtn().click();
		
		this.homePage.ensureUserMenuIsDisplayed();
		
		assertTrue(this.homePage.getUserMenu().isDisplayed());
	}
	
	@Test
	public void testSubscribedItemsLink() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();
		
		assertTrue(this.homePage.getSubscribedItemsLink().isDisplayed());
		
		this.homePage.getSubscribedItemsLink().click();
		
		assertEquals("http://localhost:4200/subscribed-items", this.driver.getCurrentUrl());
	}
	
	@Test
	public void testProfileLink() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();
		
		assertTrue(this.homePage.getUserBtn().isDisplayed());
		
		this.homePage.getUserBtn().click();
		
		assertTrue(this.homePage.getUserMenu().isDisplayed());
		
		assertTrue(this.homePage.getProfileLink().isDisplayed());
		
		this.homePage.getProfileLink().click();
		
		justWait();
		
		assertEquals(HOME_PAGE + "profile", this.driver.getCurrentUrl());
	}
	
	@Test
	public void testChangePasswordLink() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();
		
		assertTrue(this.homePage.getUserBtn().isDisplayed());
		
		this.homePage.getUserBtn().click();
		
		assertTrue(this.homePage.getUserMenu().isDisplayed());
		
		assertTrue(this.homePage.getChangePasswordLink().isDisplayed());
		
		this.homePage.getChangePasswordLink().click();
		
		justWait();
		
		assertEquals(HOME_PAGE + "change-password", this.driver.getCurrentUrl());
	}
	
	@Test
	public void testLogoutLink() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();
		
		assertTrue(this.homePage.getUserBtn().isDisplayed());
		
		this.homePage.getUserBtn().click();
		
		assertTrue(this.homePage.getUserMenu().isDisplayed());
		
		assertTrue(this.homePage.getLogoutLink().isDisplayed());
		
		this.homePage.getLogoutLink().click();
		
		justWait();
		
		assertEquals(HOME_PAGE + "home-page", this.driver.getCurrentUrl());
		
		assertTrue(this.homePage.getLoginBtn().isDisplayed());
		
	}
	
	@Test
	public void testFilterName() throws InterruptedException {
		driver.get(HOME_PAGE);
		
		justWait();
		
		this.homePage.ensureIsPageDisplayed();
		
		assertTrue(this.homePage.getNameInput().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());
		
		this.homePage.getNameInput().sendKeys("Sonsing");
		this.homePage.getApplyFilterBtn().click();
		
		justWait();
		
		assertEquals(1, this.homePage.getOffers().size());
	}
	
	@Test
	public void testFilterNameFail() throws InterruptedException {
		driver.get(HOME_PAGE);
		
		justWait();
		
		this.homePage.ensureIsPageDisplayed();
		
		assertTrue(this.homePage.getNameInput().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());
		
		this.homePage.getNameInput().sendKeys("Non existing name");
		this.homePage.getApplyFilterBtn().click();
		
		justWait();
		
		assertTrue(this.homePage.getNoOffersDiv().isDisplayed());
	}
	
	@Test
	public void testFilterType() throws InterruptedException {
		driver.get(HOME_PAGE);
		
		justWait();
		
		this.homePage.ensureIsPageDisplayed();
		
		assertTrue(this.homePage.getTypeSelect().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());
		
		this.homePage.getTypeSelect().click();
		List<WebElement> options = this.homePage.getTypeOptions();
		
		for (WebElement el : options) {
			if(el.getText().equalsIgnoreCase("Cultural centre"))
				el.click();
		}
		
		justWait();
		
		this.homePage.getApplyFilterBtn().click();
		
		justWait();
		
		assertTrue(this.homePage.getOffers().size() > 0);
	}
	
	@Test
	public void testFilterNameAndType() throws InterruptedException {
		driver.get(HOME_PAGE);
		
		justWait();
		
		this.homePage.ensureIsPageDisplayed();
		
		assertTrue(this.homePage.getNameInput().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());
		assertTrue(this.homePage.getTypeSelect().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());
		
		this.homePage.getNameInput().sendKeys("Sonsing");
		
		this.homePage.getApplyFilterBtn().click();
		List<WebElement> options = this.homePage.getTypeOptions();
		
		for (WebElement el : options) {
			if(el.getText().equalsIgnoreCase("Cultural centre"))
				el.click();
		}
		
		justWait();
		
		this.homePage.getApplyFilterBtn().click();
		
		justWait();
		
		assertEquals(1, this.homePage.getOffers().size());
	}
	
	@Test
	public void testFilterNameFailAndType() throws InterruptedException {
		driver.get(HOME_PAGE);
		
		justWait();
		
		this.homePage.ensureIsPageDisplayed();
		
		assertTrue(this.homePage.getNameInput().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());
		assertTrue(this.homePage.getTypeSelect().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());
		
		this.homePage.getNameInput().sendKeys("Non existing name");
		
		this.homePage.getApplyFilterBtn().click();
		List<WebElement> options = this.homePage.getTypeOptions();
		
		for (WebElement el : options) {
			if(el.getText().equalsIgnoreCase("Cultural centre"))
				el.click();
		}
		
		justWait();
		
		this.homePage.getApplyFilterBtn().click();
		
		justWait();
		
		assertTrue(this.homePage.getNoOffersDiv().isDisplayed());
	}
	
	@Test
	public void testOpenOfferDetailsPage() throws InterruptedException {
		driver.get(HOME_PAGE);
		
		justWait();
		
		this.homePage.ensureIsPageDisplayed();
		
		assertEquals(this.homePage.getOffers().size(), 8);
		
		this.homePage.getOffers().get(0).click();
		
		justWait();
		
		assertEquals("http://localhost:4200/offer-details/1", this.driver.getCurrentUrl());
	}

	@Test
	public void testSubscribeAndUnsubscribe() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertTrue(this.homePage.getSubscribeToggle().isDisplayed());

		//Subscribe
		
		this.homePage.getSubscribeToggle().click();

		this.homePage.ensureSwalIsDisplayed();

		assertTrue(this.homePage.getSwalSuccess().isDisplayed());
		
		this.homePage.getSwalBtn().click();
		
		//Unsubscribe

		this.homePage.getSubscribeToggle().click();

		this.homePage.ensureSwalIsDisplayed();

		assertTrue(this.homePage.getSwalSuccess().isDisplayed());
		
		this.homePage.getSwalBtn().click();
	}

	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}

}
