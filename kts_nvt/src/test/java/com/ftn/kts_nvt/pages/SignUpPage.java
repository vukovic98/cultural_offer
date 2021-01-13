package com.ftn.kts_nvt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
	

	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"firstName\"]")
	private WebElement firstName;
	
	@FindBy(xpath = "//*[@id=\"lastName\"]")
	private WebElement lastName;
	
	@FindBy(xpath = "//*[@id=\"email\"]")
	private WebElement email;

	@FindBy(xpath = "//*[@id=\"password\"]")
	private WebElement password;
	
	@FindBy(xpath = "//*[@id=\"passwordConfirm\"]")
	private WebElement passwordConfirm;
	
	
	@FindBy(xpath = "//*[@id=\"signUpBtn\"]")
	private WebElement signUpBtn;

	
	public SignUpPage() {
		super();
	}
	
	public SignUpPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebElement getFirstName() {
		return firstName;
	}

	public void setFirstName(WebElement firstName) {
		this.firstName = firstName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public void setLastName(WebElement lastName) {
		this.lastName = lastName;
	}

	public WebElement getEmail() {
		return email;
	}

	public void setEmail(WebElement email) {
		this.email = email;
	}

	public WebElement getPassword() {
		return password;
	}

	public void setPassword(WebElement password) {
		this.password = password;
	}

	public WebElement getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(WebElement passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public WebElement getSignUpBtn() {
		return signUpBtn;
	}

	public void setSignUpBtn(WebElement signUpBtn) {
		this.signUpBtn = signUpBtn;
	}

	
	public void ensureIsNotVisibleButton() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("signUpBtn")));
	}


}
