package com.ftn.kts_nvt.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageAdminPage {

	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"homeDiv\"]")
	private WebElement homeDiv;

	@FindBy(xpath = "//*[@id=\"navbarDiv\"]")
	private WebElement navbar;

	@FindBy(xpath = "//*[@id=\"profileLink\"]")
	private WebElement profileLink;

	@FindBy(xpath = "//*[@id=\"changePasswordLink\"]")
	private WebElement changePasswordLink;

	@FindBy(xpath = "//*[@id=\"logoutLink\"]")
	private WebElement logoutLink;

	@FindBy(xpath = "//*[@id=\"addOfferLink\"]")
	private WebElement addOfferLink;

	@FindBy(xpath = "//*[@id=\"categoryLink\"]")
	private WebElement categoryLink;

	@FindBy(xpath = "//*[@id=\"typeLink\"]")
	private WebElement typeLink;

	@FindBy(xpath = "//*[@id=\"approvingCommentsLink\"]")
	private WebElement approvingCommentsLink;

	@FindBy(xpath = "//*[@id=\"loginBtn\"]")
	private WebElement loginBtn;

	@FindBy(xpath = "//*[@id=\"userBtn\"]")
	private WebElement userMenuBtn;

	@FindBy(xpath = "//*[@id=\"editSaveBtn\"]")
	private WebElement editSaveBtn;

	@FindBy(xpath = "//*[@id=\"editCloseBtn\"]")
	private WebElement editCloseBtn;

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

	@FindBy(xpath = "//input[@id=\"name\"]")
	private WebElement editNameField;

	@FindBy(xpath = "//*[@id=\"offersListDiv\"]/child::*")
	private List<WebElement> offers;

	@FindBy(xpath = "//div[contains(@class, 'mat-menu-content')]")
	private WebElement userMenu;

	@FindBy(xpath = "//div[contains(@class, 'swal2-icon-success')]")
	private WebElement swalSuccess;

	@FindBy(xpath = "//button[contains(@class, 'swal2-confirm')]")
	private WebElement swalBtn;

	@FindBy(xpath = "//div[@id='offersListDiv']/app-cultural-offer-item/div/div/div[2]/div[2]/button/span/mat-icon")
	private WebElement editOfferBtn;

	@FindBy(xpath = "//div[@id='offersListDiv']/app-cultural-offer-item[8]/div/div/div[2]/div[1]/button/span/mat-icon")
	private WebElement deleteOfferBtn;

	@FindBy(xpath = "//div[@id='offersListDiv']/app-cultural-offer-item/div/div/div[1]/div/div/h3")
	private WebElement offerTitle;

	@FindBy(xpath = "//div[@id='offersListDiv']/app-cultural-offer-item[8]/div/div/div[1]/div/div/h3")
	private WebElement deleteOfferTitle;
	
	@FindBy(xpath = "//*[contains(concat(' ', @class, ' '), ' leaflet-marker-icon ')]")
	private List<WebElement> markers;
	
	@FindBy(xpath = "//*[contains(concat(' ', @class, ' '), ' leaflet-marker-icon ')]")
	private WebElement marker;
	
	@FindBy(xpath = "//a[contains(text(), 'Sonsing')]")
	private WebElement markerLink;

	public HomePageAdminPage() {
		super();
	}

	public HomePageAdminPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebElement getHomeDiv() {
		return homeDiv;
	}

	public void setHomeDiv(WebElement homeDiv) {
		this.homeDiv = homeDiv;
	}

	public WebElement getDeleteOfferBtn() {
		return deleteOfferBtn;
	}

	public void setDeleteOfferBtn(WebElement deleteOfferBtn) {
		this.deleteOfferBtn = deleteOfferBtn;
	}

	public WebElement getDeleteOfferTitle() {
		return deleteOfferTitle;
	}

	public void setDeleteOfferTitle(WebElement deleteOfferTitle) {
		this.deleteOfferTitle = deleteOfferTitle;
	}

	public WebElement getEditOfferBtn() {
		return editOfferBtn;
	}

	public void setEditOfferBtn(WebElement editOfferBtn) {
		this.editOfferBtn = editOfferBtn;
	}

	public WebElement getEditNameField() {
		return editNameField;
	}

	public void setEditNameField(WebElement editNameField) {
		this.editNameField = editNameField;
	}

	public WebElement getEditSaveBtn() {
		return editSaveBtn;
	}

	public void setEditSaveBtn(WebElement editSaveBtn) {
		this.editSaveBtn = editSaveBtn;
	}

	public WebElement getEditCloseBtn() {
		return editCloseBtn;
	}

	public void setEditCloseBtn(WebElement editCloseBtn) {
		this.editCloseBtn = editCloseBtn;
	}

	public WebElement getOfferTitle() {
		return offerTitle;
	}

	public void setOfferTitle(WebElement offerTitle) {
		this.offerTitle = offerTitle;
	}

	public WebElement getAddOfferLink() {
		return addOfferLink;
	}

	public void setAddOfferLink(WebElement addOfferLink) {
		this.addOfferLink = addOfferLink;
	}

	public WebElement getCategoryLink() {
		return categoryLink;
	}

	public void setCategoryLink(WebElement categoryLink) {
		this.categoryLink = categoryLink;
	}

	public WebElement getTypeLink() {
		return typeLink;
	}

	public void setTypeLink(WebElement typeLink) {
		this.typeLink = typeLink;
	}

	public WebElement getApprovingCommentsLink() {
		return approvingCommentsLink;
	}

	public void setApprovingCommentsLink(WebElement approvingCommentsLink) {
		this.approvingCommentsLink = approvingCommentsLink;
	}

	public WebElement getNavbar() {
		return navbar;
	}

	public void setNavbar(WebElement navbar) {
		this.navbar = navbar;
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

	public WebElement getUserMenuBtn() {
		return userMenuBtn;
	}

	public void setUserMenuBtn(WebElement userMenuBtn) {
		this.userMenuBtn = userMenuBtn;
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

	public WebElement getNoOffersDiv() {
		return noOffersDiv;
	}

	public void setNoOffersDiv(WebElement noOffersDiv) {
		this.noOffersDiv = noOffersDiv;
	}

	public List<WebElement> getOffers() {
		return offers;
	}

	public void setOffers(List<WebElement> offers) {
		this.offers = offers;
	}

	public WebElement getUserMenu() {
		return userMenu;
	}

	public void setUserMenu(WebElement userMenu) {
		this.userMenu = userMenu;
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
	
	public void ensureIsMarkerLinkDisplayed() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Sonsing')]")));
	}
	
	public void ensurePageisLoaded() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("offersListDiv")));
	}
	
	public void ensureFilterisLoaded() {
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(By.id("filterDiv")));
	}

	public List<WebElement> getTypeOptions() {
		return this.typeSelect.findElements(By.className("ng-option"));
	}

	public WebElement getMarker() {
		return marker;
	}

	public WebElement getMarkerLink() {
		return markerLink;
	}

	public List<WebElement> getMarkers() {
		return markers;
	}
	
	
}
