package com.ftn.kts_nvt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OfferDetailsRegisteredUserPage {

	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"subscribersCountText\"]")
	private WebElement subscribersCountText;
	
	@FindBy(xpath = "//div[contains(@class, 'swal2-popup')]")
	private WebElement swalAlert;
	
	@FindBy(xpath = "//*[@id=\"deletePostButton\"]")
	private WebElement deletePostButton;
	
	@FindBy(xpath = "//*[@id=\"addPostsButton\"]")
	private WebElement addPostsButton;
	
	@FindBy(xpath = "//*[@id=\"addCommentPanelId\"]")
	private WebElement addCommentPanel;
	
	public OfferDetailsRegisteredUserPage() {
		super();
	}

	public OfferDetailsRegisteredUserPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getAddCommentPanel() {
		return addCommentPanel;
	}

	public WebElement getDeletePostButton() {
		return deletePostButton;
	}
	public void ensureIsNotVisibleDeletePostButton() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("deletePostButton")));
	}
	
	public WebElement getAddPostsButton() {
		return addPostsButton;
	}
	
	public void ensureIsNotVisibleAddPostButton() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("addPostsButton")));
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
