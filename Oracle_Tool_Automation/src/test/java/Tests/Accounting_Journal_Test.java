package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.Oracle_AccountingAtGlance;
import Pages.Oracle_HomePage;
import Pages.TestInitializer;
import Utility.ExcelDataProvider;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class Accounting_Journal_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Accounting_Journal_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();


	@DataProvider(name = "JournalTest")
	public Object[][] getData() {
		// Get Excel Test Data passing Excel File Name and Sheet Name
		Object data[][] = ExcelDataProvider.testData("Oracle_TestData", "Accounting Journal");
		return data;
	}
	@Test(dataProvider = "JournalTest")
	public void oracle_Accounting_Journal_Test(String journalName) throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_AccountingAtGlance oraAag = new Oracle_AccountingAtGlance();

		waitTime(driver);
		grep.testCreate("Verify Create Accounting for Accounting Journal Test",
				"Verify Create Accounting for Accounting Journal");
		oraHome.clickHomeButton();
		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab(dataTest.toolsNavTab);
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.scheduledProcessesCatg);
		waitTime1(driver);
		grep.infoTest("Navigating Scheduled Processes");
		logger.info("Navigating Scheduled Processes");
		waitTime(driver);
		grep.captureScreenshot("pass", "Navigating Scheduled Processes for Journal Accounting",
				"ScheduleProcessPage_JournalAccounting");
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
		oraAag.selectSubledgerApplication(dataTest.cashManagementTab);
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
		grep.captureScreenshot("pass", "Creating Schedule Process for Journal Accounting ",
				"JournalAccounting_ScheduleProcessCreated");

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
		grep.captureScreenshot("pass", "Creating Schedule Process Confirmation popup for Journal Accounting",
				"confirmationPopup_ScheduleProcess_JournalAccounting");

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
		grep.captureScreenshot("pass", "Create Processing Schedule Process Sucess for Journal Accounting",
				"CreateProcessing_ScheduleProcessSucess_JournalAccounting");

		waitTime3(driver);

		oraHome.clickHomeFromPutAway();
		waitTime2(driver);

		grep.testCreate("Creating a Journal Test", "Creating a Journal");
		waitTime(driver);
		oraHome.selectTabWithNavigator(dataTest.generalAcct_NavTab);
		waitTime(driver);
		oraHome.selectFromQuickActions(dataTest.createJournalCatg);
		waitTime5(driver);

		oraAag.enterJournalBatchName(journalName);
		waitTime(driver);
		grep.captureScreenshot("pass", "Inside My journal Page", "MyJournalPage");
		waitTime(driver);
		oraAag.clickJournalAccountingPeriod(dataTest.accountPeriod);
		waitTime(driver);
		oraAag.enterJournal(journalName);
		waitTime(driver);
		oraAag.clickJournalCategory(dataTest.journalCategory);
		waitTime(driver);
		oraAag.searchAccount_inJournalLines();
		waitTime(driver);
		oraAag.searchAndSelectAccountJournalLines(dataTest.journalLine_company, dataTest.journalLine_dept,
				dataTest.journalLine_acc, dataTest.journalLine_future);
		waitTime2(driver);
		oraAag.enterDebit_inJournalLines(dataTest.journalLine_debit);
		waitTime2(driver);
		oraAag.clickNewJournalLineBtn();
		waitTime5(driver);
		oraAag.searchAccount_inJournalLines();
		waitTime(driver);
		oraAag.searchAndSelectAccountJournalLines(dataTest.journalLine_company, dataTest.journalLine_dept_2,
				dataTest.journalLine_acc_2, dataTest.journalLine_future);
		waitTime2(driver);
		oraAag.enterCredit_inJournalLines(dataTest.journalLine_credit);
		waitTime(driver);
		oraAag.clickSaveJournalBtn();
		waitTime2(driver);
		oraAag.clickCompleteJournalBtn();
		waitTime2(driver);
		oraAag.clickPostJournalBtn();
		waitTime5(driver);
		String getMsg = oraAag.retrievePostConfirmMessage();
		logger.info("Extracted Journal ID: " + getMsg);
		grep.infoTest("Extracted journal ID: " + getMsg);
		waitTime(driver);
		grep.captureScreenshot("pass", "After Posting Journal", "Posting_JournalStatus");
		waitTime(driver);
		oraAag.clickOk_inPostConfirmBtn();
		waitTime(driver);
		oraHome.clickHomeButton();
		waitTime2(driver);

		grep.testCreate("Approval of Journal Test", "Approval of Journal");
		waitTime(driver);
		oraHome.selectTabWithNavigator(dataTest.generalAcct_NavTab);
		waitTime(driver);
		oraHome.selectFromQuickActions(dataTest.manageJournalsCatg);
		waitTime5(driver);
		oraAag.enterJournalBatchName(journalName);
		waitTime(driver);
		oraAag.clickSearchBtn();
		waitTime(driver);
		oraAag.verifyJournalStatus(journalName);
		waitTime(driver);

		oraHome.clickHomeButton();

	}

}
