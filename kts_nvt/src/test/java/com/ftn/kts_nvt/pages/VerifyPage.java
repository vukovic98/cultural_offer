package com.ftn.kts_nvt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyPage {
	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"code\"]")
	private WebElement code;
	
	@FindBy(xpath = "//*[@id=\"verifyBtn\"]")
	private WebElement verifyBtn;
	
	@FindBy(xpath = "//button[contains(@class, \"swal2-confirm swal2-styled\")]")
	private WebElement goToLoginPageBtn;
	
	public VerifyPage() {
		super();
	}
	
	public VerifyPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebElement getCode() {
		return code;
	}

	public void setCode(WebElement code) {
		this.code = code;
	}

	public WebElement getVerifyBtn() {
		return verifyBtn;
	}

	public void setVerifyBtn(WebElement verifyBtn) {
		this.verifyBtn = verifyBtn;
	}
	
	public WebElement getGoToLoginPageBtn() {
		return goToLoginPageBtn;
	}

	public void setGoToLoginPageBtn(WebElement goToLoginPageBtn) {
		this.goToLoginPageBtn = goToLoginPageBtn;
	}

	public void ensureButtonIsVisible() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("verifyBtn")));
	}
	
	public void ensureGoToLoginButtonIsVisible() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("swal2-confirm")));
	}
	
}
