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

public class Oracle_InvoicePage extends WaitsManager {
	protected WebDriver driver;
	private static Logger logger = LogManager.getLogger(Oracle_InvoicePage.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();

	public Oracle_InvoicePage() {
		this.driver = DriverManager.getDriver();
	}

	// Invoice page
	By invoicePageTitle = By.xpath("//div[@title='Invoices']");
	By createInvoicePageTitle = By.xpath("//div[@title='Create Invoice: ']");
	By insertBusinessUnit = By.xpath("//label[text()='Business Unit']/parent::td/following-sibling::td/span/input");
	By insertSupplier = By.xpath("//label[text()='Supplier']/parent::td/following-sibling::td/input");
	By invoiceNumber = By.xpath("//label[text()='Number']/parent::td/following-sibling::td/input");
	By invoiceCurrency = By.xpath("//label[text()='Invoice Currency']/preceding-sibling::select");
	By invoiceAmount = By.xpath("//label[text()='Amount']/parent::td/following-sibling::td/descendant::input");
	By description = By.xpath("//label[text()='Description']/parent::td/following-sibling::td/textarea");
	By paymentTerms = By.xpath("//label[text()='Payment Terms']/parent::td/following-sibling::td/span/input");
	By invoiceReceiveDate = By.xpath("//label[text()='Invoice Received']/parent::td/following-sibling::td/input");

	// lines in invoice
	By lines = By.xpath("//a[@title='Expand Lines']");
	By selectLine = By.xpath("//table[@summary='Invoice Lines']/descendant::td[text()='1']");
//	By lineAmount = By.xpath("//td[@title='Amount']/descendant::input");
//	By lineAmount = By.xpath("//td[@title='Amount']/descendant::label[text()='Amount']/preceding-sibling::input");
	By lineAmount = By.xpath("//tr[@_afrrk='0']//label[text()='Amount']/preceding-sibling::input");

//	By distributionCombination = By.xpath("//td[@title='Distribution Combination ID']/span/descendant::a[@title='Select: Distribution Combination ID']");
	By distributionCombination = By.xpath(
			"//input[@aria-label='Distribution Combination ID']/parent::span/parent::td/following-sibling::td/a");
	By enterCompany_inDCPopup = By.xpath("//label[text()='COMPANY']/preceding-sibling::input");
	By searchBtn_inDCPopup = By.xpath("//button[@accesskey='r']");
	By okBtn_inDCPopup = By.xpath("//button[@accesskey='k']");
	By getDistributionCombination_ID = By.xpath("//input[@aria-label='Distribution Combination ID']");

	By saveInvoice = By.xpath("//div[@class='xeq p_AFTextOnly']/a/span[text()='Save']");
	By invoiceActionBtn = By.xpath("//a[text()='Invoice Actions']");

	By validateMsg = By.xpath("//a[@accesskey='Q']");
	By continueWarn = By.xpath("//button[text()='Continue']");
	// validation
	By warningImageLocator = By.xpath("//table[@summary='Holds']//img[contains(@src, 'qual_warning_16')]");
	By associatedLinkLocator = By.xpath(".//following-sibling::a");
	By selectName = By.xpath("//span[label[text()='Name'] and not(contains(@class, 'p_AFDisabled'))]/select");
	By saveAndCloseBtn = By.xpath("//button[text()='Save and Close']");

	// Accounting the invoice
	By viewAccbtn = By.xpath("//button[text()='View Accounting']");
	By text_AccountingConfirmation = By.xpath("//div[@class='AFPopupSelector']/descendant::td[@class='x1o']");

	By accountinLineHeader = By.xpath("//div[contains(@id, 'ap1:d3::_ttxt')]");
	By accoutNum = By.xpath("//span[contains(@id, 'kf1CS2::content')]");
	By debitAmt = By.xpath("//span[contains(@id, 'ATp:t1:0:ot4')]");
	By creditAmt = By.xpath(
			"//*[@id=\"pt1:_FOr1:1:_FONSr2:0:MAnt2:1:pm1:r1:0:ap1:r7:1:AT1:_ATp:t1::db\"]/table/tbody/tr[2]/td[8]/span/span");

	By doneBtn = By.xpath("//button[@accesskey='o']");

	// payments for invoice using manage installments
	By paymentReasonComment = By.xpath("//input[@title='Supplier Expenses Payment']");
	By paymentMethod = By.xpath("//input[contains(@name,'paymentMethodNameId2')]");
	
	By paymentSaveAndCloseBtn = By.xpath("//button[@accesskey='S']");

	// PAY IN FULL
	By enterBankAccount = By.xpath("//input[contains(@name,'bankAccountNamePIFId')]");

	public void validateInvoicePageTitle() throws Exception {

		try {
			implWait(driver);
			String actualText = driver.findElement(invoicePageTitle).getText().trim();
			waitTime(driver);

			grep.infoTest("Invoice Page Header: " + actualText);
			validAssert.equalsAssert(actualText, "Invoices");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateCreateInvoicePageTitle() throws Exception {

		try {
//			implWait(driver);
			waitForElement(createInvoicePageTitle, 120);
			String actualText = driver.findElement(createInvoicePageTitle).getText().trim();
			waitTime(driver);

			grep.infoTest("Invoice Page Header: " + actualText);
			validAssert.equalsAssert(actualText, "Create Invoice:");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectBusinessUnit(String businessUnitVal) {
		try {
			implWait(driver);
			By selectBusinessUnit = By.xpath("//li[text()='" + businessUnitVal + "']");

			driver.findElement(insertBusinessUnit).click();
			driver.findElement(insertBusinessUnit).sendKeys(businessUnitVal);
			waitTime(driver);
			grep.infoTest("Selecting Business Unit: " + businessUnitVal);
			logger.info("Selecting Business Unit: " + businessUnitVal);
			waitForElementToBeClickable(selectBusinessUnit, 20);
			driver.findElement(selectBusinessUnit).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectSupplier(String supplierVal) {
		try {
			implWait(driver);
			By selectSupplier = By.xpath("//li[starts-with(text(),'" + supplierVal + "')]");

			driver.findElement(insertSupplier).click();
			driver.findElement(insertSupplier).sendKeys(supplierVal);
			waitTime(driver);
			grep.infoTest("Selecting Supplier: " + supplierVal);
			logger.info("Selecting Supplier: " + supplierVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectSupplier, 20);
			driver.findElement(selectSupplier).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterInvoiceNumber(String invoiceVal) {
		try {
			implWait(driver);

			driver.findElement(invoiceNumber).click();
			driver.findElement(invoiceNumber).sendKeys(invoiceVal);
			waitTime(driver);
			grep.infoTest("Entering Invoice Number: " + invoiceVal);
			logger.info("Entering Invoice Number: " + invoiceVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterInvoiceAmount(String currencyCode, String amount) throws Exception {
		try {
			// 1. Handle Currency Dropdown
			waitForElementToBeClickable(invoiceCurrency, 60);
			WebElement currencyDropdown = driver.findElement(invoiceCurrency);
			Select currencySelect = new Select(currencyDropdown);

			System.out.println("Selecting Invoice Currency: " + currencyCode);
			grep.infoTest("Selecting Invoice Currency: " + currencyCode);
			logger.info("Selecting Invoice Currency: " + currencyCode);
			currencySelect.selectByContainsVisibleText(currencyCode);

			// 2. Handle Amount Input
			// Note: We re-wait because selecting currency sometimes disables/refreshes
			// fields
			WebElement amountInput = driver.findElement(invoiceAmount);

			System.out.println("Entering Invoice Amount: " + amount);
			grep.infoTest("Entering Invoice Amount: " + amount);
			logger.info("Entering Invoice Amount: " + amount);
			amountInput.clear(); // Clear existing value if any
			amountInput.sendKeys(amount);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterInvoiceDescription(String descVal) {
		try {
			implWait(driver);

			driver.findElement(description).click();
			driver.findElement(description).sendKeys(descVal);
			waitTime(driver);
			grep.infoTest("Entering Invoice Description: " + descVal);
			logger.info("Entering Invoice Description: " + descVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectPaymentTerms(String paymentTermsVal) {
		try {
			implWait(driver);
			By selectPaymentTerms = By.xpath("//li[text()='" + paymentTermsVal + "']");

			driver.findElement(paymentTerms).click();
			driver.findElement(paymentTerms).clear();
			driver.findElement(paymentTerms).sendKeys(paymentTermsVal);
			waitTime(driver);
			grep.infoTest("Selecting Invoice Payment terms: " + paymentTermsVal);
			logger.info("Selecting Invoice Payment terms: " + paymentTermsVal);
			waitTime(driver);
			driver.findElement(selectPaymentTerms).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterInvoiceReceiveDate(String date, String month, String year) {
		try {
			implWait(driver);
			driver.findElement(invoiceReceiveDate).click();
			String dateVal = date + "-" + month + "-" + year;
			driver.findElement(invoiceReceiveDate).sendKeys(dateVal);
			waitTime(driver);
			grep.infoTest("Entering Invoice Receive Date: " + dateVal);
			logger.info("Entering Invoice Receive Date: " + dateVal);
			actionTab();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void expandLinesSection() {
		try {
			implWait(driver);

			driver.findElement(lines).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterAmountInLines(String amount) throws Exception {
		try {
			implWait(driver);

			driver.findElement(selectLine).click();
			waitTime2(driver);
			actionTab();
			waitTime2(driver);

//			WebElement amountInput = driver.findElement(lineAmount);
//			amountInput.sendKeys(amount);
			driver.findElement(lineAmount).click();
			driver.findElement(lineAmount).sendKeys(amount);

			System.out.println("Entering Line Amount: " + amount);
			grep.infoTest("Entering Line Amount: " + amount);
			logger.info("Entering Line Amount: " + amount);
			waitTime(driver);
			actionTab();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectDistributionCombination(String companyVal, String accValue) {
		try {
			implWait(driver);

			By selectCompany_inDCPopup = By.xpath("//span[text()='" + companyVal + "']");
			By selectAcc_inDCPopup = By.xpath("//span[text()='" + accValue + "']");

			waitTime2(driver);
			driver.findElement(distributionCombination).click();
			waitForElementToBeClickable(enterCompany_inDCPopup, 20);
			driver.findElement(enterCompany_inDCPopup).sendKeys(companyVal);
			waitTime(driver);
			grep.infoTest("Selecting Comapany Value: " + companyVal);
			logger.info("Selecting Comapany Value: " + companyVal);
			waitTime(driver);
			driver.findElement(selectCompany_inDCPopup).click();
			waitTime(driver);
			driver.findElement(searchBtn_inDCPopup).click();
			waitTime(driver);

			grep.infoTest("Selecting Account for Distribution Combinantion: " + accValue);
			logger.info("Selecting Account for Distribution Combinantion: " + accValue);
			waitTime(driver);
			driver.findElement(selectAcc_inDCPopup).click();
			waitTime(driver);
			driver.findElement(okBtn_inDCPopup).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String retrieveDistributionCombinationID() throws Exception {
		String getDC_Id = null;

		try {

			waitTime1(driver);
			getDC_Id = driver.findElement(getDistributionCombination_ID).getAttribute("value");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getDC_Id;
	}

	public void clickSaveInvoiceBtn() {
		implWait(driver);

		driver.findElement(saveInvoice).click();
	}

	public void clickContinueWarnBtn() {
		try {

			implWait(driver);
			WebElement continueBtn = driver.findElement(continueWarn);
			if (continueBtn.isDisplayed()) {
				continueBtn.click();
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void clickInvoiceActionAndValidateBtn(String actionVal) throws Exception {
		try {
			implWait(driver);
			By invoiceActBtn = By.xpath("//td[text()='" + actionVal + "']");

			driver.findElement(invoiceActionBtn).click();
			waitTime1(driver);
			driver.findElement(invoiceActBtn).click();
			waitTime1(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getInvoiceValidation() throws Exception {
		String invoiceValidation = null;

		try {

			waitForElement(validateMsg, 60);
			invoiceValidation = driver.findElement(validateMsg).getText();
			System.out.println("Invoice validation message: " + invoiceValidation);
			grep.infoTest("Invoice validation message: " + invoiceValidation);
			logger.info("Invoice validation message: " + invoiceValidation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoiceValidation;
	}

	public void clickNeedReValidation() throws Exception {
		try {

			waitForElement(validateMsg, 20);
			driver.findElement(validateMsg).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickHoldWarningLink() throws Exception {

		try {
			// 1. Check if any warning image exists
			List<WebElement> warningImages = driver.findElements(warningImageLocator);

			if (!warningImages.isEmpty()) {
				WebElement warningImg = warningImages.get(0);
				System.out.println("Warning found! Image source: " + warningImg.getAttribute("src"));
				grep.infoTest("Warning found! Image source: " + warningImg.getAttribute("src"));
				logger.info("Warning found! Image source: " + warningImg.getAttribute("src"));

				// 2. Find the link relative to the warning image
				WebElement countLink = warningImg.findElement(associatedLinkLocator);

				System.out.println("Clicking the hold count link: " + countLink.getText());
				grep.infoTest("Clicking the hold count link: " + countLink.getText());
				logger.info("Clicking the hold count link: " + countLink.getText());

				countLink.click();
			} else {
				System.out.println("No warnings found in the Holds table. All checks passed.");
			}
		} catch (Exception e) {
			System.out.println("Error while processing Holds table: " + e.getMessage());
			throw e;
		}
	}

	public void selectValidatedReleaseName() throws Exception {
		try {
			// 1. Wait until the dropdown is visible and clickable
			waitForElementToBeClickable(selectName, 60);
			WebElement dropdownElement = driver.findElement(selectName);

			// 2. Use the Select wrapper
			Select releaseNameSelect = new Select(dropdownElement);

			// 3. Select 'Validated' by the visible text
			releaseNameSelect.selectByVisibleText("Validated");

			System.out.println("Successfully selected 'Validated' from the release name dropdown.");
			grep.infoTest("Successfully selected 'Validated' from the release name dropdown.");
			logger.info("Successfully selected 'Validated' from the release name dropdown.");
		} catch (Exception e) {
			System.err.println("Could not find an enabled 'Name' dropdown. Ensure the row is in edit mode.");
			e.printStackTrace();
		}
	}

	public void clickSaveAndCloseBtn() {
		implWait(driver);

		driver.findElement(saveAndCloseBtn).click();
	}

	// Accounting
	public void validateAccountingConfirmationPopup() throws Exception {
		try {
			implWait(driver);

			String getMsg = driver.findElement(text_AccountingConfirmation).getText().trim();
			validAssert.equalsAssert(getMsg, "The accounting has been completed.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickViewAccountingBtn() {
		try {
			waitForElement(viewAccbtn, 30);
			WebElement accBtn = driver.findElement(viewAccbtn);
			accBtn.click();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void validateAccountingLinesHeader(String expectedInvoiceNum) {

		try {
			// 1. Wait for the dialog header to be visible
			waitForElement(accountinLineHeader, 20);

			WebElement header = driver.findElement(accountinLineHeader);
			String actualHeaderText = header.getText();
			System.out.println("Retrieved Header: " + actualHeaderText);

			// 2. Perform Validation (Case-insensitive)
			if (actualHeaderText.toLowerCase().contains(expectedInvoiceNum.toLowerCase())) {
				logger.info("Validation Passed: Header contains invoice number: " + expectedInvoiceNum);
				grep.infoTest("Validation Passed: Header contains invoice number: " + expectedInvoiceNum);
			} else {
				logger.error("Validation Failed! Expected: " + expectedInvoiceNum + " but found: " + actualHeaderText);
				grep.failTest("Validation Failed! Expected: " + expectedInvoiceNum + " but found: " + actualHeaderText);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAccountCombination(String expectedAccNum) {
		try {
			// Wait for the element to be present and visible
			WebElement accountSpan = driver.findElement(accoutNum);

			String accountValue = accountSpan.getText().trim();
			validAssert.equalsAssert(accountValue, expectedAccNum);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyAccountingAmounts(String externalExpectedAmt) throws Exception {

		// 1. Retrieve the raw text from the UI
		String rawDebit = driver.findElement(debitAmt).getText().trim();
		String rawCredit = driver.findElement(creditAmt).getText().trim();

		// 2. Format conversion logic
		double cleanDebit = Double.parseDouble(rawDebit.replace(".", "").replace(",", "."));
		double cleanCredit = Double.parseDouble(rawCredit.replace(".", "").replace(",", "."));
		double expectedVal = Double.parseDouble(externalExpectedAmt);

		logger.info("Comparing UI Values: Debit[" + cleanDebit + "], Credit[" + cleanCredit + "] against Expected["
				+ expectedVal + "]");
		grep.infoTest("Comparing UI Values: Debit[" + cleanDebit + "], Credit[" + cleanCredit + "] against Expected["
				+ expectedVal + "]");

		// 3. Validation Logic
		if (cleanDebit == cleanCredit) {
			if (cleanDebit == expectedVal) {
				logger.info("Success: Debit, Credit, and Expected amount all match.");
				grep.infoTest("Validation Passed: " + cleanDebit + " matches expected " + expectedVal);
				grep.captureScreenshot("pass", "Final Amount Validation", "Amount_Match_Success");
			} else {
				logger.error("Data Mismatch: UI shows " + cleanDebit + " but External Data expects " + expectedVal);
				grep.captureScreenshot("fail", "External Data Mismatch", "Amount_Mismatch_External");
			}
		} else {
			logger.error("Accounting Mismatch: Debit (" + cleanDebit + ") does not equal Credit (" + cleanCredit + ")");
			grep.captureScreenshot("fail", "Debit Credit Mismatch", "Amount_Mismatch_Internal");
		}
	}

	public void clickDoneAccountingBtn() {
		try {
			waitForElement(doneBtn, 30);
			WebElement done = driver.findElement(doneBtn);
			done.click();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// payment
	public void enterPaymentReasonComment(String reasonVal) {
		try {
			implWait(driver);

			driver.findElement(paymentReasonComment).click();
			driver.findElement(paymentReasonComment).sendKeys(reasonVal);
			waitTime(driver);
			grep.infoTest("Entering Payment reason: " + reasonVal);
			logger.info("Entering Payment reason:" + reasonVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSaveAndClose_Payment_Btn() {
		implWait(driver);

		driver.findElement(paymentSaveAndCloseBtn).click();
	}

	// pay in full
	public void searchAndSelectBankAccount(String bankAccNum) {
		try {
			implWait(driver);
			By selectBankAcct = By.xpath("//li[starts-with(text(),'" + bankAccNum + "']");

			driver.findElement(enterBankAccount).click();
			driver.findElement(enterBankAccount).sendKeys(bankAccNum);
			waitTime(driver);
			grep.infoTest("Selecting Business Unit: " + bankAccNum);
			logger.info("Selecting Business Unit: " + bankAccNum);
			waitForElementToBeClickable(selectBankAcct, 20);
			driver.findElement(selectBankAcct).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
