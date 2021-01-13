package com.ftn.kts_nvt.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditProfilePage {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"firstName\"]")
	private WebElement firstName;
	
	@FindBy(xpath = "//*[@id=\"lasttName\"]")
	private WebElement lastName;
	
	@FindBy(xpath = "//*[@id=\"editProfileBtn\"]")
	private WebElement editProfileBtn;
	
	@FindBy(xpath = "//*[@id=\"cancelEditProfileBtn\"]")
	private WebElement cancelEditProfileBtn;
	
	public EditProfilePage() {
		super();
	}
	
	public EditProfilePage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebElement getFirstName() {
		return firstName;
	}

	public void setFirstName(WebElement firstName) {
		this.firstName = firstName;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public void setLastName(WebElement lastName) {
		this.lastName = lastName;
	}

	public WebElement getEditProfileBtn() {
		return editProfileBtn;
	}

	public void setEditProfileBtn(WebElement editProfileBtn) {
		this.editProfileBtn = editProfileBtn;
	}

	public WebElement getCancelEditProfileBtn() {
		return cancelEditProfileBtn;
	}

	public void setCancelEditProfileBtn(WebElement cancelEditProfileBtn) {
		this.cancelEditProfileBtn = cancelEditProfileBtn;
	}
	

}
