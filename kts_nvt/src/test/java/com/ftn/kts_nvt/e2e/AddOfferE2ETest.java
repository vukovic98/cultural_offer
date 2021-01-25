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
import org.openqa.selenium.support.ui.Select;

import com.ftn.kts_nvt.pages.AddOfferPage;
import com.ftn.kts_nvt.pages.AddTypePage;
import com.ftn.kts_nvt.pages.HomePageAdminPage;
import com.ftn.kts_nvt.pages.LoginPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddOfferE2ETest {
	
	private LoginPage loginPage;
	private HomePageAdminPage homePage;
	private AddOfferPage addOfferPage;
	
	private WebDriver driver;

	private static String ADMIN_USERNAME = "admin@gmail.com";
	private static String ADMIN_PASSWORD = "vukovic";
	private static String HOME_PAGE_PATH = "https://localhost:4200/home-page";
	private static String ADD_OFFER_PAGE_PATH = "https://localhost:4200/cultural-offer/add-offer";
	private static String LOGIN_PAGE_PATH = "https://localhost:4200/auth/login";
	
	@Before
	public void setup() throws InterruptedException {
		ChromeOptions option= new ChromeOptions();
        option.addArguments("ignore-certificate-errors");
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver(option);

		this.driver.manage().window().maximize();
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
		this.homePage = PageFactory.initElements(driver, HomePageAdminPage.class);
		this.addOfferPage = PageFactory.initElements(driver, AddOfferPage.class);

		driver.get(LOGIN_PAGE_PATH);

		justWait();

		assertFalse(loginPage.getLoginBtn().isEnabled());
		loginPage.getEmail().sendKeys(ADMIN_USERNAME);
		loginPage.getPassword().sendKeys(ADMIN_PASSWORD);
		loginPage.ensureIsButtonEnabled();
		loginPage.getLoginBtn().click();
		justWait();
		loginPage.ensureIsNotVisibleButton();
		assertEquals(HOME_PAGE_PATH, driver.getCurrentUrl());
		homePage.getAddOfferLink().click();
		justWait();
		assertEquals(ADD_OFFER_PAGE_PATH, driver.getCurrentUrl());
	}
	
	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}
	
	
	@Test 
	public void a_addOffer() throws InterruptedException {
	    addOfferPage.getOfferName().click();
	    addOfferPage.getOfferName().clear();
	    addOfferPage.getOfferName().sendKeys("newoffername");
	    addOfferPage.getOfferDescription().clear();
	    addOfferPage.getOfferDescription().sendKeys("description");
	    addOfferPage.getCategorySelect().click();
	    new Select(addOfferPage.getCategorySelect()).selectByVisibleText("Institution");
	    addOfferPage.getCategorySelect().click();
	    justWait();
	    addOfferPage.getTypeSelect().click();
	    new Select(addOfferPage.getTypeSelect()).selectByVisibleText("Museum");
	    addOfferPage.getTypeSelect().click();	    
	    addOfferPage.getMapid().click();
	    addOfferPage.getFile().sendKeys("D:\\2020\\NVT\\projekat\\index.jpg");
	    justWait();
	    driver.findElement(By.xpath("//button[@id='submit']")).click();
	    justWait();
	    addOfferPage.ensureIsDisplayedSwal();
	    assertTrue(addOfferPage.isSwalVisible());
		assertTrue(this.addOfferPage.getSwalSuccess().isDisplayed());
	    //assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	}
	
	@Test 
	public void b_addOfferFail() throws InterruptedException {
	    addOfferPage.getOfferName().click();
	    addOfferPage.getOfferName().clear();
	    addOfferPage.getOfferName().sendKeys("newoffername");
	    addOfferPage.getOfferDescription().clear();
	    addOfferPage.getOfferDescription().sendKeys("description");
	    addOfferPage.getCategorySelect().click();
	    new Select(addOfferPage.getCategorySelect()).selectByVisibleText("Institution");
	    addOfferPage.getCategorySelect().click();
	    justWait();
	    addOfferPage.getTypeSelect().click();
	    new Select(addOfferPage.getTypeSelect()).selectByVisibleText("Museum");
	    addOfferPage.getTypeSelect().click();	    
	    addOfferPage.getMapid().click();
	    addOfferPage.getFile().sendKeys("D:\\2020\\NVT\\projekat\\index.jpg");
	    justWait();
	    driver.findElement(By.xpath("//button[@id='submit']")).click();
	    justWait();
	    addOfferPage.ensureIsDisplayedSwal();
	    assertTrue(addOfferPage.isSwalVisible());
	    assertEquals("Error!", driver.findElement(By.id("swal2-title")).getText());
		//assertTrue(this.homePage.getSwalSuccess().isDisplayed());
	    //assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	}
	
	@Test
	public void c_addImageTest() throws InterruptedException {
	    int itemsBefore= driver.findElements(By.id("imagesIdsTest")).size();
	    assertEquals(0, itemsBefore);
	    
	    addOfferPage.getFile().sendKeys("D:\\2020\\NVT\\projekat\\index.jpg");
	    justWait();
	    
	    int itemsAfterAdd = driver.findElements(By.id("imagesIdsTest")).size();
	    assertEquals(1, itemsAfterAdd);
	    
	    driver.findElement(By.xpath("//mat-icon")).click();
	    justWait();
	    
	    int itemsAfterDelete = driver.findElements(By.id("imagesIdsTest")).size();
	    assertEquals(0, itemsAfterDelete);
	    
	}
	
	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}
}
