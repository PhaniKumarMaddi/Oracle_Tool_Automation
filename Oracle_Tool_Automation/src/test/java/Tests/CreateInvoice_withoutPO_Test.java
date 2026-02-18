package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.Oracle_HomePage;
import Pages.Oracle_InvoicePage;
import Pages.TestInitializer;
import Utility.ExcelDataProvider;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class CreateInvoice_withoutPO_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(CreateInvoice_withoutPO_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();
	String retrieveDC_id;

	@DataProvider(name = "InvoiceTest")
	public Object[][] getData() {
		// Get Excel Test Data passing Excel File Name and Sheet Name
		Object data[][] = ExcelDataProvider.testData("Oracle_TestData", "Invoice Without PO");
		return data;
	}

//	@Test
//	public void oracle_InvoiceCreation_WithoutPO() throws Exception {
	@Test(dataProvider = "InvoiceTest")
	public void oracle_InvoiceCreation_WithPO(String invoiceNumVal, String amountVal) throws Exception {

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
		waitTime2(driver);
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
		waitTime5(driver);
		oraInv.searchAndSelectSupplier(dataTest.selectSupplier);
		waitTime(driver);
		oraInv.enterInvoiceNumber(invoiceNumVal);
		waitTime2(driver);
//		oraInv.enterInvoiceAmount("USD", dataTest.invoiceAmt);
		oraInv.enterInvoiceAmount(amountVal);
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
		oraInv.enterAmountInLines(amountVal);
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
		waitTime5(driver);

		String status = oraInv.getInvoiceValidation();
		logger.info("Current Invoice Status: " + status);

		// 1. If not validated at all, trigger the validation action
		if (status.equalsIgnoreCase("Not validated")) {
			oraInv.clickInvoiceActionAndValidateBtn("Validate");
			status = oraInv.getInvoiceValidation(); // Refresh status
		}

		// 2. If it needs revalidation (either initially or after the first attempt)
		if (status.equals("Needs revalidation")) {
			processRevalidationFlow();
			status = oraInv.getInvoiceValidation(); // Refresh status after fix
			waitTime2(driver);
		}

		// 3. Final verification and logging
		if (status.equals("Validated")) {
			grep.infoTest("Validated");
			logger.info("Validated");
			grep.captureScreenshot("pass", "Invoice Validated  test", "InvoiceValidated");
			waitTime2(driver);
		} else {
			grep.failTest("Invoice status is currently: " + status);
			logger.warn("Invoice status is currently: " + status);
		}

		waitTime2(driver);
		grep.testCreate("Payment for invoice using Manage Installments Test",
				"Payment for invoice using Manage Installments");
		waitTime2(driver);
		oraInv.clickInvoiceActionAndValidateBtn("Manage Installments");
		waitTime2(driver);
		grep.infoTest("Entering Payment reason in manage installments popup");
		logger.info("Entering Payment reason in manage installments popup");
		waitTime2(driver);

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

		grep.infoTest("Clicking on Account in Draft");
		logger.info("Clicking on Account in Draft");
		waitTime(driver);
		oraInv.clickInvoiceActionAndValidateBtn("Account in Draft");
		waitTime(driver);
		oraInv.validateAccountingConfirmationPopup();
		waitTime(driver);
		grep.captureScreenshot("pass", "Account in Draft Accounting confirrmation popup",
				"AccountinDraftConfirmationPopup_withPO");
		oraInv.clickViewAccountingBtn();
		waitTime5(driver);
		grep.infoTest("Validating the Accounting Lines");
		logger.info("Validating the Accounting Lines");
		waitTime(driver);

		grep.captureScreenshot("pass", "Validating the Account in Draft Accounting Lines Popup test",
				"AccountinDraftaccountingLinesPopup_without_PO");

		waitTime2(driver);
		oraInv.validateAccountingLinesHeader(invoiceNumVal);
		oraInv.verifyAccountingAmounts(amountVal);
		oraInv.clickDoneAccountingBtn();

		waitTime2(driver);

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

		waitTime5(driver);
		oraInv.validateAccountingLinesHeader(invoiceNumVal);
		oraInv.clickDoneAccountingBtn();

		waitTime2(driver);

		// payment method

		grep.testCreate("Verify the Payment Method from Payments page test",
				"Verify the Payment Method from Payments page ");
		waitTime(driver);
		oraInv.clickHomeFromInvoicePage();
		waitTime(driver);
		grep.infoTest("Navigate to Payment Page");
		logger.info("Navigate to Payment Page");
		waitTime(driver);

		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.paymentsCatg);
		waitTime1(driver);

		oraHome.click_Tasks_InPO();
		waitTime(driver);
		grep.infoTest("Click Create payment from tasks");
		logger.info("Click Create payment from tasks");
		waitTime1(driver);
		oraHome.selectTasks_InTaskPage(dataTest.createPaymentBtn);
		waitTime10(driver);
		grep.infoTest("Filling the details in Payment Page");
		logger.info("Filling the details in Payment Page");
		waitTime(driver);
		oraInv.searchAndSelectBusinessUnit(dataTest.selectBU);
		waitTime2(driver);
		oraInv.searchAndSelectSupplier_inPaymentPage(dataTest.selectSupplier);
		waitTime(driver);
		oraInv.clickOk_InCreatePaymentPage();
		waitTime2(driver);
		oraInv.searchAndSelectDisbursementBankAccount(dataTest.bankAccount);
		waitTime3(driver);
		oraInv.searchAndSelectPaymentMethod_inPaymentPage(dataTest.paymentCheckMethod);
		waitTime3(driver);
		oraInv.searchAndSelectPaymentProcessProfile_inPaymentPage(dataTest.paymentProfile_Check);

		waitTime(driver);
		grep.infoTest("Click on 'Select and Add' Under Invoices to Pay at the bottom");
		logger.info("Click on 'Select and Add' Under Invoices to Pay at the bottom");
		waitTime2(driver);
		oraInv.clickSelectAndUseButton();
		waitTime2(driver);

		// invoice num
		oraInv.enterInvoiceNumber_InSelectAndAddPopup(invoiceNumVal);
		waitTime2(driver);
		// ok
		oraHome.clickSearchBtn();
		waitTime2(driver);
		oraInv.searchAndSelectInvoice_inSelectAndAddPopup(invoiceNumVal);
		waitTime2(driver);
		oraInv.clickOk_InSelectAndUsePopup();
		waitTime3(driver);
		oraInv.clickSaveAndClose_Payment_Btn();
		waitTime3(driver);
		String paymentConfirmsg = oraHome.validatePurchaseOrderCreationConfirmation();
		grep.infoTest("Payment Created Message: " + paymentConfirmsg);
		logger.info("Payment Created Message: " + paymentConfirmsg);
		waitTime(driver);
		validAssert.trueAssert(paymentConfirmsg.startsWith("Payment"));
		waitTime(driver);
		grep.captureScreenshot("pass", "Payment Created Confirmation Message Popup",
				"PaymentConfirmationMessage_WithoutPo");
		waitTime(driver);
		oraHome.clickOk_inConfirmPO_popup();
		waitTime2(driver);
		oraHome.clickHomeFromPutAway();
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
