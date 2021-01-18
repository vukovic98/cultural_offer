package com.ftn.kts_nvt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCategoryPage {

	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"name\"]")
	private WebElement name;
	
	@FindBy(xpath = "//*[@id=\"submit\"]")
	private WebElement submit;
	
	@FindBy(xpath = "//*[@id=\"editNameDialog\"]")
	private WebElement editNameDialog;
	
	@FindBy(xpath = "//*[@id=\"saveDialogButton\"]")
	private WebElement saveDialogButton;
	
	@FindBy(xpath = "//*[@id=\"byname\"]")
	private WebElement byname;
	
	@FindBy(xpath = "//div[contains(@class, 'swal2-popup')]")
	private WebElement swalAlert;
	
	public AddCategoryPage() {
		super();
	}

	public AddCategoryPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getName() {
		return name;
	}

	public WebElement getSubmit() {
		return submit;
	}

	public WebElement getEditNameDialog() {
		return editNameDialog;
	}

	public WebElement getSaveDialogButton() {
		return saveDialogButton;
	}

	public WebElement getByname() {
		return byname;
	}

	public WebElement getSwalAlert() {
		return swalAlert;
	}

	public void ensureIsDisplayedSwal() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.className("swal2-popup")));
	}

	public boolean isSwalVisible() {
		return this.swalAlert.isDisplayed();
	}
}
