package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.Oracle_HomePage;
import Pages.Oracle_InvoicePage;
import Pages.TestInitializer;
import Utility.ExcelDataProvider;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class Query_Invoice_Payment_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Query_Invoice_Payment_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();

	@DataProvider(name = "QueryInvoiceTest")
	public Object[][] getData() {
		// Get Excel Test Data passing Excel File Name and Sheet Name
		Object data[][] = ExcelDataProvider.testData("Oracle_TestData", "Query Invoice");
		return data;
	}

	@Test(dataProvider = "QueryInvoiceTest")
	public void oracle_Query_InvoicePayment(String invNumVal) throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_InvoicePage oraInv = new Oracle_InvoicePage();

		grep.testCreate("Verify Query Invoice Number For Paid Invoice Test",
				"Verify Query Invoice Number For Paid Invoice");
		oraHome.clickHomeButton();
		waitTime(driver);
		grep.infoTest("Clicking on Home icon");
		logger.info("Clicking on Home icon");

		grep.captureScreenshot("pass", "Inside Home Page ", "Oracle_HomePage_Query_Invoice");
		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab(dataTest.payableNavTab);
		grep.captureScreenshot("pass", "Expanding Payables in Navigator ", "Expand_PayablesNavigation_QueryInvoice");
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.invoiceCatg);
		waitTime1(driver);
		grep.infoTest("Click on Tasks for Create Invoice");
		logger.info("Click on Tasks for Create Invoice");
		waitTime(driver);
		oraInv.validateInvoicePageTitle();
		waitTime(driver);

		oraHome.click_Tasks_InPO();
		waitTime1(driver);
		oraHome.selectTasks_InTaskPage(dataTest.manageInvoiceBtn);
		waitTime10(driver);
		oraInv.validateManageInvoicePageTitle();
		waitTime2(driver);

		grep.captureScreenshot("pass", "Inside Manage Invoice Page", "ManageInvoicePage");
		waitTime(driver);

		oraInv.enterInvoiceNumber_InSelectAndAddPopup(invNumVal);
		waitTime2(driver);
		oraHome.clickSearchBtn();
		waitTime2(driver);
		grep.captureScreenshot("pass", "Search Invoice in Query invoice Page", "SearchInvoice_QueryInvoicePage");
		waitTime(driver);

		grep.infoTest("Clicking on Invoice Number");
		logger.info("Clicking on Invoice Number");
		oraInv.selectInvoiceNumber_inSearch(invNumVal);

		waitTime(driver);
		grep.infoTest("Clicking on Payments Tab");
		logger.info("Clicking on Payments Tab");
		oraInv.clickPaymentsTab_QueryInvoice();
		waitTime3(driver);
		grep.captureScreenshot("pass", "Inside Payments Tab", "InsidePaymentsTab_QueryInvoicePage");
		waitTime(driver);

		oraInv.clickPaymentNumber_QueryInvoice();
		waitTime(driver);
		oraInv.validateInvoiceNum_InPaymentPopup(invNumVal);
		oraInv.validateInvoiceStatus_InPaymentPopup();
		waitTime(driver);
		grep.captureScreenshot("pass", "Inside Payments Receipt Popup", "InsidePaymentsReceiptPopup_QueryInvoicePage");

		waitTime2(driver);
		oraInv.clickOk_InPaymentPopup();
		waitTime2(driver);
		oraHome.clickDoneReceiptBtn();
		waitTime(driver);
		oraHome.clickHomeFromOtherPages();

	}

}
