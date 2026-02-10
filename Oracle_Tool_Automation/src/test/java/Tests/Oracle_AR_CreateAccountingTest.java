package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Pages.Oracle_AccountingAtGlance;
import Pages.Oracle_HomePage;
import Pages.TestInitializer;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class Oracle_AR_CreateAccountingTest extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Oracle_AR_CreateAccountingTest.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();

	@Test
	public void oracle_AR_CreateAccounting() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_AccountingAtGlance oraAag = new Oracle_AccountingAtGlance();

		waitTime(driver);
		grep.testCreate("Verify Create Accounting for AR Test", "Verify Create Accounting for AR");
		oraHome.clickHomeButton();
		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab(dataTest.toolsNavTab);
		grep.captureScreenshot("pass", "Expanding Tools in Navigator for Create AR Accounting",
				"ExpandingToolsNavigator_Create_AR_Accounting");
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.scheduledProcessesCatg);
		waitTime1(driver);
		grep.infoTest("Navigating Scheduled Processes");
		logger.info("Navigating Scheduled Processes");
		waitTime(driver);
		grep.captureScreenshot("pass", "Navigating Scheduled Processes for Create AR Accounting",
				"ScheduleProcessPage__Create_AR_Accounting");
		waitTime(driver);

		grep.infoTest("Click on Schedule New Process");
		logger.info("Click on Schedule New Process");
		waitTime(driver);
		oraAag.clickScheduleProcessButton();
		waitTime2(driver);

		grep.infoTest("Click on Name dropdown");
		logger.info("Click on Name dropdown");
		waitTime(driver);
		oraAag.clickNameDropdownButton();
		waitTime(driver);
		grep.infoTest("Click on search");
		logger.info("Click on search");
		waitTime(driver);
		oraAag.clickSearch_InDropdown();
		waitTime(driver);
		grep.infoTest("Click on name and type the name");
		logger.info("Click on name and type the name");
		waitTime(driver);
		oraAag.enterName(dataTest.createAccounting);
		waitTime(driver);
		oraAag.clickSearchBtn();
		waitTime(driver);
		grep.infoTest("Select the Create Accounting  Name and click ok");
		logger.info("Select the Create Accounting  Name and click ok");
		waitTime(driver);
		oraAag.clickSelectNameFromList(dataTest.createAccounting);
		waitTime(driver);
		oraAag.clickOk_inSelectNamePopup();
		waitTime2(driver);
		oraAag.clickOk_inScheduleProcessPopup();
		waitTime(driver);
		grep.infoTest("Click on Subledger Application and Select Receivables");
		logger.info("Click on Subledger Application and Select Receivables");
		waitTime(driver);
		oraAag.selectSubledgerApplication(dataTest.receivablesSubLedger);
		waitTime(driver);
		grep.infoTest("Click on ledger or ledger Set dropdown and click Search");
		logger.info("Click on ledger or ledger Set dropdown and click Search");
		waitTime(driver);
		oraAag.clickLedgerOrLedgerSetDropdownButton();
		waitTime(driver);

		grep.infoTest("Search and Select CR PRIMARY LEDGER");
		logger.info("Search and Select CR PRIMARY LEDGER");
		waitTime(driver);
		oraAag.clickSearch_InDropdown();
		waitTime2(driver);
		oraAag.enterLedgerOr_LedgerSet(dataTest.crPrimaryLedger);
		waitTime3(driver);
		oraAag.clickSearchBtn();
		waitTime(driver);
		oraAag.clickSelectLedger_LedgerSetFromList(dataTest.crPrimaryLedger);
		waitTime(driver);
		oraAag.clickOk_inLedgerSetPopup();
		waitTime(driver);
		oraAag.selectReportStyle(dataTest.detail_reportStyle);
		waitTime2(driver);
		grep.captureScreenshot("pass", "Creating Schedule Process for AR Create Accounting ",
				"CreateAccounting_ScheduleProcessCreated");

		grep.infoTest("Click on Submit at the top");
		logger.info("Click on Submit at the top");
		waitTime(driver);
		oraAag.clickSubmitBtn_inProcessDetail();
		waitTime(driver);
		grep.infoTest("Check for confirmation and click ok");
		logger.info("Check for confirmation and click ok");
		waitTime(driver);
		String processId = oraAag.getProcessIdFromPopup();
		int process_id_int = Integer.parseInt(processId);
		grep.infoTest("Retrieved Process Id:" + processId);
		logger.info("Retrieved Process Id:" + processId);
		waitTime(driver);
		grep.captureScreenshot("pass", "Creating Schedule Process Confirmation popup",
				"confirmationPopup_ScheduleProcess_CreateAccounting");

		waitTime(driver);
		oraAag.clickOk_inConfirmation_popup();
		waitTime(driver);
		oraAag.clickRefreshBtn();
		waitTime(driver);
		grep.infoTest("Click on refresh button multiple times beside Change Process Priority");
		logger.info("Click on refresh button multiple times beside Change Process Priority");
		waitTime(driver);
		oraAag.waitForProcessSuccess(process_id_int, 2);
		waitTime(driver);
		grep.captureScreenshot("pass", "Create Processing Schedule Process Sucess",
				"CreateProcessing_ScheduleProcessSucess_CreateAccounting");

		waitTime3(driver);
		grep.infoTest("Click on Create Accounting Execution Report");
		logger.info("Click on Create Accounting Execution Report");
		waitTime(driver);
		oraAag.clickAccountingExecutionReport();
		waitTime2(driver);
		grep.captureScreenshot("pass", "Accounting Execution Report for Create AR Accounting",
				"AccountExecutionReport_CreateAccounting");
		waitTime5(driver);
		oraAag.clickDefaultDocumentBtn();
		waitTime5(driver);
		grep.captureScreenshot("pass", "Default Document for Create AR Accounting", "DefaultDocument_CreateAccounting");
		waitTime(driver);

		oraHome.clickHomeFromPutAway();
		waitTime(driver);

	}

}
