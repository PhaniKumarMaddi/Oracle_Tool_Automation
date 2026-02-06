package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Utility.DriverManager;
import Utility.GenerateReports;
import Utility.ValidatingAssertions;
import Utility.WaitsManager;

public class Oracle_BatchPaymentPage extends WaitsManager {
	protected WebDriver driver;
	private static Logger logger = LogManager.getLogger(Oracle_BatchPaymentPage.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();

	public Oracle_BatchPaymentPage() {
		this.driver = DriverManager.getDriver();
	}

	By submitPaymentProcessTitle = By.xpath("//div[@title='Submit Payment Process Request']");
	By inputPaymentProcessName = By.xpath("//input[contains(@name,'ap1:inputText1')]");
	By insertTemplateName = By.xpath("//input[contains(@name,'ap1:templateNameId')]");
	By currencyPayment = By.xpath(
			"//legend[text()='Currencies']/parent::fieldset/span/label[text()='Payment']/preceding-sibling::input");
	By paymentProcessOptions = By.xpath("//div/div/a[text()='Payment and Processing Options']");
	By bankAccount = By.xpath("//input[contains(@name,':bankAccountNameId')]");

	By submitBtn_inSubmitPaymentProcess = By.xpath("//a[@accesskey='m']");
	// last
	By paymentRefreshBtn = By.xpath("//a[contains(@id, 'PprRequiringAction:_ATTp:i2') and @title='Refresh']");
//	By paymetStatus = By
//			.xpath("//a[text()='MP_Check_Payment_001']/ancestor::td[1]/following-sibling::td[3]/descendant::span");
//	By reviewInstallmentAction = By.xpath(
//			"//a[text()='Check Payment_Test_001']/ancestor::td[1]/following-sibling::td[4]/descendant::a[contains(@id, 'commandImageLink1_1')]");

	By removeSelectedInstallmentsBtn = By.xpath("//div[@title='Remove']/a");
	By firstRow_inSelectInstallments = By.xpath("//tr[@_afrrk='1']");
	By okBtn_ForRemoveSelectedInstallments = By
			.xpath("//div[@class='AFPopupSelector']/descendant::button[text()='OK']");
	By saveSelectedInsallments = By.xpath("//span[text()='Save']");
	By resumePaymentProcessButton = By.xpath("//button[text()='Resume Payment Process']");
	By printButton = By.xpath("//button[text()='Print']");

	// validate payment file
	By paymentFileHeader = By.xpath("//div[starts-with(@title,'Payment File: ')]");
	By adminReference = By.xpath("//label[text()='Administrator Reference']/parent::td/following-sibling::td");
	By paymentFileStatus = By.xpath("//label[text()='Status']/parent::td/following-sibling::td");
	By recordPrintStatusBtn = By.xpath("//button[text()='Record Print Status']");
	By submitRecordPrint = By.xpath("//button[text()='Submit']");
	By recordPrintStatus_WarnBtn = By.xpath("//button[text()='Record the Print Status']");
	By recentlyCompletedTab = By.xpath("//div/div/a[starts-with(text(),'Recently Completed')]");

	By completedRefreshBtn = By.xpath("//a[contains(@id, 'RecentlyCompletedPpr:_ATTp:i1') and @title='Refresh']");

	// Electronic Batch payment flow

//	By businessUnitRadioBtn = By.xpath(
//			"//legend[text()='Business Units']/parent::fieldset/span/label[text()='Payment']/preceding-sibling::input");
	By businessUnitRadioBtn = By
			.xpath("//legend[text()='Business Units']/parent::fieldset/span/label[text()='Payment']");

	By businessUnit_AddRowBtn = By.xpath(
			"//legend[text()='Business Units']/parent::fieldset/ancestor::tr[1]/following-sibling::tr[2]/descendant::div[@title='Add Row']");
	By businessUnit_Name = By.xpath(
			"//legend[text()='Business Units']/parent::fieldset/ancestor::tr[1]/following-sibling::tr[2]/td/span/div/div[2]/descendant::input");

//	By legalEntityRadioBtn = By.xpath(
//			"//legend[text()='Legal Entities']/parent::fieldset/span/label[text()='Specific']/preceding-sibling::input");
	By legalEntityRadioBtn = By
			.xpath("//legend[text()='Legal Entities']/parent::fieldset/span/label[text()='Specific']");
	By legalEntity_AddRowBtn = By.xpath(
			"//legend[text()='Legal Entities']/parent::fieldset/ancestor::tr[1]/following-sibling::tr[1]/descendant::div[@title='Add Row']");
	By legalEntityName = By.xpath(
			"//legend[text()='Legal Entities']/parent::fieldset/ancestor::tr[1]/following-sibling::tr/td/span/div/div[2]/descendant::input");

	By supplierOrParty = By.xpath("//label[text()='Supplier or Party']/parent::td/following-sibling::td/input");

//	By applyCreditCheckbox = By
//			.xpath("//label[text()='Apply credits up to zero amount payment']/preceding-sibling::input");
//	By reviewInstallmentsCheckbox = By.xpath("//label[text()='Review installments']/preceding-sibling::input");
//	By createPaymentsCheckbox = By.xpath("//label[text()='Create payment files immediately']/preceding-sibling::input");
	By applyCreditCheckbox = By.xpath("//label[text()='Apply credits up to zero amount payment']");
	By reviewInstallmentsCheckbox = By.xpath("//label[text()='Review installments']");
	By createPaymentsCheckbox = By.xpath("//label[text()='Create payment files immediately']");

	By paymentProcessProfileInput = By.xpath("//label[text()='Payment Process Profile']/preceding-sibling::input");

	By enterDisbursementBankAccount = By.xpath("//input[contains(@name,'bankAccountNameId')]");

	public void validateSubmitPaymentProcessPageTitle() throws Exception {

		try {
			implWait(driver);
			String actualText = driver.findElement(submitPaymentProcessTitle).getText().trim();
			waitTime(driver);

			grep.infoTest("Submit Payment Process Request Page Header: " + actualText);
			logger.info("Submit Payment Process Request Page Header: " + actualText);

			validAssert.equalsAssert(actualText, "Submit Payment Process Request");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterInvoiceNumber_InSubmitPaymentProcessPage(String invoiceVal) {
		try {
			implWait(driver);

			driver.findElement(inputPaymentProcessName).click();
			driver.findElement(inputPaymentProcessName).sendKeys(invoiceVal);
			waitTime(driver);
			grep.infoTest("Entering Payment Process Name: " + invoiceVal);
			logger.info("Entering Payment Process Name: " + invoiceVal);
			waitTime(driver);
			actionTab();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectTemplate(String templateVal) {
		try {
			implWait(driver);
			By selectTemplate = By.xpath("//li[starts-with(text(),'" + templateVal + "')]");

			driver.findElement(insertTemplateName).click();
			driver.findElement(insertTemplateName).sendKeys(templateVal);
			waitTime(driver);
			grep.infoTest("Selecting Supplier: " + templateVal);
			logger.info("Selecting Supplier: " + templateVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectTemplate, 20);
			driver.findElement(selectTemplate).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isPaymentRadioButtonSelected() {
		try {
			implWait(driver);

			WebElement radioButton = driver.findElement(currencyPayment);

			// 1. Check the standard Selenium state
			boolean isSelected = radioButton.isSelected();

			// 2. Check the 'checked' attribute (often used in web forms)
			String checkedAttr = radioButton.getAttribute("checked");

			boolean result = isSelected || "true".equals(checkedAttr);

			System.out.println("Radio Button Checked State: " + result);
			grep.infoTest("Radio Button Checked State: " + result);
			logger.info("Radio Button Checked State: " + result);
			return result;

		} catch (Exception e) {
			System.err.println("Could not verify radio button state: " + e.getMessage());
			return false;
		}
	}

	public void clickPaymentAndProcessingOptionTab() {
//		implWait(driver);

		scrollView(insertTemplateName);
		driver.findElement(paymentProcessOptions).click();

	}

	public boolean verifyBankAccountName(String expectedValue) {
		try {
			waitForElement(bankAccount, 20);

			// 1. Wait for the element to be present
			WebElement inputField = driver.findElement(bankAccount);

			// 2. Retrieve the 'value' attribute
			String actualValue = inputField.getAttribute("value");

			// 3. Comparison
			if (actualValue != null && actualValue.equals(expectedValue)) {
				System.out.println("Verification Passed: Value is '" + actualValue + "'");
				grep.infoTest("Verification Passed: Value is '" + actualValue + "'");
				logger.info("Verification Passed: Value is '" + actualValue + "'");
				return true;
			} else {
				System.out.println(
						"Verification Failed: Expected '" + expectedValue + "' but found '" + actualValue + "'");
				grep.failTest("Verification Passed: Value is '" + actualValue + "'");
				logger.error("Verification Passed: Value is '" + actualValue + "'");
				return false;
			}

		} catch (Exception e) {
			System.err.println("Element not found or not accessible: " + e.getMessage());
			grep.failTest("Element not found or not accessible: " + e.getMessage());
			logger.error("Element not found or not accessible: " + e.getMessage());
			return false;
		}
	}

	public void clickSubmitBtn_inSubmitPaymentProcess() {
		implWait(driver);

		driver.findElement(submitBtn_inSubmitPaymentProcess).click();

	}

	public void clickRefreshPaymentProcessBtn() {
		implWait(driver);

		driver.findElement(paymentRefreshBtn).click();

	}

	public void waitForPaymentRecordAndStatus(String targetRecord, String expectedStatus, int timeoutInMinutes) {
		implWait(driver);
		// 1. Define local locators to use the parameter
		By paymentStatusLoc = By
				.xpath("//a[text()='" + targetRecord + "']/ancestor::td[1]/following-sibling::td[3]//span");
		By reviewInstallmentActionBtnLoc = By.xpath("//a[text()='" + targetRecord
				+ "']/ancestor::td[1]/following-sibling::td[4]//a[contains(@id, 'commandImageLink1_1')]");

		long endTime = System.currentTimeMillis() + (timeoutInMinutes * 60 * 1000);
		boolean isReady = false;

		System.out.println("Starting poll for record: " + targetRecord);
		logger.info("Starting poll for record: " + targetRecord);
		grep.infoTest("Starting poll for record: " + targetRecord);

		while (System.currentTimeMillis() < endTime) {
			try {
				// 2. Check if record exists and has the correct status
				WebElement statusElement = driver.findElement(paymentStatusLoc);
				WebElement actionButton = driver.findElement(reviewInstallmentActionBtnLoc);

				String actualStatus = statusElement.getText().trim();

				if (actualStatus.equalsIgnoreCase(expectedStatus) && actionButton.isDisplayed()) {
					System.out.println("Condition Met! Status: " + actualStatus);
					logger.info("Condition Met! Status: " + actualStatus);
					grep.infoTest("Condition Met! Status: " + actualStatus);
					isReady = true;
					waitTime(driver);
					grep.captureScreenshot("pass",
							"Validating Record in Payment Process Request Page for " + expectedStatus,
							"recordIn_PaymentProcessRequest_" + expectedStatus + "_Page");
					waitTime(driver);
					driver.findElement(reviewInstallmentActionBtnLoc).click();
					waitTime(driver);
					break;
				}
			} catch (Exception e) {
				// Record or status not found yet, which is expected during polling
				System.out.println("Record not ready. Clicking refresh...");
				logger.error("Record not ready. Clicking refresh...");
				grep.infoTest("Record not ready. Clicking refresh...");
			}

			// 3. Click Refresh
			try {
				driver.findElement(paymentRefreshBtn).click();
			} catch (Exception e) {
				// Fallback for intercepted refresh button
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",
						driver.findElement(paymentRefreshBtn));
			}

			// 4. Wait 10-15 seconds before the next attempt
			try {
				waitTime30(driver);

			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
		}

		if (!isReady) {
			System.out.println("Timeout: Record '" + targetRecord + "' did not reach status '" + expectedStatus
					+ "' within " + timeoutInMinutes + " minutes.");
			logger.error("Timeout: Record '" + targetRecord + "' did not reach status '" + expectedStatus + "' within "
					+ timeoutInMinutes + " minutes.");
			grep.failTest("Timeout: Record '" + targetRecord + "' did not reach status '" + expectedStatus + "' within "
					+ timeoutInMinutes + " minutes.");
		}
	}

	public void waitForPaymentStatusAndExpand(String targetRecord, String expectedStatus, int timeoutInMinutes) {
		implWait(driver);
		// 1. Define local locators to use the parameter
		By paymentStatusLoc = By
				.xpath("//a[text()='" + targetRecord + "']/ancestor::td[1]/following-sibling::td[3]//span");
		By expandPaymentLoc = By.xpath("//a[text()='" + targetRecord + "']/ancestor::td[1]/div/span/a");

		long endTime = System.currentTimeMillis() + (timeoutInMinutes * 60 * 1000);
		boolean isReady = false;

		System.out.println("Starting poll for record: " + targetRecord);
		logger.info("Starting poll for record: " + targetRecord);
		grep.infoTest("Starting poll for record: " + targetRecord);

		while (System.currentTimeMillis() < endTime) {
			try {
				// 2. Check if record exists and has the correct status
				WebElement statusElement = driver.findElement(paymentStatusLoc);
				WebElement expandEle = driver.findElement(expandPaymentLoc);

				String actualStatus = statusElement.getText().trim();

				if (actualStatus.equalsIgnoreCase(expectedStatus)) {
					System.out.println("Condition Met! Status: " + actualStatus);
					logger.info("Condition Met! Status: " + actualStatus);
					grep.infoTest("Condition Met! Status: " + actualStatus);
					isReady = true;
					waitTime(driver);
					if (expandEle.getAttribute("title").equals("Expand")) {
						expandEle.click();

						grep.captureScreenshot("pass", "Validating Payment File is generated at " + expectedStatus,
								"paymentFile" + expectedStatus + "_Page");
					}
					break;
				}
			} catch (Exception e) {
				// Record or status not found yet, which is expected during polling
				System.out.println("Record not ready. Clicking refresh...");
				logger.error("Record not ready. Clicking refresh...");
				grep.infoTest("Record not ready. Clicking refresh...");
			}

			// 3. Click Refresh
			try {
				driver.findElement(paymentRefreshBtn).click();
			} catch (Exception e) {
				// Fallback for intercepted refresh button
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",
						driver.findElement(paymentRefreshBtn));
			}

			// 4. Wait 10-15 seconds before the next attempt
			try {
				waitTime30(driver);

			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
		}

		if (!isReady) {
			System.out.println("Timeout: Record '" + targetRecord + "' did not reach status '" + expectedStatus
					+ "' within " + timeoutInMinutes + " minutes.");
			logger.error("Timeout: Record '" + targetRecord + "' did not reach status '" + expectedStatus + "' within "
					+ timeoutInMinutes + " minutes.");
			grep.failTest("Timeout: Record '" + targetRecord + "' did not reach status '" + expectedStatus + "' within "
					+ timeoutInMinutes + " minutes.");
		}
	}

	public void keepFirstRowAndRemoveOthersFromSelectInstallemnts() throws Exception {
//		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			// 1. Select the First Row to ensure focus (using the row index _afrrk)
			// Note: Based on your HTML, the first displayed row has _afrrk="1"

			waitForElementToBeClickable(firstRow_inSelectInstallments, 30);

			driver.findElement(firstRow_inSelectInstallments).click();
			waitTime(driver);

			// 2. Select All Rows (Assuming standard ADF 'Select All' icon in header)
			// If there is no 'Select All' button, we can use a Keyboard shortcut:
			// Ctrl+A is common in ADF tables.
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
			waitTime2(driver);

			// 3. Deselect the First Row (Ctrl + Click)
			actions.keyDown(Keys.CONTROL).click(driver.findElement(firstRow_inSelectInstallments)).keyUp(Keys.CONTROL)
					.perform();

			// 4. Click the Remove Button
			waitForElementToBeClickable(removeSelectedInstallmentsBtn, 20);
			driver.findElement(removeSelectedInstallmentsBtn).click();

			// Using JS click as ADF toolbar buttons are often intercepted by transparent
			// layers
			waitTime2(driver);
			grep.captureScreenshot("pass", "Removing the additional invoices", "RemoveAdditionalInvoices");
			waitTime(driver);

			driver.findElement(okBtn_ForRemoveSelectedInstallments);

			System.out.println("Cleanup complete: Remaining rows removed.");
			grep.infoTest("Cleanup complete: Remaining rows removed.");

		} catch (Exception e) {
			System.err.println("Failed to remove rows: " + e.getMessage());
		}
	}

	public void clickSave_inSelectInstallments() {
		implWait(driver);

		driver.findElement(saveSelectedInsallments).click();

	}

	public void clickResumePaymentButton() {
		waitForElementToBeClickable(resumePaymentProcessButton, 20);

		driver.findElement(resumePaymentProcessButton).click();

	}

	public String getPaymentProcessRequestNumber(String payNumVal) throws Exception {
		String payNumber = null;
		try {
			By paymentRequestNum = By.xpath("//a[text()='" + payNumVal
					+ "']/ancestor::table[2]/following-sibling::table/descendant::a[@title='Payment Process Request']");

			payNumber = driver.findElement(paymentRequestNum).getText();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		return payNumber;
	}

	public void waitForPrintPaymentActionAndStatus(String targetRecord, String expectedStatus, int timeoutInMinutes) {
		implWait(driver);
		// 1. Define local locators to use the parameter
		By paymentStatusLoc = By
				.xpath("//a[text()='" + targetRecord + "']/ancestor::td[1]/following-sibling::td[3]/span");
		By printPaymentActionBtnLoc = By.xpath("//a[text()='" + targetRecord
				+ "']/ancestor::td[1]/following-sibling::td[4]/span/a/img[@alt='Print payment documents']");

		long endTime = System.currentTimeMillis() + (timeoutInMinutes * 60 * 1000);
		boolean isReady = false;

		System.out.println("Starting poll for record: " + targetRecord);
		logger.info("Starting poll for record: " + targetRecord);
		grep.infoTest("Starting poll for record: " + targetRecord);

		while (System.currentTimeMillis() < endTime) {
			try {
				// 2. Check if record exists and has the correct status
				WebElement statusElement = driver.findElement(paymentStatusLoc);
				WebElement actionButton = driver.findElement(printPaymentActionBtnLoc);

				String actualStatus = statusElement.getText().trim();

				if (actualStatus.equalsIgnoreCase(expectedStatus) && actionButton.isDisplayed()) {
					System.out.println("Condition Met! Status: " + actualStatus);
					logger.info("Condition Met! Status: " + actualStatus);
					grep.infoTest("Condition Met! Status: " + actualStatus);
					isReady = true;
					waitTime(driver);
					grep.captureScreenshot("pass", "Validating Payment Process Request Page for " + expectedStatus,
							"recordIn_PaymentProcessRequest_" + expectedStatus + "_Page");
					waitTime(driver);
					driver.findElement(printPaymentActionBtnLoc).click();
					waitTime(driver);
					break;
				}
			} catch (Exception e) {
				// Record or status not found yet, which is expected during polling
				System.out.println("Record not ready. Clicking refresh...");
				logger.error("Record not ready. Clicking refresh...");
				grep.infoTest("Record not ready. Clicking refresh...");
			}

			// 3. Click Refresh
			try {
				driver.findElement(paymentRefreshBtn).click();
			} catch (Exception e) {
				// Fallback for intercepted refresh button
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",
						driver.findElement(paymentRefreshBtn));
			}

			// 4. Wait 10-15 seconds before the next attempt
			try {
				waitTime30(driver);

			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
		}

		if (!isReady) {
			System.out.println("Timeout: Record '" + targetRecord + "' did not reach status '" + expectedStatus
					+ "' within " + timeoutInMinutes + " minutes.");
			logger.error("Timeout: Record '" + targetRecord + "' did not reach status '" + expectedStatus + "' within "
					+ timeoutInMinutes + " minutes.");
			grep.failTest("Timeout: Record '" + targetRecord + "' did not reach status '" + expectedStatus + "' within "
					+ timeoutInMinutes + " minutes.");
		}
	}

	public void clickPrintButton() {
		waitForElementToBeClickable(printButton, 20);

		driver.findElement(printButton).click();

	}

	public void validatePaymentFileDetails(String fileName, String adminRefName, String status) throws Exception {
		try {
			WebElement file = driver.findElement(paymentFileHeader);
			WebElement admin = driver.findElement(adminReference);
			WebElement fileStatus = driver.findElement(paymentFileStatus);

			grep.infoTest("Validating the File Header: " + file.getText());
			logger.info("Validating the Payment File Header: " + file.getText());

			grep.infoTest("Validating the Payment Administrator References: " + admin.getText());
			logger.info("Validating the Payment  Administrator References: " + admin.getText());

			grep.infoTest("Validating the Payment File Status: " + fileStatus.getText());
			logger.info("Validating the Payment File Status: " + fileStatus.getText());

			validAssert.trueAssert(file.getText().contains(fileName));
			validAssert.equalsAssert(admin.getText(), adminRefName);
			validAssert.equalsAssert(fileStatus.getText(), status);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void clickRecordPrintStatusButton() {
		waitForElementToBeClickable(recordPrintStatusBtn, 20);

		driver.findElement(recordPrintStatusBtn).click();

	}

	public void clickSubmitRecordPrintFileButton() {
		waitForElementToBeClickable(submitRecordPrint, 20);

		driver.findElement(submitRecordPrint).click();

	}

	public void clickRecordPrintStatusWarningButton() {
		waitForElementToBeClickable(recordPrintStatus_WarnBtn, 20);

		driver.findElement(recordPrintStatus_WarnBtn).click();

	}

	public void clickRecentlyCompletedTab() {
		waitForElementToBeClickable(recentlyCompletedTab, 20);

		driver.findElement(recentlyCompletedTab).click();

	}

	public void clickRecentlyCompletedRefreshButton() {
		waitForElementToBeClickable(completedRefreshBtn, 20);

		driver.findElement(completedRefreshBtn).click();

	}

	public void verifyPaymentNumber_inCompletedTab(String targetRecord) {
		implWait(driver);
		// 1. Define local locators to use the parameter
		By verifyRecord = By.xpath("//a[text()='" + targetRecord + "']");

		try {
			// 2. Check if record exists and has the correct status
			WebElement statusElement = driver.findElement(verifyRecord);

			if (statusElement.isDisplayed()) {
				logger.info("Record is Displayed in Recently Completed tab :" + targetRecord);
				grep.infoTest("Record is Displayed in Recently Completed tab :" + targetRecord);
			}
		} catch (Exception e) {
			// Record or status not found yet, which is expected during polling
			System.out.println("Record not ready. Clicking refresh...");
			logger.error("Record not ready. Clicking refresh...");
			grep.infoTest("Record not ready. Clicking refresh...");
		}
	}

	// ELECTRONIC METHOD

	public void selectBusinessUnitPaymentRadio() {
		try {
			// 1. Wait for the radio button to be present in the DOM
			waitForElement(businessUnitRadioBtn, 20);
			WebElement radioBtn = driver.findElement(businessUnitRadioBtn);

			logger.info("Selecting Business Unit 'Payment' radio button.");
			grep.infoTest("Selecting Business Unit 'Payment' radio button.");
			radioBtn.click();

			// 5. Optional: Wait for Partial Page Refresh (PPR) if the UI updates
			waitTime(driver);

		} catch (Exception e) {
			logger.error("Failed to select Business Unit radio button: " + e.getMessage());
			grep.failTest("Radio button selection failed for: Business Unit Payment");
		}
	}

	public void clickBusinessUnitAddRow() {
		try {

			waitForElement(businessUnit_AddRowBtn, 20);
			WebElement addRowBtn = driver.findElement(businessUnit_AddRowBtn);

			logger.info("Clicking Add Row button for Business Units.");
			grep.infoTest("Clicking Add Row button for Business Units.");

			addRowBtn.click();
			waitTime(driver);

		} catch (Exception e) {
			logger.error("Failed to click Business Unit Add Row button: " + e.getMessage());
			grep.failTest("Add Row button click failed.");
		}
	}

	public void searchAndSelectBusinessUnit(String unitVal) {
		try {
			implWait(driver);
			By selectUnit = By.xpath("//li[starts-with(text(),'" + unitVal + "')]");

			driver.findElement(businessUnit_Name).click();
			driver.findElement(businessUnit_Name).sendKeys(unitVal);
			waitTime(driver);
			grep.infoTest("Selecting Business Unit: " + unitVal);
			logger.info("Selecting Business Unit: " + unitVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectUnit, 20);
			driver.findElement(selectUnit).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectLegalEntitiesRadio() {
		try {
			// 1. Wait for the radio button to be present in the DOM
			waitForElement(legalEntityRadioBtn, 20);
			WebElement radioBtn = driver.findElement(legalEntityRadioBtn);

			logger.info("Selecting Legal Entities 'Specific' radio button.");
			grep.infoTest("Selecting Legal Entities 'Specific' radio button.");
			radioBtn.click();

			// 5. Optional: Wait for Partial Page Refresh (PPR) if the UI updates
			waitTime(driver);

		} catch (Exception e) {
			logger.error("Failed to select Legal Entities radio button: " + e.getMessage());
			grep.failTest("Radio button selection failed for:Legal Entities Specific");
		}
	}

	public void clickLegalEntityAddRow() {
		try {

			waitForElement(legalEntity_AddRowBtn, 20);
			WebElement addRowBtn = driver.findElement(legalEntity_AddRowBtn);

			logger.info("Clicking Add Row button for Legal Entities.");
			grep.infoTest("Clicking Add Row button for Legal Entities.");

			addRowBtn.click();
			waitTime(driver);

		} catch (Exception e) {
			logger.error("Failed to click  Legal Entities Add Row button: " + e.getMessage());
			grep.failTest("Add Row button click failed.");
		}
	}

	public void searchAndSelectLegalEntities(String legalVal) {
		try {
			implWait(driver);
			By selectEntities = By.xpath("//li[starts-with(text(),'" + legalVal + "')]");

			driver.findElement(legalEntityName).click();
			driver.findElement(legalEntityName).sendKeys(legalVal);
			waitTime(driver);
			grep.infoTest("Selecting Legal entities: " + legalVal);
			logger.info("Selecting Legal entities: " + legalVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectEntities, 20);
			driver.findElement(selectEntities).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectSupplierOrParty(String supplyVal) {
		try {
			implWait(driver);
			By selectSupplier = By.xpath("//li[starts-with(text(),'" + supplyVal + "')]");

			driver.findElement(supplierOrParty).click();
			driver.findElement(supplierOrParty).sendKeys(supplyVal);
			waitTime(driver);
			grep.infoTest("Selecting Supplier or Party: " + supplyVal);
			logger.info("Selecting Supplier or Party: " + supplyVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectSupplier, 20);
			driver.findElement(selectSupplier).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enableApplyCredits() {
		implWait(driver);

		WebElement checkbox = driver.findElement(applyCreditCheckbox);
		// ADF Check: value 't' or property 'checked'
		boolean isChecked = checkbox.isSelected() || "t".equals(checkbox.getAttribute("value"));

		if (!isChecked) {
//	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
			checkbox.click();
			logger.info("Applied Credits checkbox enabled.");
			grep.infoTest("Applied Credits checkbox enabled.");
		} else {
			logger.info("Applied Credits checkbox was already enabled.");
			grep.infoTest("Applied Credits checkbox was already enabled.");
		}
	}

	public void enableReviewInstallments() {
		WebElement checkbox = driver.findElement(reviewInstallmentsCheckbox);
		boolean isChecked = checkbox.isSelected() || "t".equals(checkbox.getAttribute("value"));

		if (!isChecked) {
//	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
			checkbox.click();
			logger.info("Review Installments checkbox enabled.");
			grep.infoTest("Review Installments checkbox enabled.");
		} else {
			logger.info("Review Installments checkbox was already enabled.");
			grep.infoTest("Review Installments checkbox was already enabled.");
		}
	}

	public void enableCreatePaymentFiles() {
		WebElement checkbox = driver.findElement(createPaymentsCheckbox);
		boolean isChecked = checkbox.isSelected() || "t".equals(checkbox.getAttribute("value"));

		if (!isChecked) {
//			((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
			checkbox.click();
			logger.info("Create Payment Files checkbox enabled.");
			grep.infoTest("Create Payment Files checkbox enabled.");
		} else {
			logger.info("Create Payment Files checkbox was already enabled.");
			grep.infoTest("Create Payment Files checkbox was already enabled.");
		}
	}

	public void searchAndSelectDisbursementBankAccount(String bankAccVal) {
		try {
			implWait(driver);
			By selectBankAcct = By.xpath("//li[starts-with(text(),'" + bankAccVal + "')]");

			driver.findElement(enterDisbursementBankAccount).click();
			driver.findElement(enterDisbursementBankAccount).sendKeys(bankAccVal);
			waitTime(driver);
			grep.infoTest("Selecting Bank Account: " + bankAccVal);
			logger.info("Selecting Bank Account: " + bankAccVal);
			waitForElementToBeClickable(selectBankAcct, 20);
			driver.findElement(selectBankAcct).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectPaymentProcess(String profileVal) {
		try {
			implWait(driver);
			By selectProcess = By.xpath("//li[starts-with(text(),'" + profileVal + "')]");

			driver.findElement(paymentProcessProfileInput).click();
			driver.findElement(paymentProcessProfileInput).sendKeys(profileVal);
			waitTime(driver);
			grep.infoTest("Selecting Payment Process Profile: " + profileVal);
			logger.info("Selecting Payment Process Profile: " + profileVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectProcess, 20);
			driver.findElement(selectProcess).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
