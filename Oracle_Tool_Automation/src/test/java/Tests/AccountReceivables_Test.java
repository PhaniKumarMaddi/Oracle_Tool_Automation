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

public class AccountReceivables_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(AccountReceivables_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();
	String retrieveDC_id;

	@Test
	public void oracle_CreateTransaction_Test() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_AccountingAtGlance oraAag = new Oracle_AccountingAtGlance();

		waitTime(driver);

		grep.testCreate("Verify Create Transactions Test", "Verify Create Transactions");
		oraHome.clickHomeButton();
		waitTime(driver);
		grep.infoTest("Clicking on Home icon");
		logger.info("Clicking on Home icon");

		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectTabWithNavigator(dataTest.receivableNavTab);
		grep.captureScreenshot("pass", "Expanding Receivables in Navigator ", "ExpandingReceivablesNavigator_CreateTransaction");
		waitTime(driver);
		oraHome.selectFromQuickActions(dataTest.createTransactionAction);
		waitTime1(driver);
		grep.infoTest("Navigating Create Transaction");
		logger.info("Navigating Create Transaction");
		waitTime(driver);
		grep.captureScreenshot("pass", "Navigating Create Transaction", "CreateTransactionPage");
		waitTime(driver);

		grep.infoTest("Filling Details in Create Transaction Page");
		logger.info("Filling Details in Create Transaction Page");
		waitTime(driver);
		

	}
}