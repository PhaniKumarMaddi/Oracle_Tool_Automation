package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Pages.Oracle_Customer_StandardReport_Page;
import Pages.Oracle_HomePage;
import Pages.Oracle_Receivables_Page;
import Pages.TestInitializer;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class Oracle_AR_Customer_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Oracle_AR_Customer_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();
	String transactionNum;

	@Test
	public void oracle_Customer_Test() throws Exception {
		createCustomerTest();
		customerVerificationTest();
	}

	public void createCustomerTest() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_Customer_StandardReport_Page oraCc = new Oracle_Customer_StandardReport_Page();

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
		oraHome.clickHomeFromPutAway();

	}

	public void customerVerificationTest() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_Receivables_Page oraAr = new Oracle_Receivables_Page();
		waitTime(driver);
		grep.testCreate("Verify Customer Verification Test", "Verify Customer Verification");
		waitTime(driver);
		oraHome.clickNavigator();
//		oraHome.selectNavigationTab(dataTest.receivableNavTab);
		oraHome.selectSubCategoryInNavigator(dataTest.billingCatg);
		waitTime(driver);
		grep.infoTest("Navigating to Billing page from Receivables");
		logger.info("Navigating to Billing page from Receivables");
		waitTime3(driver);
		oraHome.click_Tasks_InPO();
		waitTime(driver);
		oraHome.selectTasks_InTaskPage(dataTest.createTransactionTask);
		waitTime(driver);
		grep.infoTest("Inside Create Transactions Page");
		logger.info("Inside Create Transactions Page");
		waitTime2(driver);
		grep.captureScreenshot("pass", "Navigating Create Transaction for Customer verification",
				"CreateTransactionPage_customerVerification");
		waitTime(driver);
		grep.infoTest("Filling Details in Create Transaction Page");
		logger.info("Filling Details in Create Transaction Page");
		waitTime(driver);
		oraAr.searchAndSelectBusinessUnit(dataTest.selectBU);
		waitTime2(driver);
		oraAr.searchAndSelectTransactionSource(dataTest.transactionSource);
		waitTime2(driver);
		oraAr.searchAndSelectTransactionType(dataTest.transactionType);
		waitTime2(driver);
		oraAr.clickBillToNameSearchBtn();
		oraAr.enterCustomerName(dataTest.custname);
		waitTime2(driver);
		oraAr.clickSearchBtn();
		waitTime(driver);
		oraAr.clickSelectCustomerNameFromList(dataTest.custname);
		grep.infoTest("Verify Newly Created Customer is Visible");
		logger.info("Verify Newly Created Customer is Visible");
		grep.captureScreenshot("pass", "Verify Newly Created Customer is Visible", "NewlyCreatedCustomerVisible");
		waitTime(driver);
		oraAr.clickOk_inBillToNamePopup();
		waitTime2(driver);
		oraAr.searchAndSelectPaymentTerms(dataTest.net_PaymentTerms);
		waitTime2(driver);
		oraAr.enterDescriptionInLines("Newly Created Customer");
		oraAr.enterQuantityInLines(dataTest.quantity);
		oraAr.enterUnitPriceInLines(dataTest.unitPriceAmt);
		waitTime2(driver);
		oraAr.clickSaveBtn_inTransactionPage();
		waitTime5(driver);
		String amount = oraAr.getExtendedAmount();

		grep.captureScreenshot("pass", "Saving Create Transaction in Customer Verification",
				"SaveCreateTransaction_CustomerVerification");
		waitTime(driver);
		oraAr.clickCompleteAndCreateBtn_inTransactionPage("Complete and Review");
		waitTime5(driver);
		grep.infoTest("Clicking on Post to ledger");
		logger.info("Clicking on Post to ledger");
		waitTime(driver);
		oraAr.clickActionAndValidate_inTransactionBtn("Post to Ledger");
		waitTime2(driver);
		grep.captureScreenshot("pass", "Post to Ledger Accounting confirrmation popup",
				"accountingConfirmationPopup_CustomerVerification");
		waitTime(driver);
		oraAr.clickOk_inActionConfirmation();

		waitTime2(driver);
		oraAr.clickSaveAndCloseBtn_inTransactionPage();
		waitTime5(driver);

		grep.captureScreenshot("pass", "Transaction Confirmation after Saving and closing the transaction",
				"confirming_CustomerVerification");

		waitTime(driver);
		transactionNum = oraAr.validateTransactionConfirmationPopup();
		System.out.println(transactionNum);
		waitTime(driver);

		logger.info("Extracted Transaction Number: " + transactionNum);
		grep.infoTest("Extracted Transaction Number: " + transactionNum);
		waitTime(driver);
		oraAr.clickOk_InTransactionConfirmation();
		waitTime(driver);

		oraHome.clickHomeFromPutAway();
		waitTime(driver);

	}
}
