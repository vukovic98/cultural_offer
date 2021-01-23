package com.ftn.kts_nvt.e2e;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.pages.LoginPage;

public class LoginE2ETest {

	private WebDriver driver;

	private LoginPage loginPage;

	@Before
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver();

		this.driver.manage().window().maximize();
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
		// categoryPage = PageFactory.initElements(driver, CategoryPage.class);
	}

	@After
	public void tearDown() {
		this.driver.quit();
	}
	
	@Test
	public void loginTestSuccess() throws InterruptedException {
		driver.get("http://localhost:4200/login");
		
		justWait();
		
		assertFalse(loginPage.getLoginBtn().isEnabled());

        loginPage.getEmail().sendKeys("vladimirvukovic98@maildrop.cc");

        loginPage.getPassword().sendKeys("vukovic");
        
        loginPage.ensureIsButtonEnabled();

        loginPage.getLoginBtn().click();

        justWait();
        
        loginPage.ensureIsNotVisibleButton();

        assertEquals("http://localhost:4200/home-page", driver.getCurrentUrl());
	}
	
	@Test
	public void loginTestFailUsername() throws InterruptedException {
		driver.get("http://localhost:4200/auth/login");
		
		justWait();
		
		assertTrue(!loginPage.getLoginBtn().isEnabled());

        loginPage.getEmail().sendKeys("vukovic@vukovic");

        loginPage.getPassword().sendKeys("vukovic");

        loginPage.getLoginBtn().click();

        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());
        
        loginPage.ensureIsDisplayedSwal();
        
        assertTrue(loginPage.isSwalVisible());
	}
	
	@Test
	public void loginTestFailPassword() throws InterruptedException {
		driver.get("http://localhost:4200/auth/login");
		
		justWait();
		
		assertTrue(!loginPage.getLoginBtn().isEnabled());

        loginPage.getEmail().sendKeys("vladimirvukovic98@maildrop.cc");

        loginPage.getPassword().sendKeys("vukovic123");

        loginPage.getLoginBtn().click();

        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());
        
        loginPage.ensureIsDisplayedSwal();
        
        assertTrue(loginPage.isSwalVisible());
	}

	private void justWait() throws InterruptedException {
        synchronized (driver)
        {
            driver.wait(1000);
        }
    }
}
