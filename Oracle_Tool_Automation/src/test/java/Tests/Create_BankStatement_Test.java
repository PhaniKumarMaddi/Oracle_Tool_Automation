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

public class Create_BankStatement_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Create_BankStatement_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();

	@DataProvider(name = "BankStatementTest")
	public Object[][] getData() {
		// Get Excel Test Data passing Excel File Name and Sheet Name
		Object data[][] = ExcelDataProvider.testData("Oracle_TestData", "BankStatement");
		return data;
	}
	@Test(dataProvider = "BankStatementTest")
	public void OracleCreate_BankStatement(String stmtId) throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_BankStatement_Page oraBsp = new Oracle_BankStatement_Page();

		waitTime(driver);

		grep.testCreate("Verify Creating Bank Statement Test", "Verify Creating Bank Statement");
		oraHome.clickHomeButton();
		waitTime(driver);

		grep.captureScreenshot("pass", "Inside Home Page ", "Oracle_HomePage_BankStatement");
		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab(dataTest.cashManagementTab);
		grep.captureScreenshot("pass", "Expanding Cash Management in Navigator ",
				"Expand_CashManagementNavigation_BankStmt");
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.bankStmtAnd_ReconciCatg);
		waitTime1(driver);
		grep.infoTest("Click on Bank Statements and Reconciliation");
		logger.info("Click on Bank Statements and Reconciliation");
		waitTime(driver);

		oraHome.click_Tasks_InPO();
		waitTime1(driver);
		oraHome.selectTasks_InTaskPage(dataTest.createBankStmtBtn);
		waitTime5(driver);
		oraBsp.validateCreateBankStatementPageTitle();
		waitTime(driver);
		grep.infoTest("Filling Bank Statement Details");
		logger.info("Filling Bank Statement Details");
		oraBsp.searchAndSelectBankAccount(dataTest.bankAccount);
		waitTime(driver);
		oraBsp.selectPeriodStartDate();
		waitTime(driver);
		oraBsp.selectPeriodEndDate();
		waitTime(driver);
		oraBsp.enterStatementId(stmtId);
		waitTime(driver);
		oraBsp.clickStatementLinesTab();
		waitTime(driver);
		grep.captureScreenshot("pass", "After Filling Bank Statement", "fillingDetails_InBankStatment");
		waitTime(driver);
		oraBsp.clickAddLine_StatementLinesTab();
		waitTime(driver);
		oraBsp.selectBookingDate_inStatementLine();
		waitTime(driver);
		oraBsp.clickTransactionDropdownButton();
		oraBsp.clickSearch_InDropdown();
		waitTime(driver);
		oraBsp.clickSearchBtn();
		oraBsp.clickSelectTransaction_FromList(dataTest.transactionCode_101);
		waitTime(driver);
		oraBsp.clickOk_inSelectTransactionPopup();
//		oraBsp.selectFlowIndicator(dataTest.debitFlowIndicator);
		oraBsp.selectFlowIndicator(dataTest.creditFlowIndicator);
		oraBsp.enterStatementLine_Amount(dataTest.unitPriceAmt);
		waitTime(driver);
		grep.captureScreenshot("pass", "After Filling Statement Line", "fillingStatementLine_InBankStatment");
		waitTime(driver);
		oraBsp.clickOK_StatementBtn();
		oraBsp.clickSaveStatementBtn();
		oraBsp.clickOK_WarnPopup();
		oraBsp.validateSaveConfiramtionPopup();
		oraBsp.clickOK_SaveConfirmPopup();
		waitTime(driver);
		oraBsp.clickSaveAndCloseStatementBtn();
		oraBsp.clickOK_WarnPopup();
		waitTime(driver);
		grep.captureScreenshot("pass", "Saving Bank Statement", "SavingBankStatement");
		waitTime(driver);
		oraBsp.validateSaveConfiramtionPopup();
		oraBsp.clickOK_SaveConfirmPopup();
		waitTime(driver);

		grep.testCreate("Validation of Bank Statement Test", "Validation of Bank Statement");
		waitTime(driver);
		oraHome.click_Tasks_InPO();
		waitTime(driver);
		oraHome.selectTasks_InTaskPage(dataTest.manageBankStmtBtn);
		waitTime2(driver);
		oraBsp.clickExpandSearchBtn();
		waitTime(driver);
		grep.infoTest("Selecting Bank Account");
		logger.info("Selecting Bank Account");
		waitTime2(driver);
		oraBsp.clickBankAccountDropdownButton();
		oraBsp.clickSearch_InDropdown();
		waitTime(driver);
		oraBsp.enterBankAccount_InPopup(dataTest.bankAccount);
		waitTime(driver);
		oraBsp.clickSearchBtn_BankAccPopup();
		waitTime(driver);
		oraBsp.clickSelectBankAccount_FromList(dataTest.bankAccount);
		waitTime(driver);
		oraBsp.clickOk_inSelectBankAccountPopup();
		waitTime(driver);
		grep.captureScreenshot("pass", "Selecting Bank Account in Manage Statement Page",
				"selectingAcc_inManageStatement");
		oraBsp.clickSearchBtn();
		waitTime2(driver);
		oraBsp.selectingStatementEndDate(dataTest.statement_EndDate);
		waitTime(driver);
		oraBsp.expandBankAccount(dataTest.bankAccount);
		waitTime(driver);
		oraBsp.verifyStatementStatus_bankStatus(stmtId);
		waitTime(driver);
		grep.captureScreenshot("pass", "Verify Bank Statement Reconciliation Status", "bankStatement_Reconciliation");
		waitTime(driver);
		oraBsp.clickDoneStatementBtn();
		waitTime(driver);
		oraHome.clickHomeFromPutAway();

	}

}
