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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.ftn.kts_nvt.pages.AddCategoryPage;
import com.ftn.kts_nvt.pages.AddTypePage;
import com.ftn.kts_nvt.pages.HomePageAdminPage;
import com.ftn.kts_nvt.pages.LoginPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddCategoryE2ETest {

	private LoginPage loginPage;
	private HomePageAdminPage homePage;
	private AddCategoryPage addCategoryPage;
	
	private WebDriver driver;

	private static String ADMIN_USERNAME = "admin@gmail.com";
	private static String ADMIN_PASSWORD = "vukovic";
	private static String HOME_PAGE_PATH = "http://localhost:4200/home-page";
	private static String CATEGORY_PAGE_PATH = "http://localhost:4200/category";
	private static String LOGIN_PAGE_PATH = "http://localhost:4200/auth/login";
	
	/* U TABELI SU
	 * Name--------------------Types
	 * Institution ------------2 types
	 * Manifestacion-----------0 types
	 * Landmark----------------2 types
	 * 
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
		this.addCategoryPage = PageFactory.initElements(driver, AddCategoryPage.class);
		
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
		homePage.getCategoryLink().click();
		justWait();
		assertEquals(CATEGORY_PAGE_PATH, driver.getCurrentUrl());
	}
	
	@Test
	public void a_addCategory() throws InterruptedException {
		int rowsBefore = driver.findElements(By.cssSelector("tr")).size();
		
	    addCategoryPage.getName().clear();
	    addCategoryPage.getName().sendKeys("newcategory");
	    addCategoryPage.getSubmit().click();
	    justWait();
	    addCategoryPage.ensureIsDisplayedSwal();
	    assertTrue(addCategoryPage.isSwalVisible());
	    assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    justWait();justWait();
	    
	    int rowsAfter = driver.findElements(By.cssSelector("tr")).size();
	    assertEquals(rowsBefore + 1, rowsAfter);

	    assertEquals("newcategory", addCategoryPage.getLastRow().getText());
	    //assertEquals("newcategory", driver.findElement(By.xpath("//tr[4]/td")).getText());
	}
	
	@Test
	public void b_changeNameAlreadyExist() {
		int rowsBefore = driver.findElements(By.cssSelector("tr")).size();
		driver.findElement(By.xpath("(//button[@id='editButton']/span/mat-icon)[4]")).click();
		addCategoryPage.getEditNameDialog().clear();
		addCategoryPage.getEditNameDialog().sendKeys("Institution");
		addCategoryPage.getSaveDialogButton().click();
	    addCategoryPage.ensureIsDisplayedSwal();
	    assertTrue(addCategoryPage.isSwalVisible());
	    assertEquals("Error!", driver.findElement(By.id("swal2-title")).getText());
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();	
	    int rowsAfter = driver.findElements(By.cssSelector("tr")).size();
	    assertEquals(rowsBefore, rowsAfter);
	}
	
	@Test
	public void c_changeNameSuccess() {
	    assertEquals("newcategory", addCategoryPage.getLastRow().getText());
	    //assertEquals("newcategory", driver.findElement(By.xpath("//tr[4]/td")).getText());
	    driver.findElement(By.xpath("(//button[@id='editButton']/span/mat-icon)[4]")).click();
	    addCategoryPage.getEditNameDialog().clear();
	    addCategoryPage.getEditNameDialog().sendKeys("newcategory2");
	    addCategoryPage.getSaveDialogButton().click();
		addCategoryPage.ensureIsDisplayedSwal();
	    assertTrue(addCategoryPage.isSwalVisible());
		assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();	
	    assertEquals("newcategory2", addCategoryPage.getLastRow().getText());
	    //assertEquals("newcategory2", driver.findElement(By.xpath("//tr[4]/td")).getText());
	}
	
	@Test
	public void d_deleteSuccess() {
		int rowsBefore = driver.findElements(By.cssSelector("tr")).size();
		driver.findElement(By.xpath("(//button[@id='deleteButton']/span/mat-icon)[4]")).click();
		addCategoryPage.ensureIsDisplayedSwal();
	    assertTrue(addCategoryPage.isSwalVisible());
		assertEquals("Success!", driver.findElement(By.id("swal2-title")).getText());
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();	
	    int rowsAfter = driver.findElements(By.cssSelector("tr")).size();
	    assertEquals(rowsBefore - 1, rowsAfter);
	}
	
	@Test
	public void e_deleteFail() {
		int rowsBefore = driver.findElements(By.cssSelector("tr")).size();
		driver.findElement(By.xpath("//button[@id='deleteButton']/span/mat-icon")).click();
		addCategoryPage.ensureIsDisplayedSwal();
	    assertTrue(addCategoryPage.isSwalVisible());
		assertEquals("Error!", driver.findElement(By.id("swal2-title")).getText());
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();	
	    int rowsAfter = driver.findElements(By.cssSelector("tr")).size();
	    assertEquals(rowsBefore, rowsAfter);
	}
	
	@Test
	public void f_search() {
		addCategoryPage.getByname().click();
		addCategoryPage.getByname().clear();
		addCategoryPage.getByname().sendKeys("Institution");
	    driver.findElement(By.xpath("//button[@id='search']/span")).click();
	    assertEquals("Institution", driver.findElement(By.xpath("//td")).getText());
	}
	
	@Test 
	public void g_searchFail() {
		addCategoryPage.getByname().click();
		addCategoryPage.getByname().clear();
		addCategoryPage.getByname().sendKeys("asdadas");
	    driver.findElement(By.xpath("//button[@id='search']/span")).click();
	    assertTrue(addCategoryPage.isSwalVisible());
		assertEquals("Error!", driver.findElement(By.id("swal2-title")).getText());
	    assertEquals("Category not found", driver.findElement(By.id("swal2-content")).getText());
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
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
