package Pages;

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
	By lineAmount = By.xpath("//td[@title='Amount']/descendant::input");
	By distributionCombination = By.xpath("//td[@title='Distribution Combination ID']/span/descendant::a");
	By enterCompany_inDCPopup = By.xpath("//label[text()='COMPANY']/preceding-sibling::input");
	By searchBtn_inDCPopup = By.xpath("//button[@accesskey='r']");
	By okBtn_inDCPopup = By.xpath("//button[@accesskey='k']");
	By getDistributionCombination_ID = By.xpath("//input[@aria-label='Distribution Combination ID']");
	By saveInvoice = By.xpath("//span[text()='Save']");

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
//			waitForElement(lineAmount, 60);
			implWait(driver);

			driver.findElement(selectLine).click();
			waitTime(driver);
			actionTab();
//			WebElement amountInput = driver.findElement(lineAmount);
			driver.findElement(lineAmount).click();
			driver.findElement(lineAmount).sendKeys(amount);

			System.out.println("Entering Line Amount: " + amount);
			grep.infoTest("Entering Line Amount: " + amount);
			logger.info("Entering Line Amount: " + amount);
//			amountInput.clear(); // Clear existing value if any
//			amountInput.sendKeys(amount);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectDistributionCombination(String companyVal, String accValue) {
		try {
			implWait(driver);

			By selectCompany_inDCPopup = By.xpath("//span[text()='" + companyVal + "']");
			By selectAcc_inDCPopup = By.xpath("//span[text()='" + accValue + "']");
			scrollView(distributionCombination);

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
}
