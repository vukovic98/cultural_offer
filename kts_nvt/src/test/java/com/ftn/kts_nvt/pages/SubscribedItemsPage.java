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

	public List<WebElement> getTableRows() {
		return tableRows;
	}

	public void setTableRows(List<WebElement> tableRows) {
		this.tableRows = tableRows;
	}

	public void ensureTableIsVisible() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.tagName("tbody")));
	}
}
