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

}
