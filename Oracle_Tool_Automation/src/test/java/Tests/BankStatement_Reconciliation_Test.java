package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.Oracle_BankStatement_Page;
import Pages.Oracle_HomePage;
import Pages.TestInitializer;
import Utility.ExcelDataProvider;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class BankStatement_Reconciliation_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(BankStatement_Reconciliation_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();

	@DataProvider(name = "BankStatementTest")
	public Object[][] getData() {
		// Get Excel Test Data passing Excel File Name and Sheet Name
		Object data[][] = ExcelDataProvider.testData("Oracle_TestData", "BankStatement Reconciliation");
		return data;
	}

	@Test(dataProvider = "BankStatementTest")
	public void OracleReconciliation_BankStatement(String stmtId) throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_BankStatement_Page oraBsp = new Oracle_BankStatement_Page();

		waitTime(driver);
		grep.testCreate("Verify Reconciliation Bank Statement Test", "Verify Reconciliation Bank Statement");
		oraHome.clickHomeButton();
		waitTime(driver);

		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab(dataTest.cashManagementTab);
		grep.captureScreenshot("pass", "Expanding Cash Management in Navigator ",
				"Expand_CashManagementNavigation_BankReconciliation");
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.bankStmtAnd_ReconciCatg);
		waitTime1(driver);
		grep.infoTest("Click on Bank Statements and Reconciliation");
		logger.info("Click on Bank Statements and Reconciliation");
		waitTime(driver);

		oraHome.click_Tasks_InPO();
		waitTime1(driver);
		oraHome.selectTasks_InTaskPage(dataTest.manualReconciliationBtn);
		waitTime5(driver);
		oraBsp.clickUnreconciledTab();
		waitTime(driver);
		grep.infoTest("Filling Details in Reconcilation Tab");
		logger.info("Filling Details in Reconcilation Tab");
		waitTime(driver);
		oraBsp.searchAndSelectBankAccount(dataTest.bankAccount);
		waitTime(driver);
		oraBsp.selectFromDate_Reconciled();
		waitTime(driver);
		oraBsp.selectToDate_Reconciled();
		waitTime(driver);
		oraBsp.clickReconciled_SearchBtn();
		waitTime(driver);

		grep.captureScreenshot("pass", "Searching for UNReconcilation for Bank account",
				"SearchUnreconciliationRecord_BankReconciliation");
		waitTime(driver);
		String amount = oraBsp.getBankStatementLineText(stmtId);
		waitTime(driver);
		oraBsp.clickBankStatementLineCheckbox(stmtId);
		waitTime(driver);
		String refId = oraBsp.getSystemTransactionLineText(amount);
		waitTime2(driver);
		oraBsp.selectSystemTransactionByAmount(amount);
		waitTime(driver);
		grep.captureScreenshot("pass", "Seleting bank statement and system transaction lines",
				"SelectingBankStmt_SystemReceipt_BankReconciliation");
		waitTime(driver);
		oraBsp.clickReconciledButton();
		waitTime5(driver);
		grep.infoTest("Clicking Reconciled Tab");
		logger.info("Clicking Reconciled Tab");
		waitTime1(driver);

		oraBsp.clickReconciledTab();
		waitTime5(driver);
		oraBsp.clickReconciled_SearchBtn();
		waitTime(driver);

		grep.infoTest("Clicking Search in Reconciled Tab");
		logger.info("Clicking Search in Reconciled Tab");
		waitTime3(driver);
		oraBsp.isReceiptPresentInReconcileTab(refId);
		waitTime(driver);

		grep.captureScreenshot("pass", "Searching for Reconcilation for Bank account",
				"SearchReconciliationRecord_BankReconciliation");
		waitTime(driver);
		oraBsp.clickDoneReconciliationBtn();

		waitTime3(driver);
		oraHome.clickHomeFromOtherPages();
		waitTime(driver);

	}

}
