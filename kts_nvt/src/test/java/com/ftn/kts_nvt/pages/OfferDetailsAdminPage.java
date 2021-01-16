package com.ftn.kts_nvt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OfferDetailsAdminPage {

	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"title\"]")
	private WebElement title;
	
	@FindBy(xpath = "//*[@id=\"content\"]")
	private WebElement content;
	
	@FindBy(xpath = "//*[@id=\"subscribersCountText\"]")
	private WebElement subscribersCountText;
	
	@FindBy(xpath = "//div[contains(@class, 'swal2-popup')]")
	private WebElement swalAlert;
	
	public OfferDetailsAdminPage() {
		super();
	}

	public OfferDetailsAdminPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getTitle() {
		return title;
	}

	public WebElement getContent() {
		return content;
	}

	public WebElement getSubscribersCountText() {
		return subscribersCountText;
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
