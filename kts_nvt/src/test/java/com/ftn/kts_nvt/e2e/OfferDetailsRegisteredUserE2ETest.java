package com.ftn.kts_nvt.e2e;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.ftn.kts_nvt.pages.AddOfferPage;
import com.ftn.kts_nvt.pages.HomePageAdminPage;
import com.ftn.kts_nvt.pages.LoginPage;
import com.ftn.kts_nvt.pages.OfferDetailsRegisteredUserPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfferDetailsRegisteredUserE2ETest {

	private LoginPage loginPage;
	private HomePageAdminPage homePage;
	private OfferDetailsRegisteredUserPage detailsPage;
	
	private WebDriver driver;

	private static String REGISTERED_USER_USERNAME = "a@gmail.com";
	private static String REGISTERED_USER_PASSWORD = "vukovic";
	private static String HOME_PAGE_PATH = "http://localhost:4200/home-page";
	private static String LOGIN_PAGE_PATH = "http://localhost:4200/auth/login";
	private static String OFFER_DETAILS_PAGE_PATH = "http://localhost:4200/cultural-offer/offer-details/1";
	
	@Before
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver();

		this.driver.manage().window().maximize();
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
		this.homePage = PageFactory.initElements(driver, HomePageAdminPage.class);
		this.detailsPage = PageFactory.initElements(driver, OfferDetailsRegisteredUserPage.class);
		
		driver.get(LOGIN_PAGE_PATH);

		justWait();

		assertFalse(loginPage.getLoginBtn().isEnabled());
		loginPage.getEmail().sendKeys(REGISTERED_USER_USERNAME);
		loginPage.getPassword().sendKeys(REGISTERED_USER_PASSWORD);
		loginPage.ensureIsButtonEnabled();
		loginPage.getLoginBtn().click();
		justWait();
		loginPage.ensureIsNotVisibleButton();
		assertEquals(HOME_PAGE_PATH, driver.getCurrentUrl());
		//homePage.getOffers().get(0).click();
		driver.get(OFFER_DETAILS_PAGE_PATH);
		justWait();
		assertEquals(OFFER_DETAILS_PAGE_PATH, driver.getCurrentUrl());
	}
	
	@Test
	public void a_openMapDialog() throws InterruptedException {
	    driver.findElement(By.xpath("//figure/div/button/span")).click();
	    justWait();
	    assertEquals(true, driver.findElement(By.id("mapid")).isDisplayed());
	}
	
	@Test
	public void b_subscribe() {
		//ponuda ima na pocetku 0 subscribersCount
	    assertEquals("0", driver.findElement(By.id("subscribersCountText")).getText());
	    driver.findElement(By.xpath("//mat-slide-toggle[@id='subscribe-toggle']/label/div")).click();
	    detailsPage.ensureIsDisplayedSwal();
	    assertTrue(detailsPage.isSwalVisible());
	    assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    assertEquals("1", driver.findElement(By.id("subscribersCountText")).getText());
	}
	
	@Test
	public void c_unsubscribe() {
	    assertEquals("1", driver.findElement(By.id("subscribersCountText")).getText());
	    driver.findElement(By.xpath("//mat-slide-toggle[@id='subscribe-toggle']/label/div")).click();
	    detailsPage.ensureIsDisplayedSwal();
	    assertTrue(detailsPage.isSwalVisible());
	    assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    assertEquals("0", driver.findElement(By.id("subscribersCountText")).getText());
	}
	
	@Test
	public void d_grade() {
		//ponuda ima avgGrade 0 i ocenjuje se sa ocenom 4
		assertEquals("0", driver.findElement(By.xpath("//p")).getText());	
		driver.findElement(By.xpath("//ul[4]/li/i")).click();
		detailsPage.ensureIsDisplayedSwal();
	    assertTrue(detailsPage.isSwalVisible());
	    assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    assertEquals("4", driver.findElement(By.xpath("//p")).getText());
	}
	
	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}
	
	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}
}
