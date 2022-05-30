 package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;

public class HomePageTest extends TestBase {

	HomePage homePage;
	LoginPage loginPage;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	TasksPage tasksPage;
	
	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("email"), prop.getProperty("password"));

	}

	@AfterMethod
	public void tearDown() {
		end();
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "Cogmento CRM", "HomePage title not matched");
	}

	@Test(priority = 2)
	public void verifyUserNameTest() {
		Assert.assertTrue(homePage.verifyCorrectUserName(), "UserNamenot displayed");
	}
	
	@Test(priority = 3)
	public void verifyContactsLink() {
		contactsPage = homePage.clickOnContactsLink();
		
	}
	
	@Test(priority = 4)
	public void verifyDealsLink() {
		dealsPage = homePage.clickOnDealsLink();
		
	}
	
	@Test(priority = 5)
	public void verifyTasksLink() {
		tasksPage = homePage.clickOnTasksLink();
		
	}
	
	
	
}
