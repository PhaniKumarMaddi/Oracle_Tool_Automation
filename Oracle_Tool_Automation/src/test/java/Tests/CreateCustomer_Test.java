package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Pages.Oracle_CreateCustomer_Page;
import Pages.Oracle_HomePage;
import Pages.Oracle_Receivables_Page;
import Pages.TestInitializer;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class CreateCustomer_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(CreateCustomer_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();
	String transactionNum;

	@Test
	public void oracle_CreateCustomer_Test() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_CreateCustomer_Page oraCc = new Oracle_CreateCustomer_Page();

		waitTime(driver);

		grep.testCreate("Verify Create Customer Test", "Verify Create Customer");
		oraHome.clickHomeButton();
		waitTime1(driver);
		oraHome.clickNavigator();
		oraHome.selectNavigationTab(dataTest.receivableNavTab);
		oraHome.selectSubCategoryInNavigator(dataTest.billingCatg);
		waitTime(driver);
		grep.infoTest("Navigating to Create Customer page from Billing");
		logger.info("Navigating to Create Customer page from Billing");
		waitTime3(driver);
		oraHome.click_Tasks_InPO();
		waitTime(driver);
		oraHome.selectTasks_InTaskPage(dataTest.createCustomerTask);
		grep.infoTest("Navigating Create Customer");
		logger.info("Navigating Create Customer");
		waitTime(driver);
		grep.captureScreenshot("pass", "Inside Create Customer Page", "Inside_CreateCustomer");
		waitTime(driver);
		grep.infoTest("Filling Details");
		logger.info("Filling Details");
		waitTime(driver);
		oraCc.enterCustomerName(dataTest.custname);
		waitTime(driver);
		oraCc.enterAccountDescription(dataTest.accDescription);
		waitTime(driver);
		oraCc.clickAccountAddressSet(dataTest.accAddressSet);
		waitTime(driver);
		oraCc.enterSiteName(dataTest.custname);
		waitTime(driver);
		oraCc.clickState(dataTest.arState);
		waitTime(driver);
		oraCc.clickAddressPurpose_AddRow();
		waitTime(driver);
		oraCc.selectPurpose_inAddressPurpose(dataTest.billTo_purpose);
		waitTime(driver);
		oraCc.clickAddressPurpose_AddRow();
		waitTime(driver);
		oraCc.selectPurpose_inAddressPurpose(dataTest.shipTo_Purpose);
		waitTime3(driver);
		oraCc.clickBillToSite_AddressPurpose(dataTest.arState);
		waitTime(driver);
		oraCc.clickAddressPurpose_SetPrimary();
		waitTime(driver);
		grep.captureScreenshot("pass", "After Filling Create Customer Page", "Saving_CreateCustomer");
		waitTime(driver);
		oraCc.clickSaveAndClose_Customer_Btn();
		waitTime2(driver);
		oraCc.verifyCustomerPresent(dataTest.custname);
		waitTime(driver);
		grep.captureScreenshot("pass", "Verify Customer in Manage Customers page", "VerifyCustomer");

	}
}
