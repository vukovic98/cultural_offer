package com.ftn.kts_nvt.e2e;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.junit.runners.MethodSorters;

import com.ftn.kts_nvt.pages.AddTypePage;
import com.ftn.kts_nvt.pages.HomePageAdminPage;
import com.ftn.kts_nvt.pages.HomePageRegisteredUser;
import com.ftn.kts_nvt.pages.LoginPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddTypeE2ETest {

	private LoginPage loginPage;
	private HomePageAdminPage homePage;
	private AddTypePage addTypePage;
	
	private WebDriver driver;

	private static String ADMIN_USERNAME = "admin@gmail.com";
	private static String ADMIN_PASSWORD = "vukovic";
	private static String HOME_PAGE_PATH = "http://localhost:4200/home-page";
	private static String TYPE_PAGE_PATH = "http://localhost:4200/type";
	private static String LOGIN_PAGE_PATH = "http://localhost:4200/auth/login";
	
	/* U TABELI SU
	 * TYPE ----------- CATEGORY
	 * Parliament-------Institution
	 * Museum-----------Institution
	 * Tower------------Landmark
	 * Stadium----------Landmark
	 * 
	 * */
	
	@Before
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver();

		this.driver.manage().window().maximize();
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
		this.homePage = PageFactory.initElements(driver, HomePageAdminPage.class);
		this.addTypePage = PageFactory.initElements(driver, AddTypePage.class);
		
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
		homePage.getTypeLink().click();
		justWait();
		assertEquals(TYPE_PAGE_PATH, driver.getCurrentUrl());
	}
	
	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}

	@Test()
	public void a_addTypeTestSuccess() throws InterruptedException {	
		int rowsBefore = driver.findElements(By.cssSelector("tr")).size();

		addTypePage.getTypeName().clear();
		addTypePage.getTypeName().sendKeys("newtypename");

		addTypePage.getCategorySelect().click();
	    new Select(addTypePage.getCategorySelect()).selectByVisibleText("Institution");
	    addTypePage.getCategorySelect().click();
	    addTypePage.getSubmitButton().click();
	    addTypePage.ensureIsDisplayedSwal();
	    assertTrue(addTypePage.isSwalVisible());
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click(); //swal OK click
		justWait();
		justWait();
		
	    assertEquals("newtypename", addTypePage.getLastRow().getText());
	    assertEquals("Institution", addTypePage.getLastRow2Col().getText());
	    //assertEquals("newtypename", driver.findElement(By.xpath("//tr[5]/td")).getText());
	    //assertEquals("Institution", driver.findElement(By.xpath("//tr[5]/td[2]")).getText());
	    
	    int rowsAfter = driver.findElements(By.cssSelector("tr")).size();
	    assertEquals(rowsBefore + 1, rowsAfter);
	}
	
	@Test
	public void b_addTypeAlreadyExist() {
		int rowsBefore = driver.findElements(By.cssSelector("tr")).size();
		addTypePage.getTypeName().clear();
		addTypePage.getTypeName().sendKeys("Museum");
		addTypePage.getCategorySelect().click();
	    new Select(addTypePage.getCategorySelect()).selectByVisibleText("Institution");
	    addTypePage.getCategorySelect().click();
	    addTypePage.getSubmitButton().click();
	    addTypePage.ensureIsDisplayedSwal();
	    assertTrue(addTypePage.isSwalVisible());
	    assertEquals("Error!", driver.findElement(By.id("swal2-title")).getText());
	    int rowsAfter = driver.findElements(By.cssSelector("tr")).size();
	    assertEquals(rowsBefore, rowsAfter);
	}
	
	@Test
	public void c_deleteType() {
		int rowsBefore = driver.findElements(By.cssSelector("tr")).size();

		addTypePage.getLastRowDeleteButton().click();
		addTypePage.ensureIsDisplayedSwal();
	    assertTrue(addTypePage.isSwalVisible());
	    assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	    //driver.findElement(By.xpath("//tr[5]/td")
	    int rowsAfter = driver.findElements(By.cssSelector("tr")).size();
	    assertEquals(rowsBefore - 1, rowsAfter);
	}
	
	@Test
	public void d_deleteTypeFail() {
		int rowsBefore = driver.findElements(By.cssSelector("tr")).size();
		//brisanje prvog u tabeli daje gresku jer se on koristi u nekoj od ponuda
		driver.findElement(By.xpath("//tr[1]/td[3]/button[2]/span/mat-icon")).click();
		addTypePage.ensureIsDisplayedSwal();
	    assertTrue(addTypePage.isSwalVisible());
	    assertEquals("Error!", driver.findElement(By.id("swal2-title")).getText());
	    //driver.findElement(By.xpath("//tr[5]/td")
	    int rowsAfter = driver.findElements(By.cssSelector("tr")).size();
	    assertEquals(rowsBefore, rowsAfter);
	}

	@Test
	public void e_editType() {
	    driver.findElement(By.xpath("//mat-icon")).click();
	    addTypePage.getEditNewTypeNameInput().clear();//driver.findElement(By.xpath("(//input[@id='name'])[2]")).clear();
	    addTypePage.getEditNewTypeNameInput().sendKeys("Parliament2");//driver.findElement(By.xpath("(//input[@id='name'])[2]")).sendKeys("Parliament2");
	    addTypePage.getEditSaveButton().click();//driver.findElement(By.id("save")).click();	    
	    addTypePage.ensureIsDisplayedSwal();
	    assertTrue(addTypePage.isSwalVisible());
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    assertEquals("Parliament2", driver.findElement(By.xpath("//td")).getText());
	    
	    //rollback
	    driver.findElement(By.xpath("//mat-icon")).click();
	    addTypePage.getEditNewTypeNameInput().clear();//driver.findElement(By.xpath("(//input[@id='name'])[2]")).clear();
	    addTypePage.getEditNewTypeNameInput().sendKeys("Parliament");//driver.findElement(By.xpath("(//input[@id='name'])[2]")).sendKeys("Parliament2");
	    addTypePage.getEditSaveButton().click();//driver.findElement(By.id("save")).click();	   
	}
	
	@Test
	public void f_editTypeNameExistFail() {
	    driver.findElement(By.xpath("//mat-icon")).click();
	    addTypePage.getEditNewTypeNameInput().clear();//driver.findElement(By.xpath("(//input[@id='name'])[2]")).clear();
	    addTypePage.getEditNewTypeNameInput().sendKeys("Museum");//driver.findElement(By.xpath("(//input[@id='name'])[2]")).sendKeys("Parliament2");
	    addTypePage.getEditSaveButton().click();//driver.findElement(By.id("save")).click();	    
	    addTypePage.ensureIsDisplayedSwal();
	    assertTrue(addTypePage.isSwalVisible());
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    assertEquals("Parliament", driver.findElement(By.xpath("//td")).getText());
	}
	
	@Test
	public void g_search() throws InterruptedException {
		addTypePage.getByName().click();
		addTypePage.getByName().clear();
		addTypePage.getByName().sendKeys("Museum");
	    addTypePage.getSearchButton().click();
	    justWait();
	    assertEquals("Museum", driver.findElement(By.xpath("//td")).getText());
	}
	
	@Test 
	public void h_searchFail() throws InterruptedException {	    
	    addTypePage.getByName().click();
	    addTypePage.getByName().clear();
	    addTypePage.getByName().sendKeys("asasddsasda");
	    justWait();
	    addTypePage.getSearchButton().click();
	    assertTrue(addTypePage.isSwalVisible());
	    assertEquals("Error!", driver.findElement(By.id("swal2-title")).getText());
	    assertEquals("Type not found", driver.findElement(By.id("swal2-content")).getText());
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	}
	
	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}
}
