package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Pages.Oracle_AccountingAtGlance;
import Pages.Oracle_Customer_StandardReport_Page;
import Pages.Oracle_HomePage;
import Pages.TestInitializer;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class Oracle_AR_StandardReportTest extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Oracle_AR_StandardReportTest.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();

	@Test
	public void oracle_AR_StandardReport() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_Customer_StandardReport_Page oraCc = new Oracle_Customer_StandardReport_Page();
		Oracle_AccountingAtGlance oraAag = new Oracle_AccountingAtGlance();

		grep.testCreate("Verify AR STandard Report Test", "Verify AR STandard Report");

		oraHome.clickHomeButton();
		waitTime1(driver);
		oraHome.selectTabWithNavigator(dataTest.receivableNavTab);
		grep.captureScreenshot("pass", "Expanding Receivables in Navigator ",
				"ExpandingReceivablesNavigator_StandardReport");
		waitTime(driver);
		oraHome.selectShowMore_UnderQuickActions(dataTest.receivableNavTab);
		waitTime1(driver);
		oraHome.selectQuickActions_inShowMore(dataTest.prepareReceivablesReconcilCatg);
		waitTime1(driver);
		grep.captureScreenshot("pass", "Navigating Prepare Receivables to General Ledger Reconciliation",
				"PrepareReceivableGeneralLedgerReconciliation_page");
		waitTime(driver);

		grep.infoTest("Filling details");
		logger.info("Filling details");
		waitTime(driver);
		oraCc.enterRequestName("Testing");
		waitTime(driver);
		oraCc.clickBusinessUnit(dataTest.selectBU);
		waitTime(driver);
		oraCc.clickAccountingPeriod(dataTest.accountPeriod);
		waitTime(driver);
		oraCc.clickSumbit_Reconciliation_Btn();
		waitTime5(driver);
		String transactionNum = oraCc.validateSubmitConfirmationPopup();
		System.out.println(transactionNum);
		int process_id = Integer.parseInt(transactionNum);
		waitTime(driver);

		logger.info("Extracted Transaction Number: " + transactionNum);
		grep.infoTest("Extracted Transaction Number: " + transactionNum);
		waitTime(driver);
		grep.captureScreenshot("pass", "Submitting Prepare Receivables to General Ledger Reconciliation",
				"Submitting_PrepareReceivableGeneralLedgerReconciliation");
		waitTime(driver);
		oraCc.clickOk_InSubmitConfirmation();
		waitTime5(driver);

		oraHome.clickFavouriteButton();
		waitTime3(driver);
		oraHome.selectRecentItems("Monitor Processes");
		waitTime(driver);
		oraAag.clickRefreshBtn();
		waitTime(driver);
		grep.infoTest("Click on refresh button multiple times beside Change Process Priority");
		logger.info("Click on refresh button multiple times beside Change Process Priority");
		waitTime(driver);
		oraAag.waitForProcessSuccess(process_id, 1);
		waitTime(driver);
		grep.captureScreenshot("pass", "Prepare Receivables to General Ledger in Monitor Process",
				"PrepareReceivablesGeneralLedger_MonitorProcess");

		waitTime3(driver);
		oraHome.clickHomeFromPutAway();
		waitTime(driver);

	}

}
