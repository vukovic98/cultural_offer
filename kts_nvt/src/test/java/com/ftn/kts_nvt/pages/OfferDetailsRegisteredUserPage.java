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

	@FindBy(xpath = "//mat-slide-toggle[@id='subscribe-toggle']/label/div")
	private WebElement subscribeToggle;

	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement swalButton;

	@FindBy(xpath = "//ul[4]/li/i")
	private WebElement gradeStar;

	@FindBy(xpath = "//mat-expansion-panel-header[@id='mat-expansion-panel-header-0']/span/mat-panel-title")
	private WebElement commentPanel;

	@FindBy(xpath = "//*[@id=\"commentText\"]")
	private WebElement commentText;

	@FindBy(xpath = "//*[@id=\"submit\"]")
	private WebElement commentSubmit;

	public OfferDetailsRegisteredUserPage() {
		super();
	}

	public OfferDetailsRegisteredUserPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebElement getGradeStar() {
		return gradeStar;
	}

	public WebElement getCommentPanel() {
		return commentPanel;
	}

	public void setCommentPanel(WebElement commentPanel) {
		this.commentPanel = commentPanel;
	}

	public WebElement getCommentText() {
		return commentText;
	}

	public void setCommentText(WebElement commentText) {
		this.commentText = commentText;
	}

	public WebElement getCommentSubmit() {
		return commentSubmit;
	}

	public void setCommentSubmit(WebElement commentSubmit) {
		this.commentSubmit = commentSubmit;
	}

	public void setGradeStar(WebElement gradeStar) {
		this.gradeStar = gradeStar;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getAddCommentPanel() {
		return addCommentPanel;
	}

	public WebElement getSubscribeToggle() {
		return subscribeToggle;
	}

	public void setSubscribeToggle(WebElement subscribeToggle) {
		this.subscribeToggle = subscribeToggle;
	}

	public WebElement getSwalButton() {
		return swalButton;
	}

	public void setSwalButton(WebElement swalButton) {
		this.swalButton = swalButton;
	}

	public void setSubscribersCountText(WebElement subscribersCountText) {
		this.subscribersCountText = subscribersCountText;
	}

	public void setSwalAlert(WebElement swalAlert) {
		this.swalAlert = swalAlert;
	}

	public void setDeletePostButton(WebElement deletePostButton) {
		this.deletePostButton = deletePostButton;
	}

	public void setAddPostsButton(WebElement addPostsButton) {
		this.addPostsButton = addPostsButton;
	}

	public void setAddCommentPanel(WebElement addCommentPanel) {
		this.addCommentPanel = addCommentPanel;
	}

	public WebElement getDeletePostButton() {
		return deletePostButton;
	}

	public void ensureIsNotVisibleDeletePostButton() {
		(new WebDriverWait(driver, 10))
				.until(ExpectedConditions.invisibilityOfElementLocated(By.id("deletePostButton")));
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
