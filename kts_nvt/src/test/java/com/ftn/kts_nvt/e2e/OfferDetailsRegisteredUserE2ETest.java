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
import org.openqa.selenium.chrome.ChromeOptions;
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

	private static String REGISTERED_USER_USERNAME = "vladimirvukovic98@maildrop.cc";
	private static String REGISTERED_USER_PASSWORD = "vukovic";
	private static String HOME_PAGE_PATH = "https://localhost:4200/home-page";
	private static String LOGIN_PAGE_PATH = "https://localhost:4200/auth/login";
	private static String OFFER_DETAILS_PAGE_PATH = "https://localhost:4200/cultural-offer/offer-details/999";
	
	@Before
	public void setup() throws InterruptedException {
		ChromeOptions option= new ChromeOptions();
        option.addArguments("ignore-certificate-errors");
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver(option);

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
		
		driver.get(OFFER_DETAILS_PAGE_PATH);
		justWait();
		assertEquals(OFFER_DETAILS_PAGE_PATH, driver.getCurrentUrl());
		justWait();justWait();
		detailsPage.ensureIsNotVisibleAddPostButton();
		detailsPage.ensureIsNotVisibleDeletePostButton();
	}
	
//	@Test
//	public void a_openMapDialog() throws InterruptedException {
//	    driver.findElement(By.xpath("//figure/div/button/span")).click();
//	    justWait();
//	    assertEquals(true, driver.findElement(By.id("mapid")).isDisplayed());
//	}
	
	@Test
	public void b_subscribe() {
	    this.detailsPage.getSubscribeToggle().click();
	    detailsPage.ensureIsDisplayedSwal();
	    assertTrue(detailsPage.isSwalVisible());
	    assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	    this.detailsPage.getSwalButton().click();
	}
	
	@Test
	public void c_unsubscribe() {
	    this.detailsPage.getSubscribeToggle().click();
	    detailsPage.ensureIsDisplayedSwal();
	    assertTrue(detailsPage.isSwalVisible());
	    assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	    this.detailsPage.getSwalButton().click();
	}
	
	@Test
	public void d_grade() {	
		this.detailsPage.getGradeStar().click();
		detailsPage.ensureIsDisplayedSwal();
	    assertTrue(detailsPage.isSwalVisible());
	    assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
		this.detailsPage.getSwalButton().click();
	}

	@Test
	public void e_comment() throws InterruptedException {

		this.detailsPage.getCommentPanel().click();
		justWait();
	    this.detailsPage.getCommentText().click();
	    this.detailsPage.getCommentText().clear();
	    this.detailsPage.getCommentText().sendKeys("test_comment");
	    this.detailsPage.getCommentSubmit().click();
	    justWait();
	    assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	    this.detailsPage.getSwalButton().click();
		
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
