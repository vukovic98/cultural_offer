package com.ftn.kts_nvt.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubscribedItemsPage {

	private WebDriver driver;

	@FindBy(xpath = "//table/tbody")
	private WebElement tableBody;

	@FindBy(xpath = "//table/tbody/tr")
	private List<WebElement> tableRows;

	@FindBy(xpath = "//table/tbody/tr[td//text()[contains(., 'Sonsing')]]/td[5]/button")
	private WebElement unsubscribeButton;

	@FindBy(xpath = "//table/tbody/tr[td//text()[contains(., 'Sonsing')]]/td[3]")
	private WebElement offerTitle;

	@FindBy(xpath = "//div[contains(@class, 'swal2-icon-success')]")
	private WebElement swalSuccess;

	@FindBy(xpath = "//button[contains(@class, 'swal2-confirm')]")
	private WebElement swalBtn;

	public SubscribedItemsPage() {
		super();
	}

	public SubscribedItemsPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebElement getTableBody() {
		return tableBody;
	}

	public void setTableBody(WebElement tableBody) {
		this.tableBody = tableBody;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getSwalSuccess() {
		return swalSuccess;
	}

	public void setSwalSuccess(WebElement swalSuccess) {
		this.swalSuccess = swalSuccess;
	}

	public WebElement getSwalBtn() {
		return swalBtn;
	}

	public void setSwalBtn(WebElement swalBtn) {
		this.swalBtn = swalBtn;
	}

	public WebElement getUnsubscribeButton() {
		return unsubscribeButton;
	}

	public void setUnsubscribeButton(WebElement unsubscribeButton) {
		this.unsubscribeButton = unsubscribeButton;
	}

	public WebElement getOfferTitle() {
		return offerTitle;
	}

	public void setOfferTitle(WebElement offerTitle) {
		this.offerTitle = offerTitle;
	}

	public List<WebElement> getTableRows() {
		return tableRows;
	}

	public void setTableRows(List<WebElement> tableRows) {
		this.tableRows = tableRows;
	}

	public void ensureTableIsVisible() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.tagName("tbody")));
	}

	public void ensureIsPageDisplayed() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("subscribedItemsDiv")));
	}
}
