package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class HomePage extends TestBase {

	TestUtil testUtil;
	
	@FindBy(xpath = "//span[contains(text(),'Sagar Parikh')]")
	WebElement userNameLabel;

	@FindBy(xpath = "//span[contains(text(),'Contacts')]")
	WebElement contactsLink;

	@FindBy(xpath = "//span[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//span[contains(text(),'Tasks')]")
	WebElement tasksLink; 
	
	@FindBy(xpath = "//span[text()='Contacts']//parent::a//following-sibling::button//child::i")
	WebElement newContactLink;

	// Initializing the Page Objects;
	public HomePage() {
		PageFactory.initElements(driver, this);
		testUtil = new TestUtil();
	}
	
	public boolean verifyCorrectUserName() {
		return userNameLabel.isDisplayed();
	}
	
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public ContactsPage clickOnContactsLink() {
		testUtil.javaScripExecutorClick(contactsLink);
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage(); 
	}
	
	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		return new TasksPage();
	}
	
	public void clickOnNewContactLink() {
		testUtil.javaScripExecutorClick(newContactLink);
	}
	

}
