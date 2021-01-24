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
	
	@FindBy(xpath = "//*[@id=\"deletePostButton\"]")
	private WebElement deletePostButton;
	
	@FindBy(xpath = "//*[@id=\"deleteCommentButton\"]")
	private WebElement deleteCommentButton;
	
	@FindBy(xpath = "//*[@id=\"addPostsButton\"]")
	private WebElement addPostsButton;
	
	@FindBy(xpath = "//*[@id=\"addNewPostButton\"]")
	private WebElement addNewPostButton;
	
	@FindBy(xpath = "//*[@id=\"addCommentPanelId\"]")
	private WebElement addCommentPanel;
	
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

	public WebElement getDeletePostButton() {
		return deletePostButton;
	}
	
	
	
	public WebElement getDeleteCommentButton() {
		return deleteCommentButton;
	}

	public WebElement getAddNewPostButton() {
		return addNewPostButton;
	}
	
	public WebElement getAddPostsButton() {
		return addPostsButton;
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

	public void ensureIsNotVisibleAddCommentPanel() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("addCommentPanelId")));
	}
	
	public void ensureIsDisplayedSwal() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.className("swal2-popup")));
	}

	public boolean isSwalVisible() {
		return this.swalAlert.isDisplayed();
	}
}
