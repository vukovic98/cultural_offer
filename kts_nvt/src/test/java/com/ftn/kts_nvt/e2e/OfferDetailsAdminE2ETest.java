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

import com.ftn.kts_nvt.pages.HomePageAdminPage;
import com.ftn.kts_nvt.pages.LoginPage;
import com.ftn.kts_nvt.pages.OfferDetailsAdminPage;
import com.ftn.kts_nvt.pages.OfferDetailsRegisteredUserPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfferDetailsAdminE2ETest {

	private LoginPage loginPage;
	private HomePageAdminPage homePage;
	private OfferDetailsAdminPage detailsPage;
	
	private WebDriver driver;

	private static String REGISTERED_USER_USERNAME = "vlado@gmail.com";
	private static String REGISTERED_USER_PASSWORD = "vukovic";
	private static String HOME_PAGE_PATH = "https://localhost:4200/home-page";
	private static String LOGIN_PAGE_PATH = "https://localhost:4200/auth/login";
	private static String OFFER_DETAILS_PAGE_PATH = "https://localhost:4200/cultural-offer/offer-details/40";
	
	@Before
	public void setup() throws InterruptedException {
		ChromeOptions option= new ChromeOptions();
        option.addArguments("ignore-certificate-errors");
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver(option);

		this.driver.manage().window().maximize();
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
		this.homePage = PageFactory.initElements(driver, HomePageAdminPage.class);
		this.detailsPage = PageFactory.initElements(driver, OfferDetailsAdminPage.class);
		
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
		this.detailsPage.ensureIsLoadedPage();
		justWait();
		assertEquals(OFFER_DETAILS_PAGE_PATH, driver.getCurrentUrl());
		justWait(); justWait();
		detailsPage.ensureIsNotVisibleAddCommentPanel();
	}
	@Test
	public void a_addPost() throws InterruptedException {
		justWait(); 	

		detailsPage.getAddNewPostButton().click();

		detailsPage.getTitle().clear();
		detailsPage.getTitle().sendKeys("newposttitle");
		detailsPage.getContent().clear();
		detailsPage.getContent().sendKeys("newpostcontent");	
		
	    this.detailsPage.getAddPostSubmitBtn().click();
	   
	    detailsPage.ensureIsDisplayedSwal();
	    assertTrue(detailsPage.isSwalVisible());
	    
	    assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	    
	    this.detailsPage.getSwalButton().click();
	}
	
	@Test
	public void b_deletePost() throws InterruptedException {
		justWait(); justWait();

		detailsPage.getDeletePostButton().click();
		justWait(); justWait();
	    assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	    this.detailsPage.getSwalButton().click();	
	}
	@Test
	public void c_deleteComment() throws InterruptedException {
		
		detailsPage.getDeleteCommentButton().click();
		justWait();justWait();
		assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	    this.detailsPage.getSwalButton().click();		
	    justWait();
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
