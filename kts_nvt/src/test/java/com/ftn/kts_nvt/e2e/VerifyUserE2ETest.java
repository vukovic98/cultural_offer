package com.ftn.kts_nvt.e2e;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.ftn.kts_nvt.pages.VerifyPage;


public class VerifyUserE2ETest {
	
	private WebDriver driver;
	
	private VerifyPage verifyPage;
	
	@Before
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver();

		this.driver.manage().window().maximize();
		this.verifyPage = PageFactory.initElements(driver, VerifyPage.class);
		
	}

	@After
	public void tearDown() {
		this.driver.quit();
	}
	
	@Test
	public void VerifyTestSuccess() throws InterruptedException {
		driver.get("http://localhost:4200/verify?email=luna@maildrop.cc");
		
		justWait();
		
		assertTrue(!verifyPage.getVerifyBtn().isEnabled());
		
		verifyPage.getCode().sendKeys("enobH824pm");
		verifyPage.getVerifyBtn().click();
		verifyPage.ensureGoToLoginButtonIsVisible();
		verifyPage.getGoToLoginPageBtn().click();
		
		assertEquals("http://localhost:4200/login", driver.getCurrentUrl());
		
	}

	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}
	
	

}
