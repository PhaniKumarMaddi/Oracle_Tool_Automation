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

public class PayableTrialBalanceReport_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(PayableTrialBalanceReport_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();
	String retrieveDC_id;

	@Test
	public void oracle_Payable_Trial_Balance_Report() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_AccountingAtGlance oraAag = new Oracle_AccountingAtGlance();

		waitTime(driver);

		grep.testCreate("Verify Payable Trial Balance Report Test", "Verify Payable Trial Balance Report");
		oraHome.clickHomeButton();
		waitTime(driver);
		grep.infoTest("Clicking on Home icon");
		logger.info("Clicking on Home icon");

		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab(dataTest.toolsNavTab);
		grep.captureScreenshot("pass", "Expanding Tools in Navigator ", "ExpandingToolsNavigator");
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.scheduledProcessesCatg);
		waitTime1(driver);
		grep.infoTest("Navigating Scheduled Processes");
		logger.info("Navigating Scheduled Processes");
		waitTime(driver);
		grep.captureScreenshot("pass", "Navigating Scheduled Processes", "ScheduleProcessPage");
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
		oraAag.enterName(dataTest.payableTrail);
		waitTime(driver);
		oraAag.clickSearchBtn();
		waitTime(driver);
		grep.infoTest("Select the Payables Trial  Name and click ok");
		logger.info("Select the Payables Trial  Name and click ok");
		waitTime(driver);
		oraAag.clickSelectNameFromList(dataTest.payableTrail);
		waitTime(driver);
		oraAag.clickOk_inSelectNamePopup();
		waitTime2(driver);
		oraAag.clickOk_inScheduleProcessPopup();
		waitTime(driver);
		oraAag.clickLedgerDropdownButton();
		waitTime(driver);

		grep.infoTest("Search and Select CR PRIMARY LEDGER");
		logger.info("Search and Select CR PRIMARY LEDGER");
		waitTime(driver);
		oraAag.clickSearch_InDropdown();
		waitTime2(driver);
		oraAag.enterName(dataTest.crPrimaryLedger);
		waitTime3(driver);
		oraAag.clickSearchBtn();
		waitTime(driver);
		oraAag.clickSelectLedgerFromList(dataTest.crPrimaryLedger);
		waitTime(driver);
		oraAag.clickOk_inLedgerPopup();
		waitTime(driver);
		grep.infoTest("Click on Business Unit and Select CRITICAL RIVER BU");
		logger.info("Click on Business Unit and Select CRITICAL RIVER BU");
		waitTime(driver);
		oraAag.searchAndSelectBusinessUnit_inProcessDetails(dataTest.selectBU);
		waitTime2(driver);
		grep.captureScreenshot("pass", "Creating Payable Trail Balance report", "payableTrailBalance");

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
				"confirmationPopup_ScheduleProcess");

		waitTime(driver);
		oraAag.clickOk_inConfirmation_popup();
		waitTime(driver);
		oraAag.clickRefreshBtn();
		waitTime(driver);
		grep.infoTest("Click on refresh button multiple times beside Change Process Priority");
		logger.info("Click on refresh button multiple times beside Change Process Priority");
		waitTime(driver);
		oraAag.waitForProcessSuccess(process_id_int, 5);
		waitTime(driver);

		grep.captureScreenshot("pass", " Payable Trail Balance Schedule Process Sucess", "PayableTrailBalance_ScheduleProcessSucess");
		waitTime3(driver);

	}
}