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

public class CreateInvoice_With_PO_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(CreateInvoice_With_PO_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();
	String retrieveDC_id;

	@Test
	public void oracle_InvoiceCreation_WithPO() throws Exception {
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

//		waitTime60(driver);
//		waitTime60(driver);

		oraHome.click_Tasks_InPO();
		waitTime1(driver);
		oraHome.selectTasks_InTaskPage(dataTest.creteInvoiceBtn);
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
		oraInv.searchAndSelectPO(dataTest.identifyPo);
		waitTime3(driver);

		oraInv.enterInvoiceNumber(dataTest.po_invoiceNum);
		waitTime(driver);
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

		// CLICK GO AND SELECT THE LINE
		oraInv.clickGoLinesBtn();
		waitTime10(driver);
		oraInv.selectInvoice_InMatchInvoicePopup();
		waitTime(driver);
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
		grep.captureScreenshot("pass", "After Selecting the Invoice Line test", "afterSelectingLine_inInvoice");
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
		oraInv.searchAndSelectPaymentMethod(dataTest.paymentMethod);
		waitTime(driver);
		grep.captureScreenshot("pass", "Entering Payment reason in manage installments popup",
				"paymentReason_InManageInstallmentsPopup_WithPO");

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
				"AccountinDraftaccountingLinesPopup_withPO");

		waitTime2(driver);
		oraInv.validateAccountingLinesHeader(dataTest.po_invoiceNum);
		oraInv.verifyAccountingAmounts(amount);
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
				"accountingConfirmationPopup_withPO");
		oraInv.clickViewAccountingBtn();
		waitTime5(driver);
		grep.infoTest("Validating the Accounting Lines");
		logger.info("Validating the Accounting Lines");
		waitTime(driver);

		grep.captureScreenshot("pass", "Validating the Accounting Lines Popup test", "accountingLinesPopup_withPO");

		waitTime2(driver);
		oraInv.validateAccountingLinesHeader(dataTest.po_invoiceNum);
//		oraInv.verifyAccountingAmounts(amount);
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
		oraHome.selectTasks_InTaskPage(dataTest.cretePaymentBtn);
		waitTime10(driver);
		grep.infoTest("Filling the details in Payment Page");
		logger.info("Filling the details in Payment Page");
		waitTime(driver);
		grep.infoTest("Select Business Unit");
		logger.info("Select Business Unit");
		waitTime(driver);
		oraInv.searchAndSelectBusinessUnit(dataTest.selectBU);
		waitTime(driver);
		grep.infoTest("Select Supplier");
		logger.info("Select Supplier");
		waitTime2(driver);
		oraInv.searchAndSelectSupplier(dataTest.selectSupplier);
		waitTime(driver);
		oraInv.clickOk_InCreatePaymentPage();
		waitTime(driver);
		grep.infoTest("Click on Disbursement Bank Account");
		logger.info("Click on Disbursement Bank Account");
		waitTime2(driver);
		oraInv.searchAndSelectDisbursementBankAccount(dataTest.bankAccount);
		waitTime(driver);
		grep.infoTest("Select Check Payment Method ");
		logger.info("Select Check Payment Method ");
		waitTime(driver);
		oraInv.searchAndSelectPaymentMethod_inPaymentPage(dataTest.paymentCheckMethod);
		waitTime(driver);
		grep.infoTest("Select Payment Process Profile");
		logger.info("Select Payment Process Profile");
		waitTime(driver);
		oraInv.searchAndSelectPaymentProcessProfile_inPaymentPage(dataTest.paymentProfile_Check);

		waitTime(driver);
		grep.infoTest("Click on 'Select and Add' Under Invoices to Pay at the bottom");
		logger.info("Click on 'Select and Add' Under Invoices to Pay at the bottom");
		waitTime(driver);
		oraInv.clickSelectAndUseButton();
		waitTime(driver);

		// invoice num
		oraInv.enterInvoiceNumber_InSelectAndAddPopup(dataTest.invoiceNum);
		// ok
		oraHome.clickSearchBtn();
		waitTime(driver);
		oraInv.searchAndSelectInvoice_inSelectAndAddPopup(dataTest.invoiceNum);
		waitTime(driver);
		oraInv.clickOk_InSelectAndUsePopup();
		waitTime(driver);
		oraInv.clickSaveAndClose_Payment_Btn();
		waitTime3(driver);
		String paymentConfirmsg = oraHome.validatePurchaseOrderCreationConfirmation();
		grep.infoTest("Payment Created Message: " + paymentConfirmsg);
		logger.info("Payment Created Message: " + paymentConfirmsg);
		waitTime(driver);
		validAssert.trueAssert(paymentConfirmsg.startsWith("Payment"));
		waitTime(driver);
		grep.captureScreenshot("pass", "Payment Created Confirmation Message Popup",
				"PaymentConfirmationMessage_WithPo");
		waitTime(driver);
		oraHome.clickOk_inConfirmPO_popup();
		waitTime2(driver);
		
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
