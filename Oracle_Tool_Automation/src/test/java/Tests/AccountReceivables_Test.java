package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Pages.Oracle_HomePage;
import Pages.Oracle_InvoicePage;
import Pages.Oracle_Receivables_Page;
import Pages.TestInitializer;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class AccountReceivables_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(AccountReceivables_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();
	String retrieveDC_id;

	@Test
	public void oracle_CreateTransaction_Test() throws Exception {

		createTransactionMethod();
		createReceiptMethod();

	}

	public void createTransactionMethod() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_Receivables_Page oraAr = new Oracle_Receivables_Page();
		Oracle_InvoicePage oraInv = new Oracle_InvoicePage();

		waitTime(driver);

		grep.testCreate("Verify Create Transactions Test", "Verify Create Transactions");
		oraHome.clickHomeButton();
		waitTime(driver);
		grep.infoTest("Clicking on Home icon");
		logger.info("Clicking on Home icon");

		waitTime1(driver);
		oraHome.selectTabWithNavigator(dataTest.receivableNavTab);
		grep.captureScreenshot("pass", "Expanding Receivables in Navigator ",
				"ExpandingReceivablesNavigator_CreateTransaction");
		waitTime(driver);
		oraHome.selectFromQuickActions(dataTest.createTransactionAction);
		waitTime1(driver);
		grep.infoTest("Navigating Create Transaction");
		logger.info("Navigating Create Transaction");
		waitTime(driver);
		grep.captureScreenshot("pass", "Navigating Create Transaction", "CreateTransactionPage");
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
		oraAr.enterCustomerName(dataTest.customerName);
		waitTime2(driver);
		oraAr.clickSearchBtn();
		waitTime(driver);
		oraAr.clickSelectCustomerNameFromList(dataTest.customerName);
		oraAr.clickOk_inBillToNamePopup();
		waitTime2(driver);
		oraAr.searchAndSelectPaymentTerms(dataTest.net_PaymentTerms);
		waitTime2(driver);
		oraAr.enterDescriptionInLines("Testing");
		oraAr.enterQuantityInLines(dataTest.quantity);
		oraAr.enterUnitPriceInLines(dataTest.unitPriceAmt);
		waitTime2(driver);
		oraAr.clickSaveBtn_inTransactionPage();
		waitTime5(driver);
		String amount = oraAr.getExtendedAmount();

		grep.captureScreenshot("pass", "Saving Create Transaction test", "SaveCreateTransaction");
		waitTime(driver);
		oraAr.clickCompleteAndCreateBtn_inTransactionPage("Complete and Review");
		waitTime5(driver);
		grep.infoTest("Clicking on Post to ledger");
		logger.info("Clicking on Post to ledger");
		waitTime(driver);
		oraAr.clickActionAndValidate_inTransactionBtn("Post to Ledger");
		waitTime(driver);
		oraInv.validateAccountingConfirmationPopup();
		waitTime(driver);
		grep.captureScreenshot("pass", "Post to Ledger Accounting confirrmation popup",
				"accountingConfirmationPopup_CreateTransaction");
		oraInv.clickViewAccountingBtn();
		waitTime10(driver);
		grep.infoTest("Validating the Accounting Lines");
		logger.info("Validating the Accounting Lines");
		waitTime(driver);

		grep.captureScreenshot("pass", "Validating the Accounting Lines Popup test",
				"accountingLinesPopup_CreateTransaction");

		waitTime(driver);
//		oraAr.verifyAccountingAmounts(amount);
		oraInv.clickDoneAccountingBtn();

		waitTime2(driver);
		oraAr.clickSaveAndCloseBtn_inTransactionPage();
		waitTime5(driver);

		grep.captureScreenshot("pass", "Transaction Confirmation after Saving and closing the transaction",
				"confirming_CreateTransaction");

		waitTime(driver);
		String msg = oraAr.validateTransactionConfirmationPopup();
		System.out.println(msg);
		waitTime(driver);

		logger.info("Extracted Transaction Number: " + msg);
		grep.infoTest("Extracted Transaction Number: " + msg);
		waitTime(driver);
		oraAr.clickOk_InTransactionConfirmation();
		waitTime(driver);

		oraHome.clickHomeButton();
	}

	public void createReceiptMethod() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_Receivables_Page oraAr = new Oracle_Receivables_Page();

		grep.testCreate("Verify Create Receipt Test", "Verify Create Receipt");
		waitTime(driver);
		oraHome.clickNavigator();
		oraHome.selectNavigationTab(dataTest.receivableNavTab);
		oraHome.selectSubCategoryInNavigator(dataTest.accReceivableCatg);
		waitTime(driver);
		oraHome.click_Tasks_InPO();
		oraHome.selectTasks_InTaskPage(dataTest.createReceiptTask);
		waitTime(driver);
		
		

	}
}