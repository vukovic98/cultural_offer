package com.ftn.kts_nvt.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddTypePage {

	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"name\"]")
	private WebElement typeName;
	
	@FindBy(xpath = "//*[@id=\"categorySelect\"]")
	private WebElement categorySelect;
	
	@FindBy(xpath = "//*[@id=\"submit\"]")
	private WebElement submitButton;

	@FindBy(xpath = "//*[@id=\"byName\"]")
	private WebElement byName;

	@FindBy(xpath = "//*[@id=\"search\"]")
	private WebElement searchButton;

	@FindBy(xpath = "//tr[last()]/td[1]")
	private WebElement lastTdTypeName;
	
	@FindBy(xpath = "//tr[last()]/td[2]")
	private WebElement lastTdCategoryName;

	@FindBy(xpath = "//tr[last()]/td[3]/button[1]")
	private WebElement editButton;
	
	@FindBy(xpath = "//tr[last()]/td[3]/button[2]")
	private WebElement deleteButton;
	
	@FindBy(xpath = "//div[contains(@class, 'swal2-popup')]")
	private WebElement swalAlert;
	
	@FindBy(xpath = "//*[@id=\"save\"]")
	private WebElement editSaveButton;
	
	@FindBy(xpath = "(//input[@id='name'])[2]")
	private WebElement editNewTypeNameInput;
			
	@FindBy(xpath = "//tr[last()]/td[1]")
	private WebElement lastRow;
	
	@FindBy(xpath = "//tr[last()]/td[2]")
	private WebElement lastRow2Col;

	@FindBy(xpath = "//tr[last()]/td[3]/button[2]/span/mat-icon")
	private WebElement lastRowDeleteButton;
	
	public AddTypePage() {
		super();
	}

	public AddTypePage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebElement getEditNewTypeNameInput() {
		return editNewTypeNameInput;
	}
	
	public WebElement getLastRow() {
		return lastRow;
	}

	public WebElement getLastRow2Col() {
		return lastRow2Col;
	}
	
	public WebElement getLastRowDeleteButton() {
		return lastRowDeleteButton;
	}
	
	public WebElement getEditSaveButton() {
		return editSaveButton;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getTypeName() {
		return typeName;
	}

	public WebElement getCategorySelect() {
		return categorySelect;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getByName() {
		return byName;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getLastTdTypeName() {
		return lastTdTypeName;
	}

	public WebElement getLastTdCategoryName() {
		return lastTdCategoryName;
	}

	public WebElement getEditButton() {
		return editButton;
	}

	public WebElement getDeleteButton() {
		return deleteButton;
	}

	public void ensureIsDisplayedSwal() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.className("swal2-popup")));
	}

	public boolean isSwalVisible() {
		return this.swalAlert.isDisplayed();
	}
}
