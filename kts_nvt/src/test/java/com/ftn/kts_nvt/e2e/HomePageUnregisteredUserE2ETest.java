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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.ftn.kts_nvt.pages.HomePageUnregisteredUser;

public class HomePageUnregisteredUserE2ETest {
	
	public static final String HOME_PAGE = "https://localhost:4200/";

	private WebDriver driver;

	private HomePageUnregisteredUser homePage;

	@Before
	public void setup() {

		ChromeOptions option= new ChromeOptions();
        option.addArguments("ignore-certificate-errors");
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver(option);

		this.driver.manage().window().maximize();
		this.homePage = PageFactory.initElements(this.driver, HomePageUnregisteredUser.class);
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

		assertTrue(this.homePage.getLoginBtn().isDisplayed());
		assertTrue(this.homePage.getSignUpBtn().isDisplayed());
		assertTrue(this.homePage.getApplyFilterBtn().isDisplayed());

		assertEquals(8, this.homePage.getOffers().size());
		
		justWait();justWait();
		
		int markersBefore = this.homePage.getMarkers().size();
		assertEquals(8,markersBefore);

	}
	
	@Test
	public void testLogin() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertEquals(HOME_PAGE + "home-page", this.driver.getCurrentUrl());
		
		this.homePage.getLoginBtn().click();
		
		justWait();
		
		assertEquals("https://localhost:4200/auth/login", this.driver.getCurrentUrl());
	}
	
	@Test
	public void testSignUp() throws InterruptedException {
		driver.get(HOME_PAGE);

		justWait();

		this.homePage.ensureIsPageDisplayed();

		assertEquals(HOME_PAGE + "home-page", this.driver.getCurrentUrl());
		
		this.homePage.getSignUpBtn().click();
		
		justWait();
		
		assertEquals("http://localhost:4200/auth/sign-up", this.driver.getCurrentUrl());
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
			if(el.getText().equalsIgnoreCase("Cultural centre"))
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
			if(el.getText().equalsIgnoreCase("Cultural centre"))
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
			if(el.getText().equalsIgnoreCase("Cultural centre"))
				el.click();
		}
		
		justWait();
		
		this.homePage.getApplyFilterBtn().click();
		
		justWait();
		
		assertTrue(this.homePage.getNoOffersDiv().isDisplayed());
		
		int markersAfter = this.homePage.getMarkers().size();
		assertEquals(markersAfter, 0);
	}
	
	@Test
	public void testOpenOfferDetailsPage() throws InterruptedException {
		driver.get(HOME_PAGE);
		
		justWait();
		
		this.homePage.ensureIsPageDisplayed();
		
		assertEquals(this.homePage.getOffers().size(), 8);
		
		this.homePage.getOffers().get(0).click();
		
		justWait();
		
		assertEquals("https://localhost:4200/cultural-offer/offer-details/1", this.driver.getCurrentUrl());
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
		
		justWait();
		
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
		assertEquals("https://localhost:4200/cultural-offer/offer-details/1", this.driver.getCurrentUrl());
		
		
	}
	
	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}
}
