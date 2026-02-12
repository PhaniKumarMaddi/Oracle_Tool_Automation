package Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Utility.DriverManager;
import Utility.GenerateReports;
import Utility.ValidatingAssertions;
import Utility.WaitsManager;

public class Oracle_BankStatement_Page extends WaitsManager {
	protected WebDriver driver;
	private static Logger logger = LogManager.getLogger(Oracle_BankStatement_Page.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();

	public Oracle_BankStatement_Page() {
		this.driver = DriverManager.getDriver();
	}

	By bankStatementTitle = By.xpath("//div[@title='Create Bank Statement']");
	By bankAccount = By.xpath("//label[text()='Bank Account']/parent::td/following-sibling::td/span/input");
	By periodStartDate = By.xpath("//a[contains(@id,':periodStartDateId:')]");
	By selectStartDate = By
			.xpath("//table[contains(@id,':periodStartDateId:')]/descendant::td[@class='x120 p_AFSelected']");
	By periodEndDate = By.xpath("//a[contains(@id,':periodEndDateId:')]");
	By selectEndDate = By
			.xpath("//table[contains(@id,':periodEndDateId:')]/descendant::td[@class='x120 p_AFSelected']");
	By statementId = By.xpath("//label[text()='Statement ID']/parent::td/following-sibling::td/input");
	// Statement Lines
	By statementLines = By.xpath("//a[text()='Statement Lines']");
	By addLine = By.xpath("//img[@title='Create']");
	By bookingDate = By.xpath("//a[contains(@id,':inputDate1')]");
	By selectBookingDate = By.xpath("//table[contains(@id,':inputDate1')]/descendant::td[@class='x120 p_AFSelected']");
	By transactionDropdown = By.xpath("//a[contains(@id,':transactioncodedispId')]");
	By searchInDropdown = By.xpath("//a[text()='Search...']");
	By searchBtn = By.xpath("//button[text()='Search']");
	By ok_inSelectTransaction = By
			.xpath("//button[contains(@id,'transactioncodedispId::lovDialogId::ok') and text()='OK']");
	By flowIndicator = By.xpath("//label[text()='Flow Indicator']/parent::td/following-sibling::td/select");
	By statementAmount = By.xpath("//input[contains(@id,'amountId::content')]");
	By okBtn = By.xpath("//button[@accesskey='K']");
	By save = By.xpath("//button[text()='Save']");
	By saveAndClose = By.xpath("//button[@accesskey='S']");
	By changesConfirmPopup = By.xpath("//div[contains(@id,':msgDlg::_cnt')]/descendant::td/div");
	By ok_inConfirmPopup = By.xpath("//button[contains(@id,'msgDlg::cancel') and text()='OK']");
	By ok_inWarnPopup = By
			.xpath("//div[@class='AFPopupSelector']/descendant::button[contains(@id,'MAnt2:1:cbsap1:cb5')]");

	// validation bank statement
	By expandSearch = By.xpath("//a[@title='Expand Search']");
	By bankAccountDropdown = By.xpath("//a[@title='Search:  Bank Account']");
	By searchBankAccountPopup = By.xpath("//input[contains(@id,'frLovInternalQueryId:value00::content')]");
	By searchBtn_bankAccPopup = By.xpath("//button[contains(@id,'afrLovInternalQueryId::search')]");
	By ok_inBankAccPopup = By.xpath("//button[contains(@id,'lovDialogId::ok')]");
	By selectStmt_EndDate = By.xpath("//select[contains(@id,'dateModeId::content')]");
	By doneBtn = By.xpath("//a[@accesskey='o']");

	public void validateCreateBankStatementPageTitle() throws Exception {

		try {
			implWait(driver);
			String actualText = driver.findElement(bankStatementTitle).getText().trim();
			waitTime(driver);

			grep.infoTest("Create Bank Statement Page Header: " + actualText);
			logger.info("Create Bank Statement Page Header: " + actualText);

			validAssert.equalsAssert(actualText, "Create Bank Statement");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectBankAccount(String bankAccVal) {
		try {
			implWait(driver);
			By selectBankAcct = By.xpath("//li[starts-with(text(),'" + bankAccVal + "')]");

			driver.findElement(bankAccount).click();
			driver.findElement(bankAccount).sendKeys(bankAccVal);
			waitTime(driver);
			grep.infoTest("Selecting Bank Account: " + bankAccVal);
			logger.info("Selecting Bank Account: " + bankAccVal);
			waitForElementToBeClickable(selectBankAcct, 20);
			driver.findElement(selectBankAcct).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectPeriodStartDate() throws Exception {
		try {
			waitForElementToBeClickable(periodStartDate, 20);
			WebElement datePickerIcon = driver.findElement(periodStartDate);
			datePickerIcon.click();
			logger.info("Clicked on Period Start Date picker icon.");
			grep.infoTest("Clicked on Period Start Date picker icon.");

			waitForElementToBeClickable(selectStartDate, 30);
			driver.findElement(selectStartDate).click();

			logger.info("Successfully selected the start date.");
			grep.infoTest("Successfully selected the Start date.");

		} catch (Exception e) {
			logger.error("Failed to select date from calendar: " + e.getMessage());
			grep.failTest("Date selection failed for Period Start Date." + e.getMessage());
		}
	}

	public void selectPeriodEndDate() throws Exception {
		try {
			waitForElementToBeClickable(periodEndDate, 20);
			WebElement datePickerIcon = driver.findElement(periodEndDate);
			datePickerIcon.click();
			logger.info("Clicked on Period End Date picker icon.");
			grep.infoTest("Clicked on Period End Date picker icon.");

			waitForElementToBeClickable(selectEndDate, 30);
			driver.findElement(selectEndDate).click();

			logger.info("Successfully selected the Enddate.");
			grep.infoTest("Successfully selected the End date.");

		} catch (Exception e) {
			logger.error("Failed to select date from calendar: " + e.getMessage());
			grep.failTest("Date selection failed for Period End Date." + e.getMessage());
		}
	}

	public void enterStatementId(String idVal) {
		try {
			implWait(driver);

			driver.findElement(statementId).click();
			driver.findElement(statementId).sendKeys(idVal);
			waitTime(driver);
			grep.infoTest("Entering Statement Id: " + idVal);
			logger.info("Entering Statement Id: " + idVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickStatementLinesTab() {
		waitForElementToBeClickable(statementLines, 30);
		driver.findElement(statementLines).click();
	}

	public void clickAddLine_StatementLinesTab() {
//		implWait(driver);
		waitForElementToBeClickable(addLine, 30);
		driver.findElement(addLine).click();

	}

	public void selectBookingDate_inStatementLine() throws Exception {
		try {
			waitForElementToBeClickable(bookingDate, 20);
			WebElement datePickerIcon = driver.findElement(bookingDate);
			datePickerIcon.click();
			logger.info("Clicked on Booking Date picker icon.");
			grep.infoTest("Clicked on Booking Date picker icon.");

			waitForElementToBeClickable(selectBookingDate, 30);
			driver.findElement(selectBookingDate).click();

			logger.info("Successfully selected the Booking date.");
			grep.infoTest("Successfully selected the Booking date.");

		} catch (Exception e) {
			logger.error("Failed to select date from calendar: " + e.getMessage());
			grep.failTest("Date selection failed for Booking Date." + e.getMessage());
		}
	}

	public void clickTransactionDropdownButton() throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(transactionDropdown).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(transactionDropdown, 30);
				driver.findElement(transactionDropdown).click();
			} else {
				logger.error("Transaction dropdown button Not Available ");
				grep.failTest("Transaction dropdown button Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void clickSearch_InDropdown() throws Exception {
		try {
//			implWait(driver);
			waitForElementToBeClickable(searchInDropdown, 20);
			boolean elementExists = !driver.findElements(searchInDropdown).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(searchInDropdown, 30);
				driver.findElement(searchInDropdown).click();
			} else {
				logger.error("Search in dropdown Not Available ");
				grep.failTest("Search in dropdown Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void clickSearchBtn() {
		implWait(driver);
		driver.findElement(searchBtn).click();
	}

	public void clickSelectTransaction_FromList(String transVal) throws Exception {

		try {
			By selectTransaction = By.xpath(
					"//div[contains(@id,'transactioncodedispId_afrLovInternalTableId::db')]/descendant::span[text()='"
							+ transVal + "']");

			waitForElementToBeClickable(selectTransaction, 30);
			List<WebElement> state = driver.findElements(selectTransaction);
			if (state.size() > 0) {
				state.getFirst().click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOk_inSelectTransactionPopup() {
		implWait(driver);
		driver.findElement(ok_inSelectTransaction).click();
	}

	public void selectFlowIndicator(String option) throws Exception {
		try {
			waitForElementToBeClickable(flowIndicator, 10);
			WebElement selectIndicator = driver.findElement(flowIndicator);
			Select sel = new Select(selectIndicator);
			sel.selectByVisibleText(option);
			grep.infoTest("Select " + option + " from the drop down under Flow Indicator");
			logger.info("Select " + option + " from the drop down under Flow Indicator");

		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}

	}

	public void enterStatementLine_Amount(String amount) throws Exception {
		try {
			// 1. Handle Currency Dropdown
			waitForElementToBeClickable(statementAmount, 60);

			WebElement amountInput = driver.findElement(statementAmount);
			System.out.println("Entering Statement Amount: " + amount);
			grep.infoTest("Entering Statement Amount: " + amount);
			logger.info("Entering Statement Amount: " + amount);
			amountInput.clear(); // Clear existing value if any
			amountInput.sendKeys(amount);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOK_StatementBtn() {
		waitForElementToBeClickable(okBtn, 30);
		driver.findElement(okBtn).click();
	}

	public void clickSaveStatementBtn() {
		waitForElementToBeClickable(save, 30);
		driver.findElement(save).click();
	}

	public void clickSaveAndCloseStatementBtn() {
		waitForElementToBeClickable(saveAndClose, 30);
		driver.findElement(saveAndClose).click();
	}

	public void clickOK_WarnPopup() {
//		waitForElementToBeClickable(ok_inWarnPopup, 30);
//		driver.findElement(ok_inWarnPopup).click();
		try {

			implWait(driver);
			WebElement warnBtn = driver.findElement(ok_inWarnPopup);
			if (warnBtn.isDisplayed()) {
				warnBtn.click();
			} else {
				grep.infoTest("Warning not available");
			}

		} catch (Exception e) {
			grep.infoTest("Warning not available");

		}

	}

	public void validateSaveConfiramtionPopup() throws Exception {

		try {
//			implWait(driver);
			waitForElement(changesConfirmPopup, 30);
			String actualBody = waitVisible(changesConfirmPopup).getText();
			validAssert.equalsAssert(actualBody, "Your changes were saved.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOK_SaveConfirmPopup() {
		waitForElementToBeClickable(ok_inConfirmPopup, 30);
		driver.findElement(ok_inConfirmPopup).click();
	}

	// Manage Bank statements
	public void clickExpandSearchBtn() {
		waitForElementToBeClickable(expandSearch, 30);
		driver.findElement(expandSearch).click();
	}

	public void clickBankAccountDropdownButton() throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(bankAccountDropdown).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(bankAccountDropdown, 30);
				driver.findElement(bankAccountDropdown).click();
			} else {
				logger.error("Bank Account dropdown button Not Available ");
				grep.failTest("Bank Account dropdown button Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void enterBankAccount_InPopup(String bankAccVal) {
		try {
			implWait(driver);
			driver.findElement(searchBankAccountPopup).click();
			driver.findElement(searchBankAccountPopup).sendKeys(bankAccVal);
			waitTime(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSearchBtn_BankAccPopup() {
		implWait(driver);
		driver.findElement(searchBtn_bankAccPopup).click();
	}

	public void clickSelectBankAccount_FromList(String bankAccVal) throws Exception {

		try {
			By selectAcc = By.xpath(
					"//div[contains(@id,'ovInternalTableId::db')]/descendant::span[text()='" + bankAccVal + "']");

			waitForElementToBeClickable(selectAcc, 30);
			List<WebElement> state = driver.findElements(selectAcc);
			if (state.size() > 0) {
				state.getFirst().click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOk_inSelectBankAccountPopup() {
		implWait(driver);
		driver.findElement(ok_inBankAccPopup).click();
	}

	public void selectingStatementEndDate(String option) throws Exception {
		try {
			waitForElementToBeClickable(selectStmt_EndDate, 10);
			WebElement selectDate = driver.findElement(selectStmt_EndDate);
			Select sel = new Select(selectDate);
			sel.selectByVisibleText(option);
			grep.infoTest("Select " + option + " from the drop down under Statement End Date");
			logger.info("Select " + option + " from the drop down under Statement End Date");

		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}

	}

	public void expandBankAccount(String accVal) throws Exception {
		try {
			By expandAcc = By
					.xpath("//span[text()=' " + accVal + "']/parent::span/preceding-sibling::span/a[@title='Expand']");
			waitTime(driver);
			driver.findElement(expandAcc).click();
			waitTime(driver);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public void verifyStatementStatus_bankStatus(String statementId) {

		By statusLoc = By.xpath("//a[text()=' " + statementId + "']/ancestor::td[1]/following-sibling::td[5]");

		try {
			waitForElement(statusLoc, 20);
			WebElement statusElement = driver.findElement(statusLoc);

			String actualStatus = statusElement.getText().trim();

			logger.info("Statement ID: " + statementId + " | Actual Reconciliation Status: " + actualStatus);
			grep.infoTest("Statement ID: " + statementId + " | Actual Reconciliation Status: " + actualStatus);

			if (actualStatus.equalsIgnoreCase("Incomplete")) {
				logger.info("Verification Passed: Status is " + actualStatus);
				grep.infoTest("Status verification successful for " + statementId);
			} else {
				logger.error("Verification Failed! Expected:Incomplete but found: " + actualStatus);
				grep.failTest("Status mismatch: Expected: Incomplete but found " + actualStatus);
			}

		} catch (Exception e) {
			logger.error("Could not find status for Statement ID: " + statementId + ": " + e.getMessage());
			grep.failTest("Failed to locate status cell using XPath: " + statementId);
		}
	}

	public void clickDoneStatementBtn() {
		try {
			waitForElementToBeClickable(doneBtn, 60);
			WebElement done = driver.findElement(doneBtn);
			done.click();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
