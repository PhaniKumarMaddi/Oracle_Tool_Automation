package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Pages.Oracle_AccountingAtGlance;
import Pages.Oracle_CreateCustomer_Page;
import Pages.Oracle_HomePage;
import Pages.Oracle_Receivables_Page;
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
		grep.testCreate("Verify Accounting at Glance Test", "Verify Accounting at Glance");
		oraHome.clickHomeButton();
		waitTime(driver);
		grep.infoTest("Clicking on Home icon");
		logger.info("Clicking on Home icon");

		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab(dataTest.toolsNavTab);
		grep.captureScreenshot("pass", "Expanding Tools in Navigator ", "ExpandingToolsNavigator_AccountingGlance");
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.scheduledProcessesCatg);
		waitTime1(driver);
		grep.infoTest("Navigating Scheduled Processes");
		logger.info("Navigating Scheduled Processes");
		waitTime(driver);
		grep.captureScreenshot("pass", "Navigating Scheduled Processes", "ScheduleProcessPage_AccountingGlance");
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
	}

}
