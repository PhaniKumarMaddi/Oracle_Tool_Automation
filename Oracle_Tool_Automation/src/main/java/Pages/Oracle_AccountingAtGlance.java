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
		implWait(driver);
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
					grep.failTest("Sub Process failed with status: " + currentStatus);
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
			grep.failTest(
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

}
