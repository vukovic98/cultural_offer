package com.ftn.kts_nvt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddOfferPage {
	
	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"name\"]")
	private WebElement offerName;

	@FindBy(xpath = "//*[@id=\"description\"]")
	private WebElement offerDescription;

	@FindBy(xpath = "//*[@id=\"categorySelect\"]")
	private WebElement categorySelect;

	@FindBy(xpath = "//*[@id=\"typeSelect\"]")
	private WebElement typeSelect;

	@FindBy(xpath = "//*[@id=\"mapid\"]")
	private WebElement mapid;

	@FindBy(xpath = "//*[@id=\"file\"]")
	private WebElement file;

	@FindBy(xpath = "button[@id='submit']/span")
	private WebElement submit;
	
	@FindBy(xpath = "//div[contains(@class, 'swal2-popup')]")
	private WebElement swalAlert;
	
	@FindBy(xpath = "//div[contains(@class, 'swal2-icon-success')]")
	private WebElement swalSuccess;
	
	public AddOfferPage() {
		super();
	}

	public AddOfferPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getSwalSuccess() {
		return swalSuccess;
	}

	public WebElement getSubmit() {
		return submit;
	}
	
	public WebElement getOfferName() {
		return offerName;
	}

	public WebElement getOfferDescription() {
		return offerDescription;
	}

	public WebElement getCategorySelect() {
		return categorySelect;
	}

	public WebElement getTypeSelect() {
		return typeSelect;
	}

	public WebElement getMapid() {
		return mapid;
	}

	public WebElement getFile() {
		return file;
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
