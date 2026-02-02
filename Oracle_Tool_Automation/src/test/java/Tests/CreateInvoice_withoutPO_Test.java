package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Pages.Oracle_HomePage;
import Pages.Oracle_InvoicePage;
import Pages.TestInitializer;
import Utility.GenerateReports;
import Utility.ValidatingAssertions;
import lombok.val;

public class CreateInvoice_withoutPO_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(CreateInvoice_withoutPO_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();

	@Test
	public void oracle_InvoiceCreation_WithoutPO() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_InvoicePage oraInv = new Oracle_InvoicePage();

		grep.testCreate("Verify Navigate to Create Invoice Page Functionality Test", "Navigate to Create Invoice Page");
		oraHome.clickHomeButton();
		waitTime(driver);
		grep.infoTest("Clicking on Home icon");
		logger.info("Clicking on Home icon");

		grep.captureScreenshot("pass", "Inside Home Page ", "Oracle_HomePage_ForInvoice");
		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab("Payables");
		grep.captureScreenshot("pass", "Expanding Payables in Navigator ", "Expand_PayablesNavigation");
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator("Invoices");
		waitTime1(driver);
		grep.infoTest("Click on Tasks for Create Invoice");
		logger.info("Click on Tasks for Create Invoice");
		waitTime(driver);
		oraInv.validateInvoicePageTitle();
		waitTime(driver);
		oraHome.click_Tasks_InPO();
		waitTime1(driver);
		oraHome.selectTasks_InTaskPage("Create Invoice");
		waitTime2(driver);
		oraInv.validateCreateInvoicePageTitle();
		grep.captureScreenshot("pass", "Inside Create Invoice Page", "CreateInvoicePage");
		waitTime(driver);

		grep.testCreate("Filling the Invoice Details Functionality Test", "Filling the Invoice Details");
		waitTime(driver);

		grep.infoTest("Filling the Invoice Details");
		logger.info("Filling the Invoice Details");
		waitTime(driver);
		oraInv.searchAndSelectBusinessUnit("CRITICAL RIVER BU");
		waitTime2(driver);
		oraInv.searchAndSelectSupplier("CR Applied Material");
		waitTime(driver);
		oraInv.enterInvoiceNumber("CR_MP_Test_001");
		waitTime(driver);
		oraInv.enterInvoiceAmount("USD", "1000");
		waitTime(driver);
		oraInv.enterInvoiceDescription("Test Supplier Invoices for Expenses");

		waitTime(driver);
		oraInv.searchAndSelectPaymentTerms("IMMEDIATE");

		waitTime(driver);
		oraInv.enterInvoiceReceiveDate("03", "01", "2026");

		waitTime(driver);
		grep.infoTest("Enter Details in Lines Section");
		logger.info("Enter Details in Lines Section");
		waitTime(driver);
		oraInv.expandLinesSection();
		waitTime2(driver);
		oraInv.enterAmountInLines("1000");
		waitTime(driver);
		oraInv.searchAndSelectDistributionCombination("101", "52110");
		waitTime(driver);
		String retrieveDC_id = oraInv.retrieveDistributionCombinationID();
		grep.infoTest("Retrieving Distibution Combinatio ID: " + retrieveDC_id);
		logger.info("Retrieving Distibution Combinatio ID: " + retrieveDC_id);

		grep.captureScreenshot("pass", "After filling Invoice fields test", "afterFillingInvoiceFields");
		waitTime(driver);
		grep.infoTest("Saving and Validating Invoice");
		logger.info("Saving and Validating Invoice");

		oraInv.clickSaveInvoiceBtn();
		oraInv.clickInvoiceActionAndValidateBtn();
		
		String validMsg = oraInv.getInvoicevalidation();
		if(validMsg.equals("Needs revalidation")) {
			grep.infoTest("Needs revalidation");
			logger.info("Needs revalidation");

		}
		
		

	}
}
