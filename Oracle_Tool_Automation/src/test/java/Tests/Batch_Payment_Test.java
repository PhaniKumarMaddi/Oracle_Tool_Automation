package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Pages.Oracle_BatchPaymentPage;
import Pages.Oracle_HomePage;
import Pages.Oracle_InvoicePage;
import Pages.TestInitializer;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class Batch_Payment_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Batch_Payment_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();
	String retrieveDC_id;

	@Test
	public void oracle_Batch_Payment() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_InvoicePage oraInv = new Oracle_InvoicePage();
		Oracle_BatchPaymentPage oraBpp = new Oracle_BatchPaymentPage();

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

		oraBpp.enterInvoiceNumber_InSubmitPaymentProcessPage(dataTest.batchPaymentNumber);
		oraBpp.searchAndSelectTemplate(dataTest.batchTemplate);
		waitTime3(driver);
		oraBpp.isPaymentRadioButtonSelected();
		waitTime(driver);
		grep.captureScreenshot("pass", "Validating Details in Selection Criteria", "selctionCriteriaPage");
		grep.infoTest("Clicking on Payment and Processing Options Tab");
		logger.info("Clicking on Payment and Processing Options Tab");
		waitTime2(driver);
		oraBpp.clickPaymentAndProcessingOptionTab();
		waitTime2(driver);
		oraBpp.verifyBankAccountName(dataTest.bankAccount);
		waitTime1(driver);
		grep.captureScreenshot("pass", "Validating Details in Payment and Processing Options Page",
				"Payment_ProcessingOptionsPage");
		waitTime2(driver);
		oraBpp.clickSubmitBtn_inSubmitPaymentProcess();
		waitTime(driver);
		oraBpp.waitForPaymentRecordAndStatus(dataTest.batchPaymentNumber, dataTest.pendingInstallReviewStatus, 10);
		waitTime2(driver);

		grep.captureScreenshot("pass", "Inside review Installment Page", "InsideReviewInstallmentPage");

		waitTime(driver);

		oraBpp.keepFirstRowAndRemoveOthersFromSelectInstallemnts();
		waitTime(driver);
		oraBpp.clickSave_inSelectInstallments();
		waitTime(driver);

		oraBpp.clickSubmitBtn_inSubmitPaymentProcess();
		waitTime3(driver);
		oraBpp.waitForPaymentRecordAndStatus(dataTest.batchPaymentNumber, dataTest.pendingPropsedPaymentReviewStatus,
				10);
		waitTime2(driver);

		grep.captureScreenshot("pass", "Inside Review Proposed Payment Page", "InsideReviewProposedPaymentPage");
		waitTime(driver);
		grep.infoTest("Clicking on Resume Payment Process Button");
		logger.info("Clicking on Resume Payment Process Button");
		waitTime2(driver);

		oraBpp.waitForPaymentStatusAndExpand(dataTest.batchPaymentNumber, dataTest.waitingForPaymentFileStatus, 10);
		waitTime(driver);
		String paymentReceiptNum = oraBpp.getPaymentProcessRequestNumber(dataTest.batchPaymentNumber);
		waitTime2(driver);
		grep.infoTest("Payment receipt Number :" + paymentReceiptNum);
		logger.info("Payment receipt Number :" + paymentReceiptNum);
		waitTime(driver);

		// oraBpp.waitForPrintPaymentActionAndStatus(dataTest.batchPaymentNumber,dataTest.createAndReadyForPrintingStatus,10);
		oraBpp.waitForPrintPaymentActionAndStatus(dataTest.batchPaymentNumber, dataTest.formattedAndReadyStatus, 10);
		grep.captureScreenshot("pass", "Inside Print Payment Document Page", "InsidePrintPaymentDocumentPage");
		waitTime(driver);
		oraBpp.clickPrintButton();

		waitTime(driver);
		oraBpp.waitForPrintPaymentActionAndStatus(dataTest.batchPaymentNumber, dataTest.SubmittedPrintingStatus, 10);

		grep.infoTest("Validating the Payment File Details");
		logger.info("Validating the Payment File Details");

		oraBpp.validatePaymentFileDetails(paymentReceiptNum, dataTest.batchPaymentNumber,
				dataTest.SubmittedPrintingStatus);

		waitTime(driver);
		grep.captureScreenshot("pass", "Validate Payment File", "PaymentFileValidationPage");

		waitTime(driver);
		oraBpp.clickRecordPrintStatusButton();
		waitTime3(driver);

		grep.infoTest("Submitting record Print Status Payment File");
		logger.info("Submitting record Print Status Payment File");

		grep.captureScreenshot("pass", "Inside Submitting record Print Status Payment File",
				"SubmitRecordPrintStatusPaymentFile");
		waitTime(driver);
		oraBpp.clickSubmitRecordPrintFileButton();
		waitTime2(driver);
		oraBpp.clickRecordPrintStatusWarningButton();
		waitTime5(driver);
		oraInv.clickDoneAccountingBtn();
		waitTime(driver);

		grep.infoTest("Navigating to Recently Completed Tab");
		logger.info("Navigating to Recently Completed Tab");
		waitTime(driver);

		oraBpp.clickRecentlyCompletedTab();
		waitTime(driver);
		oraBpp.clickRecentlyCompletedRefreshButton();
		waitTime5(driver);
		oraBpp.verifyPaymentNumber_inCompletedTab(dataTest.batchPaymentNumber);

	}

}
