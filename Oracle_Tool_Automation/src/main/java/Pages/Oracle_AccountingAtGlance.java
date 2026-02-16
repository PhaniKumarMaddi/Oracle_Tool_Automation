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

public class Oracle_AccountingAtGlance extends WaitsManager {
	protected WebDriver driver;
	private static Logger logger = LogManager.getLogger(Oracle_AccountingAtGlance.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();

	public Oracle_AccountingAtGlance() {
		this.driver = DriverManager.getDriver();
	}

	By scheduleNewProcessBtn = By.xpath("//span[text()='Schedule New Process']/parent::a");
	By nameDropdown = By.xpath("//a[@title='Search: Name']");
	By searchInDropdwon = By.xpath("//a[text()='Search...']");
	By insertName = By.xpath("//label[text()=' Name']/preceding-sibling::input");
	By searchBtn = By.xpath("//button[text()='Search']");
	By ok_inSelectName = By.xpath("//button[contains(@id,'selectOneChoice2::lovDialogId::ok') and text()='OK']");
	By ok_inScheduleProcess = By.xpath("//button[contains(@id,':snpokbtnid') and text()='OK']");

	// Process Details
	By subledgerApplication = By
			.xpath("//label[text()='Subledger Application']/parent::td/following-sibling::td/select");
	By ledgerOrLedgerSetDropdown = By.xpath("//a[@title='Search: Ledger or Ledger Set']");
	By ledgerDropdown = By.xpath("//a[@title='Ledger']");
	By insertLedgerOrLedgerSet = By.xpath("//label[text()=' Ledger or Ledger Set']/preceding-sibling::input");
	By ok_inledgerSet = By
			.xpath("//button[contains(@id,'basicReqBody:paramDynForm_LedgerAttr::lovDialogId::ok') and text()='OK']");
	By ok_inledger = By.xpath("//button[contains(@id,'basicReqBody:dynam1:0:ld::lovDialogId::ok') and text()='OK']");

	By processCatgDropdown = By.xpath("//label[text()='Process Category']/parent::td/following-sibling::td/select");
	By reportStyle = By.xpath("//label[text()='Report Style']/parent::td/following-sibling::td/select");
	By submitBtn_inProcessDetail = By.xpath("//a[@accesskey='m']");

	By refreshBtn = By.xpath("//img[@alt='Refresh']");
	By process_ConfirmationPopup = By
			.xpath("//td[contains(@id,'confirmationPopup:confirmSubmitDialog::contentContainer')]/descendant::label");
	By ok_inConfirmation_popup = By
			.xpath("//td[contains(@id,'confirmationPopup:confirmSubmitDialog')]/descendant::button");
	By accountingExecutionReport = By.xpath("//span[text()='Create Accounting Execution Report']");
	By xmlBtn = By.xpath("//div[@id='deliveryInfo']/descendant::a[@id='XMLData']");
	By businessUnitDropdown = By.xpath("//a[starts-with(@title,'Business Unit')]");
//	By defaultDocument = By.xpath("//span[text()='Default Document']");
	By defaultDocument = By.xpath("//*[@id='templateTableBody']/tr[2]/td[1]/span");

	// JOURNALS
//	By journalBatchName = By.xpath("//label[text()='Journal Batch']/parent::td/following-sibling::td/input");
	By journalBatchName = By
			.xpath("//label[text()='Journal Batch']/parent::td/following-sibling::td/descendant::input");
//	By accountingPeriod = By.xpath("//label[text()='Accounting Period']/following-sibling::a");
	By accountingPeriod = By.xpath("//label[text()='Accounting Period']/preceding-sibling::input[1]");
	By journal = By.xpath("//label[text()='Journal']/parent::td/following-sibling::td/input");
	By category = By.xpath("//label[text()='Category']/preceding-sibling::input[1]");
	By journalLines_Account = By.xpath("//input[@aria-label='Account']");
	// Account popup
	By enterCompany_inAccPopup = By.xpath("//label[text()='COMPANY']/preceding-sibling::input");
	By enterDepartment_inAccPopup = By.xpath("//label[text()='DEPTARTMENT']/preceding-sibling::input");
	By enterAccount_inAccPopup = By.xpath("//label[text()='ACCOUNT']/preceding-sibling::input");
	By enterFuture_inAccPopup = By.xpath("//label[text()='FUTURE']/preceding-sibling::input");
	By okBtn_inAccPopup = By.xpath("//button[@accesskey='k']");
//	By resetBtn_inAccPopup = By.xpath("//button[text()='Reset']");
//	By yesResetBtn_inAccPopup = By.xpath("//button[@accesskey='Y']");

	By debit_inJournalLines = By.xpath("//label[text()='Entered Debit']/preceding-sibling::input");
	By credit_inJournalLines = By.xpath("//label[text()='Entered Credit']/preceding-sibling::input");
	By newJournalLine = By.xpath("//span[text()='2']");

	By saveJournal = By.xpath("//span[text()='Save']");
	By completeJournal = By.xpath("//span[text()='Complete']");
	By postJournal = By.xpath("//span[text()='Post']");
	By postConfirmMsg = By.xpath(
			"//td[contains(@id,'contentContainer')]/div[contains(@id,'pt1:_FOr1:1:_FONSr2:0:MAnt2:0:pt1:ap1:userRes')]");
	By postConfirm_OkBtn = By.xpath("//button[@accesskey='K']");

	public void clickScheduleProcessButton() throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(scheduleNewProcessBtn).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(scheduleNewProcessBtn, 30);
				driver.findElement(scheduleNewProcessBtn).click();
			} else {
				logger.error("Schedule Process button Not Available ");
				grep.failTest("Schedule Process button Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void clickNameDropdownButton() throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(nameDropdown).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(nameDropdown, 30);
				driver.findElement(nameDropdown).click();
			} else {
				logger.error("Name dropdown button Not Available ");
				grep.failTest("Name dropdown button Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void clickSearch_InDropdown() throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(searchInDropdwon).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(searchInDropdwon, 30);
				driver.findElement(searchInDropdwon).click();
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

	public void enterName(String nameVal) {
		try {
			implWait(driver);

			driver.findElement(insertName).click();
			driver.findElement(insertName).sendKeys(nameVal);
			waitTime(driver);
			grep.infoTest("Entering Name: " + nameVal);
			logger.info("Entering Name: " + nameVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSearchBtn() {
		implWait(driver);
		driver.findElement(searchBtn).click();
	}

	public void clickSelectNameFromList(String name) throws Exception {

		try {
			By selectName = By
					.xpath("//div[contains(@id,'selectOneChoice2_afrLovInternalTableId::db')]/descendant::span[text()='"
							+ name + "']");

			implWait(driver);
			List<WebElement> state = driver.findElements(selectName);
			if (state.size() > 0) {
				state.getFirst().click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOk_inSelectNamePopup() {
		implWait(driver);
		driver.findElement(ok_inSelectName).click();
	}

	public void clickOk_inScheduleProcessPopup() {
//		implWait(driver);
		waitForElementToBeClickable(ok_inScheduleProcess, 30);
		driver.findElement(ok_inScheduleProcess).click();
	}

	public void selectSubledgerApplication(String option) throws Exception {
		try {
			waitForElementToBeClickable(subledgerApplication, 10);
			WebElement selectBu = driver.findElement(subledgerApplication);
			Select sel = new Select(selectBu);
			sel.selectByVisibleText(option);
			grep.infoTest("Select " + option + " from the drop down under Sub ledger application");
			logger.info("Select " + option + " from the drop down under Sub ledger application");

		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}

	}

	public void selectReportStyle(String option) throws Exception {
		try {
			waitForElementToBeClickable(reportStyle, 10);
			WebElement selectStyle = driver.findElement(reportStyle);
			Select sel = new Select(selectStyle);
			sel.selectByVisibleText(option);
			grep.infoTest("Select " + option + " from the drop down under Report Style");
			logger.info("Select " + option + " from the drop down under Report Style");

		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}

	}

	public void clickLedgerOrLedgerSetDropdownButton() throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(ledgerOrLedgerSetDropdown).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(ledgerOrLedgerSetDropdown, 30);
				driver.findElement(ledgerOrLedgerSetDropdown).click();
			} else {
				logger.error("Ledger Or Ledger Set dropdown button Not Available ");
				grep.failTest("Ledger Or Ledger Set dropdown button Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void clickLedgerDropdownButton() throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(ledgerDropdown).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(ledgerDropdown, 30);
				driver.findElement(ledgerDropdown).click();
			} else {
				logger.error("Ledger dropdown button Not Available ");
				grep.failTest("Ledger dropdown button Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void enterLedgerOr_LedgerSet(String ledgerVal) {
		try {
			implWait(driver);

			driver.findElement(insertLedgerOrLedgerSet).click();
			driver.findElement(insertLedgerOrLedgerSet).sendKeys(ledgerVal);
			waitTime(driver);
			grep.infoTest("Entering Ledger Or Ledger Set: " + ledgerVal);
			logger.info("Entering Ledger Or Ledger Set: " + ledgerVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSelectLedger_LedgerSetFromList(String ledger) throws Exception {

		try {
			By selectLedger = By.xpath(
					"//div[contains(@id,'basicReqBody:paramDynForm_LedgerAttr_afrLovInternalTableId::db')]/descendant::span[text()='"
							+ ledger + "']");

			implWait(driver);
			List<WebElement> state = driver.findElements(selectLedger);
			if (state.size() > 0) {
				state.getFirst().click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickSelectLedgerFromList(String ledger) throws Exception {

		try {
			By selectLedger = By.xpath(
					"//div[contains(@id,'basicReqBody:dynam1:0:ld_afrLovInternalTableId::db')]/descendant::span[text()='"
							+ ledger + "']");

			implWait(driver);
			List<WebElement> state = driver.findElements(selectLedger);
			if (state.size() > 0) {
				state.getFirst().click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOk_inLedgerSetPopup() {
		implWait(driver);
		driver.findElement(ok_inledgerSet).click();
	}

	public void clickOk_inLedgerPopup() {
		implWait(driver);
		driver.findElement(ok_inledger).click();
	}

	public void selectProcessCategory(String option) throws Exception {
		try {
			waitForElementToBeClickable(processCatgDropdown, 10);
			WebElement selectPC = driver.findElement(processCatgDropdown);
			Select sel = new Select(selectPC);
			sel.selectByVisibleText(option);
			grep.infoTest("Select " + option + " from the drop down under Process Category application");
			logger.info("Select " + option + " from the drop down under Process Category application");

		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}

	}

	public void clickSubmitBtn_inProcessDetail() {
		implWait(driver);
		driver.findElement(submitBtn_inProcessDetail).click();
	}

	public void clickRefreshBtn() {
		implWait(driver);
		driver.findElement(refreshBtn).click();
	}

	public String getProcessIdFromPopup() throws Exception {
		try {
			// 1. Wait for the popup text to be visible
			waitForElement(process_ConfirmationPopup, 30);
			WebElement popupElement = driver.findElement(process_ConfirmationPopup);
			String fullText = popupElement.getText().trim(); // Example: "Process 4796079 was submitted."

			logger.info("Popup text received: " + fullText);
			grep.infoTest("Popup text received: " + fullText);

			// 2. Use Regex to extract digits (\d+)
			// This finds the first sequence of numbers in the string
			String processId = fullText.replaceAll("[^0-9]", "");

			if (processId.isEmpty()) {
				logger.error("Could not find a numeric Process ID in the text: " + fullText);
				grep.failTest("Could not find a numeric Process ID in the text: " + fullText);
				return null;
			}

			logger.info("Extracted Process ID: " + processId);
			grep.infoTest("Extracted Process ID: " + processId);

			return processId;

		} catch (Exception e) {
			logger.error("Failed to retrieve Process ID: " + e.getMessage());
			return null;
		}
	}

	public void clickOk_inConfirmation_popup() {
		implWait(driver);
		driver.findElement(ok_inConfirmation_popup).click();
	}

	public void waitForProcessSuccess(int process_id, int timeoutInMinutes) throws Exception {
		By getStatus = By.xpath("//span[text()='" + process_id + "']/parent::td/following-sibling::td[1]");

		// Use the refresh button locator defined previously
		long endTime = System.currentTimeMillis() + (timeoutInMinutes * 60L * 1000L);
		boolean isSuccess = false;

		logger.info("Monitoring status for Process ID: " + process_id);

		while (System.currentTimeMillis() < endTime) {
			try {
				// 2. Retrieve current status
				WebElement statusElement = driver.findElement(getStatus);
				scrollView(getStatus);
				waitTime(driver);
				String currentStatus = statusElement.getText().trim();

				logger.info("Current Status of " + process_id + ": " + currentStatus);
				grep.infoTest("Current Status of " + process_id + ": " + currentStatus);

				// 3. Check if status is "Succeeded" (adjust string if Oracle uses "Success")
				if (currentStatus.equalsIgnoreCase("Succeeded") || currentStatus.equalsIgnoreCase("Success")) {
					isSuccess = true;
					grep.infoTest("Process " + process_id + " completed successfully.");
					logger.info("Process " + process_id + " completed successfully.");
					break;
				}

				// Check for failure to stop early
				if (currentStatus.equalsIgnoreCase("Error") || currentStatus.equalsIgnoreCase("Failed")) {
					grep.warnTest("Process " + process_id + " failed with status: " + currentStatus);
//					throw new RuntimeException("Process Failed.");
					break;
				}

			} catch (Exception e) {
				// Element might not be visible yet if table is still loading
				System.out.println("Process row not found yet. Refreshing...");
			}

			// 4. Refresh the table
			try {
				driver.findElement(refreshBtn).click();
			} catch (Exception refEx) {
				logger.warn("Could not click refresh button.");
			}

			// 5. Wait 10 seconds before next poll
			try {
				waitTime10(driver);
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
		}

		if (!isSuccess) {
			grep.warnTest(
					"Timeout: Process " + process_id + " did not succeed within " + timeoutInMinutes + " minutes.");
			logger.error(
					"Timeout: Process " + process_id + " did not succeed within " + timeoutInMinutes + " minutes.");
		}
	}

	public void waitForSubProcessSuccess(int timeoutInMinutes) throws Exception {
		By taskNameLoc = By
				.xpath("//span[text()='Create Accounting: Subprocess']/ancestor::td/following-sibling::td[2]");
		// Use the refresh button locator defined previously
		long endTime = System.currentTimeMillis() + (timeoutInMinutes * 60L * 1000L);
		boolean isSuccess = false;

		logger.info("Monitoring status for Sub Process");

		while (System.currentTimeMillis() < endTime) {
			try {
				// 2. Retrieve current status
				WebElement statusElement = driver.findElement(taskNameLoc);
				scrollView(taskNameLoc);
				waitTime(driver);
				String currentStatus = statusElement.getText().trim();

				logger.info("Current Status of Sub process :" + currentStatus);
				grep.infoTest("Current Status of Sub process : " + currentStatus);

				// 3. Check if status is "Succeeded" (adjust string if Oracle uses "Success")
				if (currentStatus.equalsIgnoreCase("Succeeded") || currentStatus.equalsIgnoreCase("Success")) {
					isSuccess = true;
					grep.infoTest("Sub Process completed successfully.");
					logger.info("Sub Process completed successfully.");
					break;
				}

				// Check for failure to stop early
				if (currentStatus.equalsIgnoreCase("Error") || currentStatus.equalsIgnoreCase("Failed")) {
					grep.warnTest("Sub Process failed with status: " + currentStatus);
				}

			} catch (Exception e) {
				// Element might not be visible yet if table is still loading
				System.out.println("Process row not found yet. Refreshing...");
			}

			// 4. Refresh the table
			try {
				driver.findElement(refreshBtn).click();

				waitTime10(driver);
			} catch (Exception refEx) {
				logger.warn("Could not click refresh button.");
			}

		}

		if (!isSuccess) {
			grep.warnTest(
					"Timeout: Create Processing Sub Processs did not succeed within " + timeoutInMinutes + " minutes.");
			logger.error(
					"Timeout: Create Processing Sub Processs did not succeed within " + timeoutInMinutes + " minutes.");
		}
	}

	public void clickAccountingExecutionReport() {
		implWait(driver);
		scrollView(accountingExecutionReport);
		driver.findElement(accountingExecutionReport).click();
	}

	public void clickXMLDataBtn() {
//		implWait(driver);
//		driver.switchTo().frame("xnb p_AFFlow");
		waitForElement(xmlBtn, 20);
		scrollView(xmlBtn);
		driver.findElement(xmlBtn).click();
	}

	public void clickDefaultDocumentBtn() {
		By iframeLoc = By.xpath("//iframe[contains(@id, 'processDetails') and contains(@id, 'if1')]");
		try {
			waitForElement(iframeLoc, 30);

			WebElement iframe = driver.findElement(iframeLoc);
			// 2. Switch context to the iframe
			driver.switchTo().frame(iframe);
			logger.info("Switched to Process Details iframe.");

			waitForElement(defaultDocument, 20);
			scrollView(defaultDocument);
			driver.findElement(defaultDocument).click();
			waitTime(driver);
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void searchAndSelectBusinessUnit_inProcessDetails(String businessUnitVal) {
		try {
			implWait(driver);
			By selectBusinessUnit = By.xpath("//span[text()='" + businessUnitVal + "']");

			driver.findElement(businessUnitDropdown).click();
			driver.findElement(businessUnitDropdown).sendKeys(businessUnitVal);
			waitTime(driver);
			grep.infoTest("Selecting Business Unit: " + businessUnitVal);
			logger.info("Selecting Business Unit: " + businessUnitVal);
			waitForElementToBeClickable(selectBusinessUnit, 20);
			driver.findElement(selectBusinessUnit).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// JOURNALS

	public void enterJournalBatchName(String nameVal) {
		try {
//			implWait(driver);
			waitForElementToBeClickable(journalBatchName, 40);

			driver.findElement(journalBatchName).click();
			driver.findElement(journalBatchName).sendKeys(nameVal);
			waitTime(driver);
			grep.infoTest("Entering Journal Name: " + nameVal);
			logger.info("Entering Journal Name: " + nameVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickJournalAccountingPeriod(String selectAcc) throws Exception {

//		implWait(driver);
		By select_AccPeriod = By.xpath("//td[text()='" + selectAcc + "']");

		waitForElementToBeClickable(accountingPeriod, 30);
		driver.findElement(accountingPeriod).click();
		waitTime2(driver);
		scrollView(select_AccPeriod);
		driver.findElement(select_AccPeriod).click();
		waitTime(driver);
		grep.infoTest("Selecting " + selectAcc + " Option in Accounting Period");
		logger.info("Selecting " + selectAcc + " Option in Accounting Period");
		waitTime(driver);

	}

	public void enterJournal(String nameVal) {
		try {
			implWait(driver);

			driver.findElement(journal).click();
			driver.findElement(journal).sendKeys(nameVal);
			waitTime(driver);
			grep.infoTest("Entering Journal: " + nameVal);
			logger.info("Entering Journal: " + nameVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickJournalCategory(String catgVal) throws Exception {

		implWait(driver);
		By select_Catg = By.xpath("//span[text()='" + catgVal + "']");

		driver.findElement(category).click();
		driver.findElement(category).sendKeys(catgVal);
		waitTime2(driver);
		driver.findElement(select_Catg).click();
		waitTime(driver);
		grep.infoTest("Selecting " + catgVal + " Option in Journal Category");
		logger.info("Selecting " + catgVal + " Option in Journal Category");
		waitTime(driver);

	}

	public void searchAccount_inJournalLines() throws Exception {

		implWait(driver);

		driver.findElement(journalLines_Account).click();
		waitTime2(driver);
		actionTab();
		waitTime(driver);
		actionEntered();
		waitTime(driver);

	}

	public void searchAndSelectAccountJournalLines(String companyVal, String deptVal, String accVal, String futureVal) {
		try {
			implWait(driver);

			By selectCompanyVal_inAccPopup = By.xpath("//span[text()='" + companyVal + "']");
			By selectDeptVal_inAccPopup = By.xpath("//span[text()='" + deptVal + "']");
			By selectFutureValue_inAccPopup = By.xpath("//span[text()='" + futureVal + "']");
			By selectAcct_inAccPopup = By.xpath("//span[text()='" + accVal + "']");

			waitTime5(driver);
			boolean elementExists = driver.findElements(enterCompany_inAccPopup).isEmpty();
			if (elementExists) {
				searchAccount_inJournalLines();
			} else {
				waitForElementToBeClickable(enterCompany_inAccPopup, 20);
//				driver.findElement(resetBtn_inAccPopup).click();
//				driver.findElement(yesResetBtn_inAccPopup).click();
				waitTime2(driver);
				driver.findElement(enterCompany_inAccPopup).sendKeys(companyVal);
				waitTime(driver);
				grep.infoTest("Selecting Company Value: " + companyVal);
				logger.info("Selecting Company Value: " + companyVal);
				waitTime(driver);
				driver.findElement(selectCompanyVal_inAccPopup).click();
				waitTime(driver);
				waitForElementToBeClickable(enterDepartment_inAccPopup, 20);
				driver.findElement(enterDepartment_inAccPopup).sendKeys(deptVal);
				waitTime(driver);
				grep.infoTest("Selecting Department Value: " + deptVal);
				logger.info("Selecting Department Value: " + deptVal);
				waitTime(driver);
				driver.findElement(selectDeptVal_inAccPopup).click();
				waitTime(driver);
				waitForElementToBeClickable(enterAccount_inAccPopup, 20);
				driver.findElement(enterAccount_inAccPopup).sendKeys(accVal);
				waitTime(driver);
				grep.infoTest("Selecting Account Value: " + accVal);
				logger.info("Selecting Account Value: " + accVal);
				waitTime(driver);
				driver.findElement(selectAcct_inAccPopup).click();
				waitTime(driver);
				waitForElementToBeClickable(enterFuture_inAccPopup, 20);
				driver.findElement(enterFuture_inAccPopup).sendKeys(futureVal);
				waitTime(driver);
				grep.infoTest("Selecting Future Value: " + futureVal);
				logger.info("Selecting Future Value: " + futureVal);
				waitTime(driver);
				driver.findElement(selectFutureValue_inAccPopup).click();
				waitTime2(driver);
				driver.findElement(okBtn_inAccPopup).click();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterDebit_inJournalLines(String debitVal) {
		try {
			implWait(driver);

			scrollView(debit_inJournalLines);
			driver.findElement(debit_inJournalLines).click();
			driver.findElement(debit_inJournalLines).sendKeys(debitVal);
			waitTime(driver);
			grep.infoTest("Entering Debit Amount: " + debitVal);
			logger.info("Entering Debit Amount: " + debitVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterCredit_inJournalLines(String creditVal) {
		try {
			implWait(driver);

			scrollView(credit_inJournalLines);
			driver.findElement(credit_inJournalLines).click();
			driver.findElement(credit_inJournalLines).sendKeys(creditVal);
			waitTime(driver);
			grep.infoTest("Entering Credit Amount: " + creditVal);
			logger.info("Entering Credit Amount: " + creditVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickNewJournalLineBtn() {
		implWait(driver);
		scrollView(newJournalLine);
		driver.findElement(newJournalLine).click();
	}

	public void clickSaveJournalBtn() {
		waitForElementToBeClickable(saveJournal, 30);
		scrollView(saveJournal);
		driver.findElement(saveJournal).click();
	}

	public void clickCompleteJournalBtn() {
		waitForElementToBeClickable(completeJournal, 30);
//		scrollView(completeJournal);
		driver.findElement(completeJournal).click();
	}

	public void clickPostJournalBtn() {
		waitForElementToBeClickable(postJournal, 30);
		scrollView(postJournal);
		driver.findElement(postJournal).click();
	}

	public void clickOk_inPostConfirmBtn() {

		implWait(driver);
		List<WebElement> ok = driver.findElements(postConfirm_OkBtn);
		ok.getLast().click();
	}

	public String retrievePostConfirmMessage() throws Exception {
		String getMsg = null;
		try {
			waitForElement(postConfirmMsg, 30);
			WebElement popupElement = driver.findElement(postConfirmMsg);
			String fullText = popupElement.getText().trim(); // Example: "Process 4796079 was submitted."

			logger.info("Post Journal Confirmation text received: " + fullText);
			grep.infoTest("Post Journal Confirmation text received: " + fullText);

			// 2. Use Regex to extract digits (\d+)
			// This finds the first sequence of numbers in the string
			getMsg = fullText.replaceAll("[^0-9]", "");

			if (getMsg.isEmpty()) {
				logger.error("Could not find a numeric Journal ID in the text: " + fullText);
				grep.failTest("Could not find a numeric Journal ID in the text: " + fullText);
				return null;
			}

			return getMsg;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getMsg;
	}

	public void verifyJournalStatus(String journalVal) throws Exception {

		implWait(driver);
		By manage_JournalStatus = By
				.xpath("//a[text()='" + journalVal + "']/ancestor::td[1]/following-sibling::td[7]/span");

		String status = driver.findElement(manage_JournalStatus).getText().trim();

		if (status.equals("Posted")) {
			grep.infoTest(journalVal + " Journal Status is Posted");
			logger.info(journalVal + " Journal Status is Posted");
			waitTime(driver);
			grep.captureScreenshot("pass", "Journal Status is Posted", "JournalStatusPosted");
			waitTime(driver);
		} else {
			grep.warnTest(journalVal + " Journal Status is not Posted");
			logger.error(journalVal + " Journal Status is not Posted");
			waitTime(driver);
			grep.captureScreenshot("warn", "Journal Status is not Posted", "JournalStatus_NotPosted");
		}

	}

}