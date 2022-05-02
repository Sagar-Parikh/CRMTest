package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class ContactsPage extends TestBase {

	TestUtil testUtil;

	@FindBy(xpath = "//div[text()='Contacts']")
	WebElement contactsLabel;

	@FindBy(name = "first_name")
	WebElement firstName;
	
	@FindBy(name = "last_name")
	WebElement lastName;
	
	@FindBy(xpath = "//div[@role = 'listbox' and @name='status']")
	WebElement status;
	
	@FindBy(xpath = "//button[@class='ui linkedin button']")
	WebElement saveButton;

	// Initializing the Page Objects;
	public ContactsPage() {
		PageFactory.initElements(driver, this);
		testUtil = new TestUtil();
	}

	public boolean verifyContactLabel() {
		return contactsLabel.isDisplayed();
	}

	public boolean selectContact(String name) {
		WebElement element = driver.findElement(
				By.xpath("//a[text()='" + name + "']//parent::td//preceding-sibling::td//child::div/input"));
		testUtil.javaScripExecutorClick(element);
		return element.isSelected();
	}

	public void createNewContact(String fName, String lName, String s) {
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		status.click();
		WebElement sel_status = status.findElement(By.xpath(".//span[text()='"+s+"']"));
		testUtil.javaScripExecutorClick(sel_status);
		saveButton.click();
		
	}

}
