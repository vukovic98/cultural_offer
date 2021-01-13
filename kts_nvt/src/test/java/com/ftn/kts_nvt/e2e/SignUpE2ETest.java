package com.ftn.kts_nvt.e2e;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.beans.VerificationCode;
import com.ftn.kts_nvt.pages.SignUpPage;
import com.ftn.kts_nvt.pages.VerifyPage;
import com.ftn.kts_nvt.services.RegisteredUserService;
import com.ftn.kts_nvt.services.VerificationCodeService;

public class SignUpE2ETest {

	private WebDriver driver;

	private SignUpPage signUpPage;
	
	private VerifyPage verifyPage;
	

	@Before
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		this.driver = new ChromeDriver();

		this.driver.manage().window().maximize();
		this.signUpPage = PageFactory.initElements(driver, SignUpPage.class);
		this.verifyPage = PageFactory.initElements(driver, VerifyPage.class);
		
	}

	@After
	public void tearDown() throws Exception {
		this.driver.quit();
	}

	@Test
	public void signUpTestSuccess() throws Exception {
		
		driver.get("http://localhost:4200/sign-up");
		justWait();
		assertTrue(!signUpPage.getSignUpBtn().isEnabled());

		
		String email = "ttt@maildrop.cc";

		signUpPage.getFirstName().sendKeys("Ivana");
		signUpPage.getLastName().sendKeys("Vlaisavljevic");
		signUpPage.getEmail().sendKeys(email);
		signUpPage.getPassword().sendKeys("1234567i");
		signUpPage.getPasswordConfirm().sendKeys("1234567i");
		signUpPage.getSignUpBtn().click();

		justWait();
		
		verifyPage.ensureButtonIsVisible();

		assertEquals("http://localhost:4200/verify?email=" + email, driver.getCurrentUrl());
	
	}

	
	  @Test public void signUpTestFail() throws InterruptedException {
	  driver.get("http://localhost:4200/sign-up");
	  
	  justWait();
	  
	  assertTrue(!signUpPage.getSignUpBtn().isEnabled());
	  
	  signUpPage.getFirstName().sendKeys("Ivana");
	  signUpPage.getLastName().sendKeys("Vlaisavljevic");
	  signUpPage.getEmail().sendKeys("haha@maildrop.cc");
	  signUpPage.getPassword().sendKeys("1234567i");
	  signUpPage.getPasswordConfirm().sendKeys("1234567i");
	  signUpPage.getSignUpBtn().click();
	  
	  justWait();
	  
	  assertFalse("http://localhost:4200/verify?email=haha@maildrop.cc".
	  equalsIgnoreCase(driver.getCurrentUrl())); }
	 

	private void justWait() throws InterruptedException {
		synchronized (driver) {
			driver.wait(1000);
		}
	}

}
