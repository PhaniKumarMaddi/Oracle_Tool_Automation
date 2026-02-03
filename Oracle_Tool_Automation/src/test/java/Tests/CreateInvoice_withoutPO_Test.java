package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Pages.Oracle_HomePage;
import Pages.Oracle_InvoicePage;
import Pages.TestInitializer;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class CreateInvoice_withoutPO_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(CreateInvoice_withoutPO_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();
	String retrieveDC_id;

	@Test
	public void oracle_InvoiceCreation_WithoutPO() throws Exception {
		waitTime(driver);
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
//		oraHome.selectNavigationTab("Payables");
		oraHome.selectNavigationTab(dataTest.payableNavTab);
		grep.captureScreenshot("pass", "Expanding Payables in Navigator ", "Expand_PayablesNavigation");
		waitTime(driver);
//		oraHome.selectSubCategoryInNavigator("Invoices");
		oraHome.selectSubCategoryInNavigator(dataTest.invoiceCatg);
		waitTime1(driver);
		grep.infoTest("Click on Tasks for Create Invoice");
		logger.info("Click on Tasks for Create Invoice");
		waitTime(driver);
		oraInv.validateInvoicePageTitle();
		waitTime(driver);

		waitTime60(driver);
		waitTime60(driver);
		waitTime60(driver);

//		oraHome.click_Tasks_InPO();
//		waitTime1(driver);
////		oraHome.selectTasks_InTaskPage("Create Invoice");
//		oraHome.selectTasks_InTaskPage(dataTest.creteInvoiceBtn);
//		waitTime2(driver);
//		oraInv.validateCreateInvoicePageTitle();
//		grep.captureScreenshot("pass", "Inside Create Invoice Page", "CreateInvoicePage");
//		waitTime(driver);
//
//		grep.testCreate("Filling the Invoice Details Functionality Test", "Filling the Invoice Details");
//		waitTime(driver);
//
//		grep.infoTest("Filling the Invoice Details");
//		logger.info("Filling the Invoice Details");
//		waitTime(driver);
////		oraInv.searchAndSelectBusinessUnit("CRITICAL RIVER BU");
////		waitTime2(driver);
////		oraInv.searchAndSelectSupplier("CR Applied Material");
////		waitTime(driver);
////		oraInv.enterInvoiceNumber("CR_MP_Test_008");
////		waitTime(driver);
////		oraInv.enterInvoiceAmount("USD", "2000");
//
//		oraInv.searchAndSelectBusinessUnit(dataTest.selectBU);
//		waitTime2(driver);
//		oraInv.searchAndSelectSupplier(dataTest.selectSupplier);
//		waitTime(driver);
//		oraInv.enterInvoiceNumber(dataTest.invoiceNum);
//		waitTime(driver);
//		oraInv.enterInvoiceAmount("USD", dataTest.invoiceAmt);
//		waitTime(driver);
//
//		oraInv.enterInvoiceDescription("Test Supplier Invoices for Expenses");
//
//		waitTime(driver);
////		oraInv.searchAndSelectPaymentTerms("IMMEDIATE");
////		waitTime(driver);
////		oraInv.enterInvoiceReceiveDate("03", "01", "2026");
//
//		oraInv.searchAndSelectPaymentTerms(dataTest.paymentTerms);
//		waitTime(driver);
//		oraInv.enterInvoiceReceiveDate(dataTest.date, dataTest.month, dataTest.year);
//
//		waitTime(driver);
//		grep.infoTest("Enter Details in Lines Section");
//		logger.info("Enter Details in Lines Section");
//		waitTime(driver);
//		oraInv.expandLinesSection();
//		waitTime2(driver);
////		oraInv.enterAmountInLines("1500");
//		oraInv.enterAmountInLines(dataTest.invoiceAmt);
//		waitTime(driver);
////		oraInv.searchAndSelectDistributionCombination("101", "52110");
//		oraInv.searchAndSelectDistributionCombination(dataTest.company_DC, dataTest.acc_inDC);
//		waitTime(driver);
//		retrieveDC_id = oraInv.retrieveDistributionCombinationID();
//		grep.infoTest("Retrieving Distibution Combinatio ID: " + retrieveDC_id);
//		logger.info("Retrieving Distibution Combinatio ID: " + retrieveDC_id);
//
//		grep.captureScreenshot("pass", "After filling Invoice fields test", "afterFillingInvoiceFields");
//		waitTime(driver);
//		grep.infoTest("Saving and Validating Invoice");
//		logger.info("Saving and Validating Invoice");
//
//		oraInv.clickSaveInvoiceBtn();
//		waitTime1(driver);
//		oraInv.clickContinueWarnBtn();
//		waitTime1(driver);
//		oraInv.clickInvoiceActionAndValidateBtn("Validate");
//		waitTime3(driver);
//
//		String status = oraInv.getInvoiceValidation();
//		logger.info("Current Invoice Status: " + status);
//
//		// 1. If not validated at all, trigger the validation action
//		if (status.equalsIgnoreCase("Not validated")) {
//			oraInv.clickInvoiceActionAndValidateBtn("Validate");
//			status = oraInv.getInvoiceValidation(); // Refresh status
//		}
//
//		// 2. If it needs revalidation (either initially or after the first attempt)
//		if (status.equals("Needs revalidation")) {
//			processRevalidationFlow();
//			status = oraInv.getInvoiceValidation(); // Refresh status after fix
//		}
//
//		// 3. Final verification and logging
//		if (status.equals("Validated")) {
//			grep.infoTest("Validated");
//			logger.info("Validated");
//			grep.captureScreenshot("pass", "Invoice Validated  test", "InvoiceValidated");
//		} else {
//			grep.failTest("Invoice status is currently: " + status);
//			logger.warn("Invoice status is currently: " + status);
//		}

		waitTime2(driver);
		grep.testCreate("Accounting the Validated Invoice Test", "Accounting the Validated Invoice");
		waitTime(driver);
		grep.infoTest("Clicking on Post to ledger");
		logger.info("Clicking on Post to ledger");
		waitTime(driver);
		oraInv.clickInvoiceActionAndValidateBtn("Post to Ledger");
		waitTime(driver);
		oraInv.validateAccountingConfirmationPopup();
		waitTime(driver);
		grep.captureScreenshot("pass", "Post to Ledger Accounting confirrmation popup", "accountingConfirmationPopup");
		oraInv.clickViewAccountingBtn();
		waitTime5(driver);
		grep.infoTest("Validating the Accounting Lines");
		logger.info("Validating the Accounting Lines");
		waitTime(driver);

		grep.captureScreenshot("pass", "Validating the Accounting Lines Popup test", "accountingLinesPopup");

		waitTime(driver);
		oraInv.validateAccountingLinesHeader(dataTest.invoiceNum);
		oraInv.getAccountCombination(retrieveDC_id);
		oraInv.verifyAccountingAmounts(dataTest.invoiceAmt);
		oraInv.clickDoneAccountingBtn();

		waitTime2(driver);

		grep.testCreate("Payment for invoice using Manage Installments Test",
				"Payment for invoice using Manage Installments");
		waitTime(driver);
		oraInv.clickInvoiceActionAndValidateBtn("Manage Installments");
		waitTime(driver);
		grep.infoTest("Entering Payment reason in manage installments popup");
		logger.info("Entering Payment reason in manage installments popup");
		waitTime(driver);

		oraInv.enterPaymentReasonComment(dataTest.paymentReasonDesc);
		waitTime(driver);
		grep.captureScreenshot("pass", "Entering Payment reason in manage installments popup",
				"paymentReason_InManageInstallmentsPopup");

		oraInv.clickSaveAndClose_Payment_Btn();
		oraInv.clickInvoiceActionAndValidateBtn("Pay in Full");
		waitTime(driver);

		grep.infoTest("Entering Payment details in Pay in Full popup");
		logger.info("Entering Payment details in Pay in Full popup");
		waitTime(driver);
		

		grep.captureScreenshot("pass", "Entering Payment in Pay in full popup",
				"paymentReason_InManageInstallmentsPopup");

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
}
