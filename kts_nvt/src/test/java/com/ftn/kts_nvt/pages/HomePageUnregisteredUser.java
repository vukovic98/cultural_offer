package com.ftn.kts_nvt.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageUnregisteredUser {

	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"homeDiv\"]")
	private WebElement homeDiv;

	@FindBy(xpath = "//*[@id=\"navbarDiv\"]")
	private WebElement navbar;

	@FindBy(xpath = "//*[@id=\"loginBtn\"]")
	private WebElement loginBtn;

	@FindBy(xpath = "//*[@id=\"signUpBtn\"]")
	private WebElement signUpBtn;

	@FindBy(xpath = "//*[@id=\"typeSelect\"]")
	private WebElement typeSelect;

	@FindBy(xpath = "//*[@id=\"expression\"]")
	private WebElement nameInput;

	@FindBy(xpath = "//*[@id=\"applyFilter\"]")
	private WebElement applyFilterBtn;

	@FindBy(xpath = "//*[@id=\"offersListDiv\"]")
	private WebElement offersDiv;

	@FindBy(xpath = "//*[@id=\"noOffersDiv\"]")
	private WebElement noOffersDiv;

	@FindBy(xpath = "//*[@id=\"offersListDiv\"]/child::*")
	private List<WebElement> offers;
	
	@FindBy(xpath = "//*[contains(concat(' ', @class, ' '), ' leaflet-marker-icon ')]")
	private List<WebElement> markers;
	
	@FindBy(xpath = "//*[contains(concat(' ', @class, ' '), ' leaflet-marker-icon ')]")
	private WebElement marker;
	
	@FindBy(xpath = "//a[contains(text(), 'Sonsing')]")
	private WebElement markerLink;
	
	public HomePageUnregisteredUser() {
		super();
	}

	public HomePageUnregisteredUser(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getHomeDiv() {
		return homeDiv;
	}

	public void setHomeDiv(WebElement homeDiv) {
		this.homeDiv = homeDiv;
	}

	public WebElement getNavbar() {
		return navbar;
	}

	public void setNavbar(WebElement navbar) {
		this.navbar = navbar;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public void setLoginBtn(WebElement loginBtn) {
		this.loginBtn = loginBtn;
	}

	public WebElement getSignUpBtn() {
		return signUpBtn;
	}

	public void setSignUpBtn(WebElement signUpBtn) {
		this.signUpBtn = signUpBtn;
	}

	public WebElement getTypeSelect() {
		return typeSelect;
	}

	public void setTypeSelect(WebElement typeSelect) {
		this.typeSelect = typeSelect;
	}

	public WebElement getNameInput() {
		return nameInput;
	}

	public void setNameInput(WebElement nameInput) {
		this.nameInput = nameInput;
	}

	public WebElement getApplyFilterBtn() {
		return applyFilterBtn;
	}

	public void setApplyFilterBtn(WebElement applyFilterBtn) {
		this.applyFilterBtn = applyFilterBtn;
	}

	public WebElement getOffersDiv() {
		return offersDiv;
	}

	public void setOffersDiv(WebElement offersDiv) {
		this.offersDiv = offersDiv;
	}

	public List<WebElement> getOffers() {
		return offers;
	}

	public void setOffers(List<WebElement> offers) {
		this.offers = offers;
	}

	public WebElement getNoOffersDiv() {
		return noOffersDiv;
	}

	public void setNoOffersDiv(WebElement noOffersDiv) {
		this.noOffersDiv = noOffersDiv;
	}

	public void ensureIsPageDisplayed() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("homeDiv")));
	}
	
	public List<WebElement> getTypeOptions() {
		return this.typeSelect.findElements(By.tagName("option"));
	}

	public List<WebElement> getMarkers() {
		return markers;
	}

	public void setMarkers(List<WebElement> markers) {
		this.markers = markers;
	}

	public void ensureIsMarkerLinkDisplayed() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Sonsing')]")));
	}
	
	public WebElement getMarker() {
		return marker;
	}

	public WebElement getMarkerLink() {
		return markerLink;
	}

	
	
}
