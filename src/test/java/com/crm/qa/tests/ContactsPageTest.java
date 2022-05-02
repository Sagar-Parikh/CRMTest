package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;


public class ContactsPageTest extends TestBase {

	HomePage homePage;
	LoginPage loginPage;
	ContactsPage contactsPage;
	String sheetName = "contacts";

	public ContactsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
		contactsPage = homePage.clickOnContactsLink();
	}

	@AfterMethod
	public void tearDown() {
		end();
	}

	@Test(priority = 1)
	public void verifyContactsLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactLabel(), "Contacts Label missing on page");
	}

	@Test(priority = 2)
	public void selectContactsTest() {
		Assert.assertTrue(contactsPage.selectContact("ui ui"));
	}

	@Test(priority = 3)
	public void selectMultipleContactTest() {
		Assert.assertTrue(contactsPage.selectContact("ui ui"));
		Assert.assertTrue(contactsPage.selectContact("test2 test2"));
	}
	
	@Test(priority = 4, dataProvider = "getCRMTestData")
	public void validateCreateNewContact(String firstName, String lastName, String status) {
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(firstName, lastName, status);
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object[][] data = TestUtil.getTestData(sheetName);
		return data;
	}

}
