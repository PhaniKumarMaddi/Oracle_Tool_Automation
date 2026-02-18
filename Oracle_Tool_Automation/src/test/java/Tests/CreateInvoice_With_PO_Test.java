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

public class CreateInvoice_With_PO_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(CreateInvoice_With_PO_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();

	@DataProvider(name = "InvoiceTest")
	public Object[][] getData() {
		// Get Excel Test Data passing Excel File Name and Sheet Name
		Object data[][] = ExcelDataProvider.testData("Oracle_TestData", "Invoice With PO");
		return data;
	}

//	@Test
//	public void oracle_InvoiceCreation_WithPO() throws Exception {
	@Test(dataProvider = "InvoiceTest")
	public void oracle_InvoiceCreation_WithPO(String poNumVal, String invoiceNumVal, String amountVal, String dateVal,
			String monthVal, String yearVal, String payReason, String payMethod) throws Exception {

		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_InvoicePage oraInv = new Oracle_InvoicePage();

		grep.testCreate("Verify Navigate to Create Invoice Page Functionality With PO Test",
				"Navigate to Create Invoice Page With PO ");
		oraHome.clickHomeButton();
		waitTime(driver);
		grep.infoTest("Clicking on Home icon");
		logger.info("Clicking on Home icon");

		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab(dataTest.payableNavTab);
		grep.captureScreenshot("pass", "Expanding Payables in Navigator ", "Expand_PayablesNavigation_WithPO");
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
		grep.captureScreenshot("pass", "Inside Create Invoice Page", "CreateInvoicePage_withPO");
		waitTime(driver);

		grep.testCreate("Filling the Invoice Details Functionality Test", "Filling the Invoice Details");
		waitTime(driver);

		grep.infoTest("Filling the Invoice Details");
		logger.info("Filling the Invoice Details");
		waitTime(driver);

		// SELECT PO
//		oraInv.searchAndSelectPO(dataTest.identifyPo);
//		waitTime3(driver);
//		oraInv.enterInvoiceNumber(dataTest.po_invoiceNum);
		oraInv.searchAndSelectPO(poNumVal);
		waitTime3(driver);
		oraInv.enterInvoiceNumber(invoiceNumVal);
		waitTime(driver);
		oraInv.enterInvoiceAmount(amountVal);
		waitTime(driver);

		oraInv.enterInvoiceDescription("Test Supplier Invoices for Expenses");

		waitTime5(driver);

		oraInv.searchAndSelectPaymentTerms(dataTest.paymentTerms);
		waitTime(driver);
		oraInv.enterInvoiceReceiveDate(dateVal, monthVal, yearVal);

		waitTime(driver);
		grep.infoTest("Enter Details in Lines Section");
		logger.info("Enter Details in Lines Section");
		waitTime(driver);
		oraInv.expandLinesSection();
		waitTime2(driver);

		// CLICK GO AND SELECT THE LINE
		oraInv.clickGoLinesBtn();
		waitTime10(driver);
		oraInv.selectInvoice_InMatchInvoicePopup();
		waitTime1(driver);
		oraInv.clickOk_forWarn_MatchInvoicePopup();
		waitTime2(driver);
		String amount = oraInv.getAmountFromMatchInvoicePopup();
		System.out.println(amount);
		waitTime(driver);
		grep.infoTest("Po Amount: " + amount);
		logger.info("Po Amount: " + amount);
		waitTime(driver);

		grep.infoTest("Click in Apply Match invoice popup");
		logger.info("Click in Apply Match invoice popup");
		waitTime(driver);
		oraInv.clickApply_InMatchInvoicePopup();
		waitTime(driver);
		grep.captureScreenshot("pass", "After Selecting the Invoice Line test", "afterSelectingLine_inInvoice_withPO");
		waitTime(driver);
		grep.infoTest("Applying the Invoice Line");
		logger.info("Applying the Invoice Line");
		oraInv.clickOk_InMatchInvoicePopup();
		waitTime2(driver);
		oraInv.enterInvoiceAmount(amount);
		waitTime(driver);

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
		if (status.equals("Needs revalidation")) {
			processRevalidationFlow();
			status = oraInv.getInvoiceValidation(); // Refresh status after fix
		}

		// 3. Final verification and logging
		if (status.equals("Validated")) {
			grep.infoTest("Validated");
			logger.info("Validated");
			grep.captureScreenshot("pass", "Invoice Validated  test", "InvoiceValidated_withPO");
		} else {
			grep.failTest("Invoice status is currently: " + status);
			logger.warn("Invoice status is currently: " + status);
		}
		waitTime2(driver);
		grep.testCreate("Accounting the Validated Invoice Test", "Accounting the Validated Invoice");
		waitTime2(driver);
		grep.infoTest("Clicking on Post to ledger");
		logger.info("Clicking on Post to ledger");
		waitTime(driver);
		oraInv.clickInvoiceActionAndValidateBtn("Post to Ledger");
		waitTime(driver);
		oraInv.validateAccountingConfirmationPopup();
		waitTime(driver);
		grep.captureScreenshot("pass", "Post to Ledger Accounting confirrmation popup",
				"accountingConfirmationPopup_withPO");
		oraInv.clickViewAccountingBtn();
		waitTime5(driver);
		grep.infoTest("Validating the Accounting Lines");
		logger.info("Validating the Accounting Lines");
		waitTime(driver);

		grep.captureScreenshot("pass", "Validating the Accounting Lines Popup test", "accountingLinesPopup_withPO");

		waitTime(driver);
		oraInv.validateAccountingLinesHeader(invoiceNumVal);
		oraInv.verifyAccountingAmounts(amount);
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

		oraInv.enterPaymentReasonComment(payReason);
		waitTime(driver);
		oraInv.searchAndSelectPaymentMethod(payMethod);
		waitTime(driver);
		grep.captureScreenshot("pass", "Entering Payment reason in manage installments popup",
				"paymentReason_InManageInstallmentsPopup_withPO");

		oraInv.clickSaveAndClose_Payment_Btn();
		waitTime5(driver);

		oraInv.clickInvoiceActionAndValidateBtn("Pay in Full");
		waitTime(driver);

		grep.infoTest("Entering Payment details in Pay in Full popup");
		logger.info("Entering Payment details in Pay in Full popup");
		waitTime(driver);
		oraInv.searchAndSelectBankAccount(dataTest.bankAccount);
		waitTime(driver);
		oraInv.searchAndSelectPaymentProfile(dataTest.paymentProfile);
		waitTime2(driver);

		grep.captureScreenshot("pass", "Entering Payment in Pay in full popup",
				"paymentDetails_inPayInFullPopup_withPO");

		oraInv.clickSubmit_Payment_Btn();
		waitTime2(driver);

		oraInv.validatePaymentConfirmationPopup();
		waitTime2(driver);
		grep.captureScreenshot("pass", "Verifying Payment Confirmation Popup",
				"paymentConfirmation_inPayInFullPopup_withPO");
		oraInv.clickOk_InPaymentConfirmation();
		waitTime2(driver);
		oraInv.clickSaveAndCloseInvoiceBtn();
		waitTime2(driver);

		grep.infoTest("Save and Close Invoice");
		logger.info("Save and Close Invoice");
		waitTime3(driver);

		oraHome.clickHomeButton();
		waitTime(driver);

	}

	private void processRevalidationFlow() throws Exception {
		Oracle_InvoicePage oraInv = new Oracle_InvoicePage();
		waitTime(driver);

		grep.infoTest("Needs revalidation");
		logger.info("Processing revalidation flow...");

		grep.captureScreenshot("pass", "Invoice Need Re-Validation", "InvoiceNeed_Revalidation_withPO");

		oraInv.clickNeedReValidation();
		waitTime1(driver);
		oraInv.clickHoldWarningLink();
		waitTime1(driver);
		grep.captureScreenshot("pass", "Clicking on Warning link", "warning_Link_Revalidation_withPO");

		oraInv.selectValidatedReleaseName();
		waitTime2(driver);
		oraInv.clickSaveAndCloseBtn();
		waitTime3(driver);

	}
}
