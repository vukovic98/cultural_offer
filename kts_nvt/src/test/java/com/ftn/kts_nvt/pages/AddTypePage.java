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

	@FindBy(xpath = "//button[@id=\"nextPageBtn\"]")
	private WebElement nextPageBtn;

	@FindBy(xpath = "//tr[last()]/td[1]")
	private WebElement lastRow;

	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement swalBtn;

	@FindBy(xpath = "//tr[last()]/td[2]")
	private WebElement lastRow2Col;

	@FindBy(xpath = "//tr[last()]/td[3]/button[2]/span/mat-icon")
	private WebElement lastRowDeleteButton;

	@FindBy(xpath = "//tr[1]/td[3]/button[2]/span/mat-icon")
	private WebElement deleteTypeBtn;

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

	public WebElement getNextPageBtn() {
		return nextPageBtn;
	}

	public void setNextPageBtn(WebElement nextPageBtn) {
		this.nextPageBtn = nextPageBtn;
	}

	public WebElement getSwalAlert() {
		return swalAlert;
	}

	public WebElement getDeleteTypeBtn() {
		return deleteTypeBtn;
	}

	public void setDeleteTypeBtn(WebElement deleteTypeBtn) {
		this.deleteTypeBtn = deleteTypeBtn;
	}

	public void setSwalAlert(WebElement swalAlert) {
		this.swalAlert = swalAlert;
	}

	public WebElement getSwalBtn() {
		return swalBtn;
	}

	public void setSwalBtn(WebElement swalBtn) {
		this.swalBtn = swalBtn;
	}

	public void setTypeName(WebElement typeName) {
		this.typeName = typeName;
	}

	public void setCategorySelect(WebElement categorySelect) {
		this.categorySelect = categorySelect;
	}

	public void setSubmitButton(WebElement submitButton) {
		this.submitButton = submitButton;
	}

	public void setByName(WebElement byName) {
		this.byName = byName;
	}

	public void setSearchButton(WebElement searchButton) {
		this.searchButton = searchButton;
	}

	public void setLastTdTypeName(WebElement lastTdTypeName) {
		this.lastTdTypeName = lastTdTypeName;
	}

	public void setLastTdCategoryName(WebElement lastTdCategoryName) {
		this.lastTdCategoryName = lastTdCategoryName;
	}

	public void setEditButton(WebElement editButton) {
		this.editButton = editButton;
	}

	public void setDeleteButton(WebElement deleteButton) {
		this.deleteButton = deleteButton;
	}

	public void setEditSaveButton(WebElement editSaveButton) {
		this.editSaveButton = editSaveButton;
	}

	public void setEditNewTypeNameInput(WebElement editNewTypeNameInput) {
		this.editNewTypeNameInput = editNewTypeNameInput;
	}

	public void setLastRow(WebElement lastRow) {
		this.lastRow = lastRow;
	}

	public void setLastRow2Col(WebElement lastRow2Col) {
		this.lastRow2Col = lastRow2Col;
	}

	public void setLastRowDeleteButton(WebElement lastRowDeleteButton) {
		this.lastRowDeleteButton = lastRowDeleteButton;
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
