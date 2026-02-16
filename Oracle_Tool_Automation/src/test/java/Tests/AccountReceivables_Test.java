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
	String transactionNum;

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
		waitTime5(driver);
		oraAr.searchAndSelectPaymentTerms(dataTest.net_PaymentTerms);
		waitTime2(driver);
		oraAr.enterDescriptionInLines("Testing 2");
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
		transactionNum = oraAr.validateTransactionConfirmationPopup();
		System.out.println(transactionNum);
		waitTime(driver);

		logger.info("Extracted Transaction Number: " + transactionNum);
		grep.infoTest("Extracted Transaction Number: " + transactionNum);
		waitTime(driver);
		oraAr.clickOk_InTransactionConfirmation();
		waitTime(driver);

		oraHome.clickHomeButton();
	}

	public void createReceiptMethod() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_Receivables_Page oraAr = new Oracle_Receivables_Page();
		Oracle_InvoicePage oraInv = new Oracle_InvoicePage();

		grep.testCreate("Verify Create Receipt Test", "Verify Create Receipt");
		waitTime(driver);
		oraHome.clickNavigator();
		oraHome.selectNavigationTab(dataTest.receivableNavTab);
		oraHome.selectSubCategoryInNavigator(dataTest.accReceivableCatg);
		waitTime(driver);
		grep.infoTest("Navigating to Acoount Receivables");
		logger.info("Navigating to Acoount Receivables");
		waitTime(driver);
		oraHome.click_Tasks_InPO();
		waitTime(driver);
		grep.infoTest("Click on Create Receipt");
		logger.info("Click on Create Receipt");

		oraHome.selectTasks_InTaskPage(dataTest.createReceiptTask);
		waitTime(driver);
		waitTime(driver);
		grep.infoTest("Filling the Details in Receipt Page");
		logger.info("Filling the Details in Receipt Page");

		oraAr.searchAndSelectBusinessUnit(dataTest.selectBU);
		waitTime2(driver);
		oraAr.searchAndSelectReceiptMethod(dataTest.receiptMethod);
		oraAr.enterReceiptNumber(dataTest.receiptNum);
		waitTime2(driver);
		oraAr.enterAmount_inReceipt(dataTest.unitPriceAmt);
		oraAr.searchAndSelectBankName(dataTest.bankName);
		waitTime2(driver);
		oraAr.searchAndSelectBankBranch(dataTest.bankBranch);
		waitTime2(driver);
		oraAr.searchAndSelectBankAccount(dataTest.bankAccount);
		waitTime(driver);
		grep.infoTest("Click on Submit Apply Manually");
		logger.info("Click on Submit Apply Manually");

		oraAr.clickSubmitCreateAnotherBtn_inReceiptPage(dataTest.submitApplyManually);
		waitTime3(driver);
		grep.captureScreenshot("pass", "Submitting the Receipt", "afterSubmittingReceipt");
		waitTime3(driver);
		grep.infoTest("Click on Application tab under Receipt Details Section");
		logger.info("Click on Application tab under Receipt Details Section");
		waitTime(driver);
		oraAr.clickApplicationTab_inReceiptDetails();
		grep.infoTest("Click on Add open Receivables");
		logger.info("Click on Add open Receivables");
		waitTime(driver);
		oraAr.clickAddOpenReceivablesBtn_inReceiptDetails();
		waitTime2(driver);
		grep.captureScreenshot("pass", "Inside Open Receivables popup", "openReceivablesPopup");
		waitTime(driver);
		oraAr.enterTransactionCustomerName_Receipt(dataTest.customerName);
		waitTime2(driver);
		oraAr.scrollToLastVisibleRow();
		waitTime2(driver);
//		oraAr.clickSelectReceiptFromList("101013");
		oraAr.clickSelectReceiptFromList(transactionNum);
		waitTime(driver);
		oraAr.clickDoneBtn_inOpenReceivables();
		waitTime3(driver);
		grep.captureScreenshot("pass", "After Selecting Transaction", "afterSelectingTransaction");
		waitTime(driver);
		oraAr.clickSaveReceiptBtn();
		waitTime(driver);

		grep.infoTest("Clicking on Post to ledger");
		logger.info("Clicking on Post to ledger");
		waitTime(driver);
		oraAr.clickActionAndValidate_inTransactionBtn("Post to Ledger");
		waitTime(driver);
		oraInv.validateAccountingConfirmationPopup();
		waitTime(driver);
		grep.captureScreenshot("pass", "Post to Ledger Accounting confirrmation popup",
				"accountingConfirmationPopup_CreateReceipt");
		oraInv.clickViewAccountingBtn();
		waitTime10(driver);
		grep.infoTest("Validating the Accounting Lines");
		logger.info("Validating the Accounting Lines");
		waitTime(driver);

		grep.captureScreenshot("pass", "Validating the Accounting Lines Popup test",
				"accountingLinesPopup_CreateReceipt");

		waitTime(driver);
//		oraAr.verifyAccountingAmounts(amount);
		oraInv.clickDoneAccountingBtn();
		waitTime5(driver);
		oraInv.clickSaveAndClose_Payment_Btn();
		waitTime(driver);
		oraHome.clickNavigatorFromPutAway();
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.billingCatg);
		waitTime(driver);
		grep.infoTest("Navigating to Billing page from Receivables");
		logger.info("Navigating to Billing page from Receivables");
		waitTime5(driver);
		oraHome.click_Tasks_InPO();
		waitTime(driver);
		oraHome.selectTasks_InTaskPage(dataTest.manageTransactionTask);
		waitTime(driver);
		grep.infoTest("Inside Manage Transactions Page");
		logger.info("Inside Manage Transactions Page");
		waitTime2(driver);
		oraAr.selectTransactionDate();
		waitTime2(driver);
		oraAr.clickBUDropdownButton();
		oraAr.clickSearch_InDropdown();
		waitTime2(driver);
		oraAr.enterBU(dataTest.selectBU);
		waitTime2(driver);
		oraAr.clickSearch_inSelect_Popup();
		oraAr.clickSelect_NameFromList(dataTest.selectBU);
		waitTime(driver);
		oraAr.clickOk_inSelect_Popup();
		waitTime(driver);

		oraAr.clickTransactionSourceDropdownButton();
		oraAr.clickSearch_InDropdown();
		waitTime2(driver);
		oraAr.enterTransactionSourceName_inpopup(dataTest.transactionSource);
		waitTime(driver);
		oraAr.clickSearch_inSelect_Popup();
		oraAr.clickSelect_NameFromList(dataTest.transactionSource);
		waitTime(driver);
		oraAr.clickOk_inSelect_Popup();
		waitTime5(driver);

		oraAr.clickSearchBtn();
		waitTime2(driver);
		grep.captureScreenshot("pass", "Searching transaction based on BU and Source",
				"searchingTransaction_inManageTransaction");

		waitTime(driver);
//		oraAr.clickSelect_TransactionNumber("101013");
		oraAr.clickSelect_TransactionNumber(transactionNum);
		waitTime(driver);

		grep.infoTest("Clicking on View Balance Details");
		logger.info("Clicking on View Balance Details");
		waitTime(driver);
		oraAr.clickActionAndValidate_inTransactionBtn("View Balance Details");
		waitTime2(driver);

		grep.captureScreenshot("pass", "Inside View Balance Details Page",
				"ViewBalanceDetailsPage_inManageTransaction");
		waitTime(driver);
		oraAr.clickDoneBtn_inOpenReceivables();
		waitTime(driver);

		oraAr.clickSaveAndCloseBtn_inTransactionPage();
		waitTime5(driver);

		grep.captureScreenshot("pass", "Transaction Confirmation after Saving and closing the transaction",
				"confirming_ManageTransaction");

		waitTime(driver);
		String msgId = oraAr.validateTransactionConfirmationPopup();
		System.out.println(msgId);
		waitTime(driver);

		logger.info("Extracted Transaction Number: " + msgId);
		grep.infoTest("Extracted Transaction Number: " + msgId);
		waitTime(driver);
		validAssert.equalsAssert(msgId, transactionNum);
		waitTime(driver);
		oraAr.clickOk_InManageTransactionConfirmation();
		waitTime(driver);
		oraAr.clickDoneBtn_inOpenReceivables();
		waitTime(driver);

		oraHome.clickHomeFromPutAway();

	}
}