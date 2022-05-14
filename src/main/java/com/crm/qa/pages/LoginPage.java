package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	// PAge Factory - Object Repository
	@FindBy(css = "#preloader")
	WebElement preloader;

	@FindBy(partialLinkText = "Login")
	WebElement loginLink;

	@FindBy(name = "email")
	WebElement email;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//div[contains(text(),'Login')]")
	WebElement loginButton;

	@FindBy(linkText = "Sign Up")
	WebElement SignUpButton;

	@FindBy(xpath = "//a[@class='navbar-brand']//img[@class='img-responsive']")
	WebElement crmLogo;

	// Initializing the Page Objects;
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean validateCrmLogoImage() {
		return crmLogo.isDisplayed();
	}

	public HomePage login(String e, String pwd) {
		wait.until(ExpectedConditions.invisibilityOf(preloader));
		loginLink.click();
		
		email.sendKeys(e);
		password.sendKeys(pwd);
		loginButton.click();

		return new HomePage();
	}

}
