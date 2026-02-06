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

public class Accounting_At_Glance_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Accounting_At_Glance_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();
	String retrieveDC_id;

	@Test
	public void oracle_Accounting_At_Glance() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_InvoicePage oraInv = new Oracle_InvoicePage();

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
		grep.captureScreenshot("pass", "Expanding Tools in Navigator ", "Expand_PayablesNavigation_BatchPayment");
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.scheduledProcessesCatg);
		waitTime1(driver);
	}
}