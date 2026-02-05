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
		oraHome.selectTasks_InTaskPage(dataTest.submiPaymentProcessRequestBtn);
		waitTime10(driver);
		oraBpp.validateSubmitPaymentProcessPageTitle();
		

	}
}
