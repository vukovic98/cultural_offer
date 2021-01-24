package com.ftn.kts_nvt.e2e;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.ftn.kts_nvt.pages.HomePageAdminPage;
import com.ftn.kts_nvt.pages.LoginPage;

public class HomePageAdminE2ETest {

	public final static String HOME_PAGE = "http://localhost:4200/";

	private WebDriver driver;

	private HomePageAdminPage homePage;

	private LoginPage loginPage;

	@Before
	public void setup() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver();

		this.driver.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.driver, HomePageAdminPage.class);
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);

		driver.get(HOME_PAGE + "auth/login");

		this.loginPage.getEmail().sendKeys("vlado@gmail.com");
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

		assertTrue(this.homePage.getUserMenuBtn().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());

		assertEquals(8, this.homePage.getOffers().size());

	}

	@Test
	public void testUserMenu() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertEquals(HOME_PAGE + "home-page", this.driver.getCurrentUrl());

		assertTrue(this.homePage.getUserMenuBtn().isDisplayed());

		this.homePage.getUserMenuBtn().click();

		this.homePage.ensureUserMenuIsDisplayed();

		assertTrue(this.homePage.getUserMenu().isDisplayed());
	}

	@Test
	public void testProfileLink() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertTrue(this.homePage.getUserMenuBtn().isDisplayed());

		this.homePage.getUserMenuBtn().click();

		assertTrue(this.homePage.getUserMenu().isDisplayed());

		assertTrue(this.homePage.getProfileLink().isDisplayed());

		this.homePage.getProfileLink().click();

		justWait();

		assertEquals(HOME_PAGE + "user/profile", this.driver.getCurrentUrl());
	}

	@Test
	public void testChangePasswordLink() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertTrue(this.homePage.getUserMenuBtn().isDisplayed());

		this.homePage.getUserMenuBtn().click();

		assertTrue(this.homePage.getUserMenu().isDisplayed());

		assertTrue(this.homePage.getChangePasswordLink().isDisplayed());

		this.homePage.getChangePasswordLink().click();

		justWait();

		assertEquals(HOME_PAGE + "user/change-password", this.driver.getCurrentUrl());
	}

	@Test
	public void testLogoutLink() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertTrue(this.homePage.getUserMenuBtn().isDisplayed());

		this.homePage.getUserMenuBtn().click();

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

		justWait();justWait();
		
		int markersBefore = this.homePage.getMarkers().size();
		assertEquals(8,markersBefore);
		
		this.homePage.getNameInput().sendKeys("Sonsing");
		this.homePage.getApplyFilterBtn().click();

		justWait();

		assertEquals(1, this.homePage.getOffers().size());
		
		int markersAfter = this.homePage.getMarkers().size();
		assertEquals(markersAfter, 1);
	}

	@Test
	public void testFilterNameFail() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertTrue(this.homePage.getNameInput().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());
		
		justWait();justWait();
		
		int markersBefore = this.homePage.getMarkers().size();
		assertEquals(8,markersBefore);

		this.homePage.getNameInput().sendKeys("Non existing name");
		this.homePage.getApplyFilterBtn().click();

		justWait();

		assertTrue(this.homePage.getNoOffersDiv().isDisplayed());
		
		int markersAfter = this.homePage.getMarkers().size();
		assertEquals(markersAfter, 0);
	}

	@Test
	public void testFilterType() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertTrue(this.homePage.getTypeSelect().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());

		int markersBefore = this.homePage.getMarkers().size();
		assertEquals(8,markersBefore);
		
		this.homePage.getTypeSelect().click();
		List<WebElement> options = this.homePage.getTypeOptions();

		for (WebElement el : options) {
			if (el.getText().equalsIgnoreCase("Cultural centre"))
				el.click();
		}

		justWait();

		this.homePage.getApplyFilterBtn().click();

		justWait();

		assertTrue(this.homePage.getOffers().size() > 0);
		
		int markersAfter = this.homePage.getMarkers().size();
		assertTrue(markersAfter > 0);
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
		
		int markersBefore = this.homePage.getMarkers().size();
		assertEquals(8,markersBefore);

		this.homePage.getNameInput().sendKeys("Sonsing");

		this.homePage.getApplyFilterBtn().click();
		List<WebElement> options = this.homePage.getTypeOptions();

		for (WebElement el : options) {
			if (el.getText().equalsIgnoreCase("Cultural centre"))
				el.click();
		}

		justWait();

		this.homePage.getApplyFilterBtn().click();

		justWait();

		assertEquals(1, this.homePage.getOffers().size());
		
		int markersAfter = this.homePage.getMarkers().size();
		assertEquals(markersAfter, 1);
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

		int markersBefore = this.homePage.getMarkers().size();
		assertEquals(8,markersBefore);
		
		this.homePage.getNameInput().sendKeys("Non existing name");

		this.homePage.getApplyFilterBtn().click();
		List<WebElement> options = this.homePage.getTypeOptions();

		for (WebElement el : options) {
			if (el.getText().equalsIgnoreCase("Cultural centre"))
				el.click();
		}

		justWait();

		this.homePage.getApplyFilterBtn().click();

		justWait();
		
		int markersAfter = this.homePage.getMarkers().size();
		assertEquals(markersAfter, 0);

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

		assertEquals("http://localhost:4200/cultural-offer/offer-details/1", this.driver.getCurrentUrl());
	}
	
	@Test
	public void testOpenOfferDetailsFromMarker() throws InterruptedException {
		
		driver.get(HOME_PAGE);
		
		justWait();
		
		this.homePage.ensureIsPageDisplayed();
		
		assertTrue(this.homePage.getNameInput().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());
		assertTrue(this.homePage.getTypeSelect().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());
		
		int markersBefore = this.homePage.getMarkers().size();
		assertEquals(8,markersBefore);
		
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
		
		this.homePage.getMarker().click();
		justWait();justWait();
		
		this.homePage.ensureIsMarkerLinkDisplayed();
		
		this.homePage.getMarkerLink().click();
		
		
		assertEquals("http://localhost:4200/cultural-offer/offer-details/1", this.driver.getCurrentUrl());
		
		
	}

	@Test
	public void testAddOfferLink() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertTrue(this.homePage.getAddOfferLink().isDisplayed());

		this.homePage.getAddOfferLink().click();
		
		justWait();

		assertEquals(HOME_PAGE + "cultural-offer/add-offer", driver.getCurrentUrl());
	}

	@Test
	public void testCategoryLink() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertTrue(this.homePage.getCategoryLink().isDisplayed());

		this.homePage.getCategoryLink().click();
		
		justWait();

		assertEquals(HOME_PAGE + "category", driver.getCurrentUrl());
	}

	@Test
	public void testTypeLink() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertTrue(this.homePage.getTypeLink().isDisplayed());

		this.homePage.getTypeLink().click();
		
		justWait();

		assertEquals(HOME_PAGE + "type", this.driver.getCurrentUrl());
	}

	@Test
	public void testApprovingCommentsLink() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();
		
		assertTrue(this.homePage.getApprovingCommentsLink().isDisplayed());
		
		this.homePage.getApprovingCommentsLink().click();
		
		justWait();
		
		assertEquals(HOME_PAGE + "comment/approving-comments", this.driver.getCurrentUrl());
	}

	@Test
	public void testEditOffer() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertTrue(this.homePage.getEditOfferBtn().isDisplayed());

		assertEquals("Sonsing", this.homePage.getOfferTitle().getText());

		this.homePage.getEditOfferBtn().click();

		justWait();

		this.homePage.getEditNameField().sendKeys("_test");

		justWait();

		assertTrue(this.homePage.getEditSaveBtn().isDisplayed());

		this.homePage.getEditSaveBtn().click();

		justWait();

		assertTrue(this.homePage.getOfferTitle().isDisplayed());
		
		this.homePage.ensureSwalIsDisplayed();
		
		assertTrue(this.homePage.getSwalSuccess().isDisplayed());
		
		assertTrue(this.homePage.getSwalBtn().isDisplayed());
		
		this.homePage.getSwalBtn().click();
		
		justWait();

		assertEquals("Sonsing_test", this.homePage.getOfferTitle().getText());
		
		//RETURN TO PREVIOUS VALUE
		
		assertTrue(this.homePage.getEditOfferBtn().isDisplayed());

		assertEquals("Sonsing_test", this.homePage.getOfferTitle().getText());

		this.homePage.getEditOfferBtn().click();

		justWait();

		this.homePage.getEditNameField().clear();
		this.homePage.getEditNameField().sendKeys("Sonsing");

		justWait();

		assertTrue(this.homePage.getEditSaveBtn().isDisplayed());

		this.homePage.getEditSaveBtn().click();

		justWait();

		assertTrue(this.homePage.getOfferTitle().isDisplayed());
		
		this.homePage.ensureSwalIsDisplayed();
		
		assertTrue(this.homePage.getSwalSuccess().isDisplayed());
		
		assertTrue(this.homePage.getSwalBtn().isDisplayed());
		
		this.homePage.getSwalBtn().click();
		
		justWait();

		assertEquals("Sonsing", this.homePage.getOfferTitle().getText());
	}
	
	@Test
	public void testCancelEdit() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertTrue(this.homePage.getEditOfferBtn().isDisplayed());

		assertEquals("Sonsing", this.homePage.getOfferTitle().getText());

		this.homePage.getEditOfferBtn().click();

		justWait();

		assertTrue(this.homePage.getEditCloseBtn().isDisplayed());

		this.homePage.getEditCloseBtn().click();
		
		assertEquals("Sonsing", this.homePage.getOfferTitle().getText());

		justWait();
	}
	
	//DO NOT RUN!
	
//	@Test
//	public void testDeleteOffer() throws InterruptedException {
//		this.driver.get(HOME_PAGE);
//		
//		justWait();
//		
//		this.homePage.ensureIsPageDisplayed();
//		
//		assertTrue(this.homePage.getDeleteOfferBtn().isDisplayed());
//		
//		String deletedOfferTitle = this.homePage.getDeleteOfferTitle().getText();
//		
//		this.homePage.getDeleteOfferBtn().click();
//		
//		justWait();
//		
//		this.homePage.ensureIsPageDisplayed();
//		
//		assertNotEquals(deletedOfferTitle, this.homePage.getDeleteOfferTitle().getText());
//	}

	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}
}