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
	By manageInvoicePageTitle = By.xpath("//div[@title='Manage Invoices']");
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
	By lineAmount = By.xpath("//tr[@_afrrk='0']//label[text()='Amount']/preceding-sibling::input");

	By distributionCombination = By.xpath(
			"//input[@aria-label='Distribution Combination ID']/parent::span/parent::td/following-sibling::td/a");
	By enterCompany_inDCPopup = By.xpath("//label[text()='COMPANY']/preceding-sibling::input");
	By searchBtn_inDCPopup = By.xpath("//button[@accesskey='r']");
	By okBtn_inDCPopup = By.xpath("//button[@accesskey='k']");
	By getDistributionCombination_ID = By.xpath("//input[@aria-label='Distribution Combination ID']");

	By saveInvoice = By.xpath("//div[@class='xeq p_AFTextOnly']/a/span[text()='Save']");
	By saveAndCloseInvoice = By.xpath("//div[@class='xeq p_AFTextOnly']/a[@accesskey='S']");

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
	By creditAmt = By.xpath("//div[@class='x1hf']/table/tbody/tr[2]/td[8]/span/span");
	By doneBtn = By.xpath("//button[@accesskey='o']");

	// payments for invoice using manage installments
	By paymentReasonComment = By
			.xpath("//label[text()='Payment Reason Comments']/parent::td/following-sibling::td/input");
	By paymentMethod = By.xpath("//input[contains(@name,'paymentMethodNameId2')]");

	By paymentSaveAndCloseBtn = By.xpath("//button[@accesskey='S']");

	// PAY IN FULL
	By enterBankAccount = By.xpath("//input[contains(@name,'bankAccountNamePIFId')]");
	By enterPaymentProfile = By.xpath("//input[contains(@name,'paymentProfileNameId')]");
	By submitBtn_inPaymentPopup = By.xpath("//button[@accesskey='m']");
	By paymentConfirmation = By.xpath("//div[@class='AFPopupSelector']/descendant::td[@class='x1n1']");
	By okBtn_InPaymentConfirmation = By.xpath("//button[text()='OK']");

	// Invoice With PO
	By identifyPO = By.xpath("//label[text()='Identifying PO']/parent::td/following-sibling::td/descendant::input");
	By clickGo = By.xpath("//a[@title='Go']");
	By selectInvoiceLine = By.xpath(
			"//table[@summary='Search Results']/descendant::tr[@_afrrk='0']//descendant::input[@type='checkbox']/following-sibling::label");

	By getAmount = By.xpath("//*[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:pm1:r1:0:ap1:r11:1:at1:_ATp:ta1:o268']");
	By okWarnBtn_inMatchInvoicePopup = By
			.xpath("//*[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:pm1:r1:0:ap1:r11:1:at1:_ATp:ta1:0:cb3']");
	By okBtn_inMatchInvoicePopup = By.xpath("//button[@accesskey='l']/following-sibling::button[@accesskey='K']");
	By applyBtn_inMatchInvoicePopup = By.xpath("//button[@accesskey='l']");
	By homeFromInvoice = By.xpath("//a[@id='_FOpt1:_UIShome']");

	// Payment
	By insertSupplier_inPaymentPage = By
			.xpath("//label[text()='Supplier or Party']/parent::td/following-sibling::td/input");
	By okWarnBtn_inPaymentPage = By.xpath("//button[@accesskey='K']");
	By enterDisbursementBankAccount = By.xpath("//input[contains(@name,'bankAccountNameId')]");
	By paymentMethd_inPayment = By.xpath("//input[contains(@name,'paymentMethodNameUiId')]");
	By enterPaymentProcessProfile = By.xpath("//input[contains(@name,'paymentProfileUICompId')]");
	By selectAndAddBtn = By.xpath("//div[@title='Select and Add']/a");
	By invoiceNum_SelectAndAdd = By.xpath("//input[@aria-label=' Invoice Number']");
	By okBtn_inSelectAndAdd = By
			.xpath("//button[@accesskey='l']/parent::span/following-sibling::button[@accesskey='K']");

	// QUERY INVOICE NUMBER
	By paymentsTab = By.xpath("//div[@class='x1gd']/a[text()='Payments']");
	By paymentNumber_queryInv = By.cssSelector("span.x2ey>a");
	By invNum_InPaymentPopup = By.xpath("//table[@summary='Paid Invoices']/descendant::table//tr[1]/td[1]//span");
	By invStatus_InPaymentPopup = By
			.xpath("//table[@summary='Paid Invoices']/descendant::table//tr[1]/td[last()]//span");
	By ok_inPaymentPopup = By.xpath("//div[@class='AFPopupSelector']/descendant::button[text()='OK']");

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
			waitForElement(createInvoicePageTitle, 180);
			String actualText = driver.findElement(createInvoicePageTitle).getText().trim();
			waitTime(driver);

			grep.infoTest("Invoice Page Header: " + actualText);
			validAssert.equalsAssert(actualText, "Create Invoice:");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateManageInvoicePageTitle() throws Exception {

		try {
//			implWait(driver);
			waitForElement(manageInvoicePageTitle, 180);
			String actualText = driver.findElement(manageInvoicePageTitle).getText().trim();
			waitTime(driver);

			grep.infoTest("Invoice Page Header: " + actualText);
			validAssert.equalsAssert(actualText, "Manage Invoices");
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

	public void enterInvoiceAmount(String amount) throws Exception {
		try {
			// 1. Handle Currency Dropdown
			waitForElementToBeClickable(invoiceAmount, 60);

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

	public void clickSaveAndCloseInvoiceBtn() {
		implWait(driver);

		driver.findElement(saveAndCloseInvoice).click();
	}

	public void clickContinueWarnBtn() {
		try {

			implWait(driver);
			WebElement continueBtn = driver.findElement(continueWarn);
			if (continueBtn.isDisplayed()) {
				continueBtn.click();
			} else {
				grep.infoTest("continue not available");
			}

		} catch (Exception e) {
			grep.infoTest("continue not available");

		}
	}

	public void clickInvoiceActionAndValidateBtn(String actionVal) throws Exception {
		try {
			implWait(driver);
//			waitForElementToBeClickable(invoiceActionBtn, 30);
			By invoiceActBtn = By.xpath("//td[text()='" + actionVal + "']");
			waitTime2(driver);
			driver.findElement(invoiceActionBtn).click();
			waitTime3(driver);
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
//			implWait(driver);
			waitForElement(text_AccountingConfirmation,30);

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
			waitForElement(accountinLineHeader, 60);

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
			waitForElement(doneBtn, 60);
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

	public void searchAndSelectPaymentMethod(String payMethod) {
		try {
			implWait(driver);
			By selectPayment = By.xpath("//li[text()='" + payMethod + "']");

			driver.findElement(paymentMethod).click();
			driver.findElement(paymentMethod).clear();
			waitTime(driver);
			driver.findElement(paymentMethod).sendKeys(payMethod);
			waitTime(driver);
			grep.infoTest("Selecting Payment Method: " + payMethod);
			logger.info("Selecting Payment Method: " + payMethod);
			waitForElementToBeClickable(selectPayment, 20);
			driver.findElement(selectPayment).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSaveAndClose_Payment_Btn() {
		implWait(driver);

		driver.findElement(paymentSaveAndCloseBtn).click();
	}

	// pay in full
	public void searchAndSelectBankAccount(String bankAccVal) {
		try {
			implWait(driver);
			By selectBankAcct = By.xpath("//li[starts-with(text(),'" + bankAccVal + "')]");

			driver.findElement(enterBankAccount).click();
			driver.findElement(enterBankAccount).sendKeys(bankAccVal);
			waitTime(driver);
			grep.infoTest("Selecting Bank Account: " + bankAccVal);
			logger.info("Selecting Bank Account: " + bankAccVal);
			waitForElementToBeClickable(selectBankAcct, 20);
			driver.findElement(selectBankAcct).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectPaymentProfile(String paymentProfileVal) {
		try {
			implWait(driver);
			By selectPaymentProfile = By.xpath("//li[starts-with(text(),'" + paymentProfileVal + "')]");

			driver.findElement(enterPaymentProfile).click();
			driver.findElement(enterPaymentProfile).sendKeys(paymentProfileVal);
			waitTime(driver);
			grep.infoTest("Selecting Payment Profile: " + paymentProfileVal);
			logger.info("Selecting Payment Profile: " + paymentProfileVal);
			waitForElementToBeClickable(selectPaymentProfile, 20);
			driver.findElement(selectPaymentProfile).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSubmit_Payment_Btn() {
		implWait(driver);

		driver.findElement(submitBtn_inPaymentPopup).click();
	}

	public void validatePaymentConfirmationPopup() throws Exception {
		try {
			implWait(driver);

			String getMsg = driver.findElement(paymentConfirmation).getText().trim();
			grep.infoTest("Payment Confirmation Message: " + getMsg);
			logger.info("Payment Confirmation Message: " + getMsg);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOk_InPaymentConfirmation() {
		implWait(driver);

		driver.findElement(okBtn_InPaymentConfirmation).click();
	}

	// with po

	public void searchAndSelectPO(String poVal) {
		try {
			implWait(driver);
			By selectPo = By.xpath("//li[starts-with(text(),'" + poVal + "')]");

			driver.findElement(identifyPO).click();
			driver.findElement(identifyPO).sendKeys(poVal);
			waitTime(driver);
			grep.infoTest("Selecting PO: " + poVal);
			logger.info("Selecting PO: " + poVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectPo, 20);
			driver.findElement(selectPo).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickGoLinesBtn() {
		implWait(driver);

		driver.findElement(clickGo).click();
	}

	public void selectInvoice_InMatchInvoicePopup() {
//		implWait(driver);
		try {
			waitForElement(selectInvoiceLine, 60);

			driver.findElement(selectInvoiceLine).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public String getAmountFromMatchInvoicePopup() throws Exception {
		try {
			implWait(driver);
			// 1. Wait for input and get the 'value' attribute
			String rawValue = driver.findElement(getAmount).getText();
			logger.info("Raw UI Value: " + rawValue);

			String cleanedString = rawValue.replace(".", "").split(",")[0];

			logger.info("Cleaned String Value: " + cleanedString);

			return cleanedString; // Returns "90" as a String

		} catch (Exception e) {
			logger.error("Failed to retrieve amount from input: " + e.getMessage());
			grep.failTest("Failed to retrieve amount from input: " + e.getMessage());
			return "";
		}
	}

	public void clickApply_InMatchInvoicePopup() {
		implWait(driver);

		driver.findElement(applyBtn_inMatchInvoicePopup).click();
	}

	public void clickOk_forWarn_MatchInvoicePopup() {
		implWait(driver);

		driver.findElement(okWarnBtn_inMatchInvoicePopup).click();
	}

	public void clickOk_InMatchInvoicePopup() {
		implWait(driver);

		driver.findElement(okBtn_inMatchInvoicePopup).click();
	}

	public void clickHomeFromInvoicePage() {
		implWait(driver);
		driver.findElement(homeFromInvoice).click();
	}

	// PAYMENT
	public void searchAndSelectSupplier_inPaymentPage(String supplierVal) {
		try {
			implWait(driver);
			By selectSupplier = By.xpath("//li[starts-with(text(),'" + supplierVal + "')]");

			driver.findElement(insertSupplier_inPaymentPage).click();
			driver.findElement(insertSupplier_inPaymentPage).sendKeys(supplierVal);
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

	public void clickOk_InCreatePaymentPage() {
		implWait(driver);

		driver.findElement(okWarnBtn_inPaymentPage).click();
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

	public void searchAndSelectPaymentMethod_inPaymentPage(String paymentMtdValue) {
		try {
			implWait(driver);
			By selectPaymentMtd = By.xpath("//li[text()='" + paymentMtdValue + "']");

			driver.findElement(paymentMethd_inPayment).click();
			driver.findElement(paymentMethd_inPayment).sendKeys(paymentMtdValue);
			waitTime(driver);
			grep.infoTest("Selecting Payment method: " + paymentMtdValue);
			logger.info("Selecting Payment method: " + paymentMtdValue);
			waitForElementToBeClickable(selectPaymentMtd, 20);
			driver.findElement(selectPaymentMtd).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectPaymentProcessProfile_inPaymentPage(String paymentProfileVal) {
		try {
			implWait(driver);
			By selectPaymentProfile = By.xpath("//li[starts-with(text(),'" + paymentProfileVal + "')]");

			driver.findElement(enterPaymentProcessProfile).click();
			driver.findElement(enterPaymentProcessProfile).sendKeys(paymentProfileVal);
			waitTime(driver);
			grep.infoTest("Selecting Payment Profile: " + paymentProfileVal);
			logger.info("Selecting Payment Profile: " + paymentProfileVal);
			waitForElementToBeClickable(selectPaymentProfile, 20);
			driver.findElement(selectPaymentProfile).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSelectAndUseButton() {
		implWait(driver);

		driver.findElement(selectAndAddBtn).click();
	}

	public void enterInvoiceNumber_InSelectAndAddPopup(String invoiceVal) {
		try {
			implWait(driver);

			driver.findElement(invoiceNum_SelectAndAdd).click();
			driver.findElement(invoiceNum_SelectAndAdd).sendKeys(invoiceVal);
			waitTime(driver);
			grep.infoTest("Entering Invoice Number: " + invoiceVal);
			logger.info("Entering Invoice Number: " + invoiceVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOk_InSelectAndUsePopup() {
		implWait(driver);

		driver.findElement(okBtn_inSelectAndAdd).click();
	}

	public void searchAndSelectInvoice_inSelectAndAddPopup(String invNum) {
		try {
			implWait(driver);
			By selectInvoice = By.xpath(
					"//table[@summary='Search Results']//tr[1]//td[2]//span[starts-with(text(),'" + invNum + "')]");

			List<WebElement> select = driver.findElements(selectInvoice);
			if (select.size() > 0) {
				select.getFirst().click();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Query Invoice Number
	public void selectInvoiceNumber_inSearch(String invNum) {
		try {
			implWait(driver);
			By clickInvoiceNumber = By.xpath("//a[text()='" + invNum + "']");

			List<WebElement> select = driver.findElements(clickInvoiceNumber);
			if (select.size() > 0) {
				select.getFirst().click();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickPaymentsTab_QueryInvoice() {
		implWait(driver);

		driver.findElement(paymentsTab).click();
	}

	public void clickPaymentNumber_QueryInvoice() throws Exception {
		try {
//		implWait(driver);
			waitForElement(paymentNumber_queryInv, 10);

			WebElement paymentNum = driver.findElement(paymentNumber_queryInv);
			System.out.println("Payment number got invoice: " + paymentNum.getText());
			grep.infoTest("Payment number got invoice: " + paymentNum.getText());
			logger.info("Payment number got invoice: " + paymentNum.getText());
			waitTime(driver);
			paymentNum.click();

		} catch (Exception e) {
			System.out.println("No Payments available for this invoice");

		}
	}

	public void validateInvoiceNum_InPaymentPopup(String invNumber) throws Exception {

		try {
			implWait(driver);
			String actualText = driver.findElement(invNum_InPaymentPopup).getText().trim();
			waitTime(driver);

			grep.infoTest("Validating Invoice Number in Payment Popup: " + actualText);
			logger.info("Validating Invoice Number in Payment Popup: " + actualText);
			validAssert.equalsAssert(actualText, invNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateInvoiceStatus_InPaymentPopup() throws Exception {

		try {
			implWait(driver);
			String actualText = driver.findElement(invStatus_InPaymentPopup).getText().trim();
			waitTime(driver);

			grep.infoTest("Validating Invoice Status in Payment Popup: " + actualText);
			logger.info("Validating Invoice Status in Payment Popup: " + actualText);
			validAssert.equalsAssert(actualText, "Fully paid");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOk_InPaymentPopup() {
		implWait(driver);

		driver.findElement(ok_inPaymentPopup).click();
	}
}
