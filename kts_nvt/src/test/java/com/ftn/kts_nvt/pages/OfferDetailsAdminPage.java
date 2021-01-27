package com.ftn.kts_nvt.pages;

import java.util.ArrayList;
import java.util.List;

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

	@FindBy(xpath = "//*[@id=\"addPostsButton\"]")
	private WebElement addNewPostButton;

	@FindBy(xpath = "//*[@id=\"addPostBtn\"]")
	private WebElement addPostSubmitBtn;

	@FindBy(xpath = "//*[@id=\"addCommentPanelId\"]")
	private WebElement addCommentPanel;

	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement swalButton;

	@FindBy(xpath = "//*[@id=\"postItem\"]")
	private List<WebElement> posts;

	@FindBy(xpath = "//*[@id=\"commentItemId\"]")
	private List<WebElement> comments;

	public OfferDetailsAdminPage() {
		super();
	}

	public OfferDetailsAdminPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public List<WebElement> getComments() {
		return comments;
	}

	public WebElement getAddPostSubmitBtn() {
		return addPostSubmitBtn;
	}

	public void setAddPostSubmitBtn(WebElement addPostSubmitBtn) {
		this.addPostSubmitBtn = addPostSubmitBtn;
	}

	public void setComments(List<WebElement> comments) {
		this.comments = comments;
	}

	public WebElement getSwalButton() {
		return swalButton;
	}

	public WebElement getAddCommentPanel() {
		return addCommentPanel;
	}

	public void setAddCommentPanel(WebElement addCommentPanel) {
		this.addCommentPanel = addCommentPanel;
	}

	public List<WebElement> getPosts() {
		return posts;
	}

	public void setPosts(List<WebElement> posts) {
		this.posts = posts;
	}

	public void setTitle(WebElement title) {
		this.title = title;
	}

	public void setContent(WebElement content) {
		this.content = content;
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

	public void setDeleteCommentButton(WebElement deleteCommentButton) {
		this.deleteCommentButton = deleteCommentButton;
	}

	public void setAddPostsButton(WebElement addPostsButton) {
		this.addPostsButton = addPostsButton;
	}

	public void setAddNewPostButton(WebElement addNewPostButton) {
		this.addNewPostButton = addNewPostButton;
	}

	public void setSwalButton(WebElement swalButton) {
		this.swalButton = swalButton;
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
		(new WebDriverWait(driver, 10))
				.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addCommentPanelId")));
	}

	public void ensureIsLoadedPage() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.id("offerDetailsDiv")));
	}

	public void ensureIsDisplayedSwal() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.className("swal2-popup")));
	}

	public boolean isSwalVisible() {
		return this.swalAlert.isDisplayed();
	}
}
