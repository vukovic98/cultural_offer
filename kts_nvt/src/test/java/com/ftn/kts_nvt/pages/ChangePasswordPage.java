package com.ftn.kts_nvt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChangePasswordPage {

	private WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"oldPassword\"]")
	private WebElement oldPassword;
	
	@FindBy(xpath = "//*[@id=\"newPassword\"]")
	private WebElement newPassword;
	
	@FindBy(xpath = "//*[@id=\"confirmPassword\"]")
	private WebElement confirmedPassword;
	
	@FindBy(xpath = "//*[@id=\"changePassBtn\"]")
	private WebElement saveChangesBtn;
	
	@FindBy(xpath = "//*[@id=\"cancelBtn\"]")
	private WebElement cancelChangesBtn;
	
	public ChangePasswordPage() {
		super();
	}
	
	public ChangePasswordPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebElement getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(WebElement oldPassword) {
		this.oldPassword = oldPassword;
	}

	public WebElement getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(WebElement newPassword) {
		this.newPassword = newPassword;
	}

	public WebElement getConfirmedPassword() {
		return confirmedPassword;
	}

	public void setConfirmedPassword(WebElement confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}

	public WebElement getSaveChangesBtn() {
		return saveChangesBtn;
	}

	public void setSaveChangesBtn(WebElement saveChangesBtn) {
		this.saveChangesBtn = saveChangesBtn;
	}

	public WebElement getCancelChangesBtn() {
		return cancelChangesBtn;
	}

	public void setCancelChangesBtn(WebElement cancelChangesBtn) {
		this.cancelChangesBtn = cancelChangesBtn;
	}
	
	public void ensureIsPageDisplayed() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("changePasswordDiv")));
	}
}
