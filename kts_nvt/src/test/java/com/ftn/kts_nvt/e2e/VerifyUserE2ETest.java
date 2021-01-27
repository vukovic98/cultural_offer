package com.ftn.kts_nvt.e2e;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.ftn.kts_nvt.pages.VerifyPage;


public class VerifyUserE2ETest {
	
	private WebDriver driver;
	
	private VerifyPage verifyPage;
	
	@Before
	public void setup() {

		ChromeOptions option= new ChromeOptions();
        option.addArguments("ignore-certificate-errors");
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver(option);

		this.driver.manage().window().maximize();
		this.verifyPage = PageFactory.initElements(driver, VerifyPage.class);
		
	}

	@After
	public void tearDown() {
		this.driver.quit();
	}
	
	@Test
	public void VerifyTestSuccess() throws InterruptedException {
		driver.get("https://localhost:4200/auth/verify?email=e2etest@maildrop.cc");
		
		justWait();
		
		assertTrue(!verifyPage.getVerifyBtn().isEnabled());
		
		verifyPage.getCode().sendKeys("jvjS1DefyK");
		verifyPage.getVerifyBtn().click();
		verifyPage.ensureGoToLoginButtonIsVisible();
		verifyPage.getGoToLoginPageBtn().click();
		
		justWait();
		
		assertEquals("https://localhost:4200/auth/login", driver.getCurrentUrl());
		
	}

	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}
	
	

}
