package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.Oracle_BatchPaymentPage;
import Pages.Oracle_HomePage;
import Pages.Oracle_InvoicePage;
import Pages.TestInitializer;
import Utility.ExcelDataProvider;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class Batch_Payment_Electonic_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Batch_Payment_Electonic_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();
	String retrieveDC_id;

	@DataProvider(name = "BatchPaymentTest")
	public Object[][] getData() {
		// Get Excel Test Data passing Excel File Name and Sheet Name
		Object data[][] = ExcelDataProvider.testData("Oracle_TestData", "Batch Payment");
		return data;
	}

//	@Test
//	public void oracle_Batch_Payment_Electronic() throws Exception {

	@Test(dataProvider = "BatchPaymentTest")
	public void oracle_Batch_Payment_Electronic(String invoiceNumVal, String batchPaymentNumVal) throws Exception {

		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_BatchPaymentPage oraBpp = new Oracle_BatchPaymentPage();

//		createInvoice(invoiceNumVal);
		waitTime(driver);

		grep.testCreate("Verify Batch Payment Invoice Test", "Verify Batch Payment Invoice");
		oraHome.clickHomeButton();
		waitTime(driver);
		grep.infoTest("Clicking on Home icon");
		logger.info("Clicking on Home icon");

		grep.captureScreenshot("pass", "Inside Home Page ", "Oracle_HomePage_BatchPayment");
		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab(dataTest.payableNavTab);
		grep.captureScreenshot("pass", "Expanding Payables in Navigator ", "Expand_PayablesNavigation_BatchPayment");
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.paymentsCatg);
		waitTime1(driver);
		grep.infoTest("Click on Tasks for Submit Payment Process Request");
		logger.info("Click on Tasks for Submit Payment Process Request");
		waitTime(driver);

		oraHome.click_Tasks_InPO();
		waitTime1(driver);
		oraHome.selectTasks_InTaskPage(dataTest.submitPaymentProcessRequestBtn);
		waitTime5(driver);
		oraBpp.validateSubmitPaymentProcessPageTitle();
		waitTime(driver);
		grep.infoTest("Entering Batch Number and Batch Template");
		logger.info("Entering Batch Number and Batch Template");

		oraBpp.enterInvoiceNumber_InSubmitPaymentProcessPage(batchPaymentNumVal);
		waitTime3(driver);
		// For electronic flow
		oraBpp.selectBusinessUnitPaymentRadio();
		waitTime2(driver);
		oraBpp.clickBusinessUnitAddRow();
		waitTime2(driver);
		oraBpp.searchAndSelectBusinessUnit(dataTest.selectBU);
		waitTime3(driver);

		oraBpp.selectLegalEntitiesRadio();
		waitTime2(driver);
		oraBpp.clickLegalEntityAddRow();
		waitTime2(driver);
		oraBpp.searchAndSelectLegalEntities(dataTest.selectLegal);
		waitTime3(driver);
		oraBpp.searchAndSelectSupplierOrParty(dataTest.selectSupplier);
		waitTime2(driver);
		grep.captureScreenshot("pass", "Validating Details in Select Criteria Page", "Payment_SelectCriteriaPage");
		waitTime2(driver);

		grep.infoTest("Clicking on Payment and Processing Options Tab");
		logger.info("Clicking on Payment and Processing Options Tab");
		waitTime2(driver);
		oraBpp.clickPaymentAndProcessingOptionTab();
		waitTime2(driver);
		oraBpp.searchAndSelectDisbursementBankAccount(dataTest.bankAccount);
		waitTime1(driver);
		oraBpp.enableApplyCredits();
		waitTime2(driver);
		oraBpp.enableReviewInstallments();
		waitTime2(driver);
		oraBpp.enableCreatePaymentFiles();
		waitTime1(driver);
		grep.captureScreenshot("pass", "Validating Details in Payment and Processing Options Page",
				"Payment_ProcessingOptionsPage");
		waitTime2(driver);
		oraBpp.clickSubmitBtn_inSubmitPaymentProcess();
		waitTime10(driver);
		oraBpp.clickRefreshPaymentProcessBtn();
		waitTime(driver);
		oraBpp.waitForPaymentRecordAndStatus(batchPaymentNumVal, dataTest.pendingInstallReviewStatus, 2);
		waitTime2(driver);

		grep.captureScreenshot("pass", "Inside review Installment Page", "InsideReviewInstallmentPage");

		waitTime2(driver);

		oraBpp.clickSave_inSelectInstallments();
		waitTime(driver);

		oraBpp.clickSubmitBtn_inSubmitPaymentProcess();
		waitTime10(driver);
		oraBpp.clickRefreshPaymentProcessBtn();
		waitTime10(driver);

		oraBpp.waitForPaymentRecordAndStatus(batchPaymentNumVal, dataTest.pendingActionToComplete, 2);
		waitTime2(driver);

		grep.captureScreenshot("pass", "Inside Assign Proposed Payment Page", "InsideReviewProposedPaymentPage");
		waitTime2(driver);
		oraBpp.searchAndSelectPaymentProcess(dataTest.paymentProfile);
		waitTime2(driver);
		grep.infoTest("Clicking on Resume Payment Process Button");
		logger.info("Clicking on Resume Payment Process Button");
		waitTime2(driver);
		oraBpp.clickResumePaymentButton();
//		waitTime(driver);
		waitTime10(driver);
		oraBpp.clickRefreshPaymentProcessBtn();
		waitTime10(driver);

		oraBpp.waitForPaymentRecordAndStatus(batchPaymentNumVal, dataTest.pendingPropsedPaymentReviewStatus, 10);
		waitTime2(driver);
		grep.infoTest("Clicking on Resume Payment Process Button");
		logger.info("Clicking on Resume Payment Process Button");
		waitTime2(driver);
		oraBpp.clickResumePaymentButton();
		waitTime(driver);

		oraBpp.waitForPaymentStatusAndExpand(batchPaymentNumVal, dataTest.waitingForPaymentFileStatus, 2);

		waitTime(driver);
		String paymentReceiptNum = oraBpp.getPaymentProcessRequestNumber(batchPaymentNumVal);
		waitTime2(driver);
		grep.infoTest("Payment receipt Number :" + paymentReceiptNum);
		logger.info("Payment receipt Number :" + paymentReceiptNum);
		waitTime2(driver);

		oraBpp.clickRecentlyCompletedTab();
		waitTime(driver);
		oraBpp.clickRecentlyCompletedRefreshButton();
		waitTime5(driver);
		oraBpp.verifyPaymentNumber_inCompletedTab(batchPaymentNumVal);
		waitTime2(driver);

//		oraHome.clickHomeButton();
		oraHome.clickHomeFromPutAway();
		waitTime2(driver);

		oracle_Query_InvoicePayment(invoiceNumVal);
	}

	public void createInvoice(String invNumVal) throws Exception {

		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_InvoicePage oraInv = new Oracle_InvoicePage();

		grep.testCreate("Verify Navigate to Create Invoice Page Functionality Test", "Navigate to Create Invoice Page");
		oraHome.clickHomeButton();
		waitTime(driver);
		grep.infoTest("Clicking on Home icon");
		logger.info("Clicking on Home icon");

		grep.captureScreenshot("pass", "Inside Home Page ", "Oracle_HomePage_ForInvoice");
		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab(dataTest.payableNavTab);
		grep.captureScreenshot("pass", "Expanding Payables in Navigator ", "Expand_PayablesNavigation");
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.invoiceCatg);
		waitTime1(driver);
		grep.infoTest("Click on Tasks for Create Invoice");
		logger.info("Click on Tasks for Create Invoice");
		waitTime(driver);
		oraInv.validateInvoicePageTitle();
		waitTime(driver);

		oraHome.click_Tasks_InPO();
		waitTime1(driver);
		oraHome.selectTasks_InTaskPage(dataTest.createInvoiceBtn);
		waitTime2(driver);
		waitTime60(driver);
		oraInv.validateCreateInvoicePageTitle();
		grep.captureScreenshot("pass", "Inside Create Invoice Page", "CreateInvoicePage");
		waitTime(driver);

		grep.testCreate("Filling the Invoice Details Functionality Test", "Filling the Invoice Details");
		waitTime(driver);

		grep.infoTest("Filling the Invoice Details");
		logger.info("Filling the Invoice Details");
		waitTime(driver);

		oraInv.searchAndSelectBusinessUnit(dataTest.selectBU);
		waitTime2(driver);
		oraInv.searchAndSelectSupplier(dataTest.selectSupplier);
		waitTime(driver);
		oraInv.enterInvoiceNumber(invNumVal);
		waitTime2(driver);
//		oraInv.enterInvoiceAmount("USD", dataTest.invoiceAmt);
		oraInv.enterInvoiceAmount(dataTest.invoiceAmt);
		waitTime(driver);

		oraInv.enterInvoiceDescription("Test Supplier Invoices for Expenses");

		waitTime(driver);
		oraInv.searchAndSelectPaymentTerms(dataTest.paymentTerms);
		waitTime(driver);
		oraInv.enterInvoiceReceiveDate(dataTest.date, dataTest.month, dataTest.year);

		waitTime(driver);
		grep.infoTest("Enter Details in Lines Section");
		logger.info("Enter Details in Lines Section");
		waitTime(driver);
		oraInv.expandLinesSection();
		waitTime2(driver);
		oraInv.enterAmountInLines(dataTest.invoiceAmt);
		waitTime(driver);
		oraInv.searchAndSelectDistributionCombination(dataTest.company_DC, dataTest.acc_inDC);
		waitTime2(driver);
		retrieveDC_id = oraInv.retrieveDistributionCombinationID();
		grep.infoTest("Retrieving Distibution Combinatio ID: " + retrieveDC_id);
		logger.info("Retrieving Distibution Combinatio ID: " + retrieveDC_id);

		grep.captureScreenshot("pass", "After filling Invoice fields test", "afterFillingInvoiceFields");
		waitTime(driver);
		grep.infoTest("Saving and Validating Invoice");
		logger.info("Saving and Validating Invoice");

		oraInv.clickSaveInvoiceBtn();
		waitTime2(driver);
		oraInv.clickContinueWarnBtn();
		waitTime3(driver);
		oraInv.clickInvoiceActionAndValidateBtn("Validate");
		waitTime3(driver);

		String status = oraInv.getInvoiceValidation();
		logger.info("Current Invoice Status: " + status);

		// 1. If not validated at all, trigger the validation action
		if (status.equalsIgnoreCase("Not validated")) {
			oraInv.clickInvoiceActionAndValidateBtn("Validate");
			status = oraInv.getInvoiceValidation(); // Refresh status
		}

		// 2. If it needs revalidation (either initially or after the first attempt)
		if (status.equalsIgnoreCase("Not validated")) {
			oraInv.clickInvoiceActionAndValidateBtn("Validate");
			status = oraInv.getInvoiceValidation(); // Refresh status
		}

		if (status.equals("Needs revalidation")) {
			processRevalidationFlow();
			status = oraInv.getInvoiceValidation(); // Refresh status after fix
		}

		// 3. Final verification and logging
		if (status.equals("Validated")) {
			grep.infoTest("Validated");
			logger.info("Validated");
			grep.captureScreenshot("pass", "Invoice Validated  test", "InvoiceValidated");
		} else {
			grep.failTest("Invoice status is currently: " + status);
			logger.warn("Invoice status is currently: " + status);
		}

		waitTime(driver);
		grep.testCreate("Payment for invoice using Manage Installments Test",
				"Payment for invoice using Manage Installments");
		waitTime2(driver);
		oraInv.clickInvoiceActionAndValidateBtn("Manage Installments");
		waitTime(driver);
		grep.infoTest("Entering Payment reason in manage installments popup");
		logger.info("Entering Payment reason in manage installments popup");
		waitTime(driver);

		oraInv.enterPaymentReasonComment(dataTest.paymentReasonDesc);
		waitTime(driver);
		oraInv.searchAndSelectPaymentMethod(dataTest.paymentMethod);
		waitTime(driver);
		grep.captureScreenshot("pass", "Entering Payment reason in manage installments popup",
				"paymentReason_InManageInstallmentsPopup_Without_PO");

		oraInv.clickSaveAndClose_Payment_Btn();
		waitTime5(driver);

		grep.testCreate("Accounting the Validated Invoice Test", "Accounting the Validated Invoice");
		waitTime(driver);

		grep.infoTest("Clicking on Post to ledger");
		logger.info("Clicking on Post to ledger");
		waitTime(driver);
		oraInv.clickInvoiceActionAndValidateBtn("Post to Ledger");
		waitTime(driver);
		oraInv.validateAccountingConfirmationPopup();
		waitTime(driver);
		grep.captureScreenshot("pass", "Post to Ledger Accounting confirrmation popup",
				"accountingConfirmationPopup_without_PO");
		oraInv.clickViewAccountingBtn();
		waitTime5(driver);
		grep.infoTest("Validating the Accounting Lines");
		logger.info("Validating the Accounting Lines");
		waitTime(driver);

		grep.captureScreenshot("pass", "Validating the Accounting Lines Popup test", "accountingLinesPopup_withPO");

		waitTime2(driver);
		oraInv.validateAccountingLinesHeader(invNumVal);
		oraInv.clickDoneAccountingBtn();

		waitTime2(driver);

		oraInv.clickHomeFromInvoicePage();
		waitTime(driver);
	}

	private void processRevalidationFlow() throws Exception {
		Oracle_InvoicePage oraInv = new Oracle_InvoicePage();
		waitTime(driver);

		grep.infoTest("Needs revalidation");
		logger.info("Processing revalidation flow...");

		grep.captureScreenshot("pass", "Invoice Need Re-Validation", "InvoiceNeed_Revalidation");

		oraInv.clickNeedReValidation();
		waitTime1(driver);
		oraInv.clickHoldWarningLink();
		waitTime1(driver);
		grep.captureScreenshot("pass", "Clicking on Warning link", "warning_Link_Revalidation");

		oraInv.selectValidatedReleaseName();
		waitTime2(driver);
		oraInv.clickSaveAndCloseBtn();
		waitTime3(driver);

	}

	public void oracle_Query_InvoicePayment(String invNumVal) throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_InvoicePage oraInv = new Oracle_InvoicePage();

		grep.testCreate("Verify Query Invoice Number For Paid Invoice Test",
				"Verify Query Invoice Number For Paid Invoice");
		waitTime(driver);
		grep.infoTest("Clicking on Home icon");
		logger.info("Clicking on Home icon");

		grep.captureScreenshot("pass", "Inside Home Page ", "Oracle_HomePage_Query_Invoice");
		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		grep.captureScreenshot("pass", "Expanding Payables in Navigator ", "Expand_PayablesNavigation_QueryInvoice");
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.invoiceCatg);
		waitTime1(driver);
		grep.infoTest("Click on Tasks for Create Invoice");
		logger.info("Click on Tasks for Create Invoice");
		waitTime(driver);
		oraInv.validateInvoicePageTitle();
		waitTime(driver);

		oraHome.click_Tasks_InPO();
		waitTime1(driver);
		oraHome.selectTasks_InTaskPage(dataTest.manageInvoiceBtn);
		waitTime10(driver);
		oraInv.validateManageInvoicePageTitle();
		waitTime2(driver);

		grep.captureScreenshot("pass", "Inside Manage Invoice Page", "ManageInvoicePage");
		waitTime(driver);

		oraInv.enterInvoiceNumber_InSelectAndAddPopup(invNumVal);
		waitTime2(driver);
		oraHome.clickSearchBtn();
		waitTime2(driver);
		grep.captureScreenshot("pass", "Search Invoice in Query invoice Page", "SearchInvoice_QueryInvoicePage");
		waitTime(driver);

		grep.infoTest("Clicking on Invoice Number");
		logger.info("Clicking on Invoice Number");
		oraInv.selectInvoiceNumber_inSearch(invNumVal);

		waitTime(driver);
		grep.infoTest("Clicking on Payments Tab");
		logger.info("Clicking on Payments Tab");
		oraInv.clickPaymentsTab_QueryInvoice();
		waitTime3(driver);
		grep.captureScreenshot("pass", "Inside Payments Tab", "InsidePaymentsTab_QueryInvoicePage");
		waitTime(driver);

		oraInv.clickPaymentNumber_QueryInvoice();
		waitTime(driver);
		oraInv.validateInvoiceNum_InPaymentPopup(invNumVal);
		oraInv.validateInvoiceStatus_InPaymentPopup();
		waitTime(driver);
		grep.captureScreenshot("pass", "Inside Payments Receipt Popup", "InsidePaymentsReceiptPopup_QueryInvoicePage");

		waitTime2(driver);
		oraInv.clickOk_InPaymentPopup();
		waitTime2(driver);
		oraHome.clickDoneReceiptBtn();

	}

}
