package com.ftn.kts_nvt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApprovingCommentsPage {

	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"commentsDiv\"]/child::*")
	private WebElement commentsDiv;

	@FindBy(xpath = "//*[@id=\"noCommentsDiv\"]")
	private WebElement noCommentsDiv;

	@FindBy(xpath = "//*[@id=\"mat-expansion-panel-header-0\"]")
	private WebElement expandComment;

	@FindBy(xpath = "//*[@id=\"approveBtn\"]")
	private WebElement approveBtn;

	@FindBy(xpath = "//*[@id=\"rejectBtn\"]")
	private WebElement rejectBtn;

	@FindBy(xpath = "//*[contains(@class, 'swal2-icon-success')]")
	private WebElement swalSuccess;

	@FindBy(xpath = "//*[@id=\"swal2-content\"]")
	private WebElement swalSuccessContent;

	public ApprovingCommentsPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public ApprovingCommentsPage() {
		super();
	}

	public WebElement getCommentsDiv() {
		return commentsDiv;
	}

	public void setCommentsDiv(WebElement commentsDiv) {
		this.commentsDiv = commentsDiv;
	}

	public WebElement getNoCommentsDiv() {
		return noCommentsDiv;
	}

	public void setNoCommentsDiv(WebElement noCommentsDiv) {
		this.noCommentsDiv = noCommentsDiv;
	}

	public WebElement getExpandComment() {
		return expandComment;
	}

	public void setExpandComment(WebElement expandComment) {
		this.expandComment = expandComment;
	}

	public WebElement getApproveBtn() {
		return approveBtn;
	}

	public void setApproveBtn(WebElement approveBtn) {
		this.approveBtn = approveBtn;
	}

	public WebElement getRejectBtn() {
		return rejectBtn;
	}

	public void setRejectBtn(WebElement rejectBtn) {
		this.rejectBtn = rejectBtn;
	}

	public WebElement getSwalSuccess() {
		return swalSuccess;
	}

	public void setSwalSuccess(WebElement swalSuccess) {
		this.swalSuccess = swalSuccess;
	}

	public WebElement getSwalSuccessContent() {
		return swalSuccessContent;
	}

	public void setSwalSuccessContent(WebElement swalSuccessContent) {
		this.swalSuccessContent = swalSuccessContent;
	}

	public void ensureCommentListIsVisible() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("commentsDiv")));
	}
	
	public void ensureIsPageDisplayed() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("commentsDiv")));
	}
	
	public void ensureNoCommentsIsNotDisplayed() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("noCommentsDiv")));
	}
}
