package com.ftn.kts_nvt.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageRegisteredUser {

	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"homeDiv\"]")
	private WebElement homeDiv;

	@FindBy(xpath = "//*[@id=\"navbarDiv\"]")
	private WebElement navbar;

	@FindBy(xpath = "//*[@id=\"subscribedItemsLink\"]")
	private WebElement subscribedItemsLink;

	@FindBy(xpath = "//*[@id=\"profileLink\"]")
	private WebElement profileLink;

	@FindBy(xpath = "//*[@id=\"changePasswordLink\"]")
	private WebElement changePasswordLink;

	@FindBy(xpath = "//*[@id=\"logoutLink\"]")
	private WebElement logoutLink;

	@FindBy(xpath = "//*[@id=\"loginBtn\"]")
	private WebElement loginBtn;

	@FindBy(xpath = "//*[@id=\"userBtn\"]")
	private WebElement userMenuBtn;

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

	@FindBy(xpath = "//div[contains(@class, 'mat-menu-content')]")
	private WebElement userMenu;

	@FindBy(xpath = "//mat-slide-toggle[@id='mat-slide-toggle-5']/label/div/div/div")
	private WebElement subscribeToggle;

	@FindBy(xpath = "//mat-slide-toggle[@id='mat-slide-toggle-1']/label/div/div/div")
	private WebElement sonsingSubscribeToggle;

	@FindBy(xpath = "//div[contains(@class, 'swal2-icon-success')]")
	private WebElement swalSuccess;

	@FindBy(xpath = "//button[contains(@class, 'swal2-confirm')]")
	private WebElement swalBtn;

	public HomePageRegisteredUser() {
		super();
	}

	public HomePageRegisteredUser(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getSonsingSubscribeToggle() {
		return sonsingSubscribeToggle;
	}

	public void setSonsingSubscribeToggle(WebElement sonsingSubscribeToggle) {
		this.sonsingSubscribeToggle = sonsingSubscribeToggle;
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

	public WebElement getSubscribeToggle() {
		return subscribeToggle;
	}

	public void setSubscribeToggle(WebElement subscribeToggle) {
		this.subscribeToggle = subscribeToggle;
	}

	public WebElement getProfileLink() {
		return profileLink;
	}

	public void setProfileLink(WebElement profileLink) {
		this.profileLink = profileLink;
	}

	public WebElement getChangePasswordLink() {
		return changePasswordLink;
	}

	public void setChangePasswordLink(WebElement changePasswordLink) {
		this.changePasswordLink = changePasswordLink;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public void setLogoutLink(WebElement logoutLink) {
		this.logoutLink = logoutLink;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public void setLoginBtn(WebElement loginBtn) {
		this.loginBtn = loginBtn;
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

	public WebElement getSubscribedItemsLink() {
		return subscribedItemsLink;
	}

	public void setSubscribedItemsLink(WebElement subscribedItemsLink) {
		this.subscribedItemsLink = subscribedItemsLink;
	}

	public WebElement getUserBtn() {
		return userMenuBtn;
	}

	public void setUserBtn(WebElement userBtn) {
		this.userMenuBtn = userBtn;
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

	public WebElement getUserMenu() {
		return userMenu;
	}

	public void setUserMenu(WebElement userMenu) {
		this.userMenu = userMenu;
	}

	public void ensureIsPageDisplayed() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("homeDiv")));
	}

	public void ensureUserMenuIsDisplayed() {
		(new WebDriverWait(driver, 30))
				.until(ExpectedConditions.elementToBeClickable(By.className("mat-menu-content")));
	}

	public void ensureSwalIsDisplayed() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.className("swal2-popup")));
	}

	public List<WebElement> getTypeOptions() {
		return this.typeSelect.findElements(By.tagName("option"));
	}

	public WebElement getUserMenuBtn() {
		return userMenuBtn;
	}

	public void setUserMenuBtn(WebElement userMenuBtn) {
		this.userMenuBtn = userMenuBtn;
	}

}
