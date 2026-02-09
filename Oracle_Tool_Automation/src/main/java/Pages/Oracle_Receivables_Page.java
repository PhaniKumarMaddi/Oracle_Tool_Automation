package Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utility.DriverManager;
import Utility.GenerateReports;
import Utility.ValidatingAssertions;
import Utility.WaitsManager;

public class Oracle_Receivables_Page extends WaitsManager {

	protected WebDriver driver;
	private static Logger logger = LogManager.getLogger(Oracle_Receivables_Page.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();

	public Oracle_Receivables_Page() {
		this.driver = DriverManager.getDriver();
	}

	// Create Transaction
	By insertBusinessUnit = By.xpath("//label[text()='Business Unit']/preceding-sibling::input");
	By insertTransactionSource = By.xpath("//label[text()='Transaction Source']/preceding-sibling::input");
	By insertTransactionType = By.xpath("//label[text()='Transaction Type']/preceding-sibling::input");
	By billToNameSearchBtn = By.xpath("//a[@title='Search: Bill-to Name']");
	By insertCustomerName = By.xpath("//label[text()=' Name']/preceding-sibling::input");
	By searchBtn = By.xpath("//button[text()='Search']");
	By okBillToNameBtn = By.xpath("//button[text()='OK']");
	By paymentTerms = By.xpath("//label[text()='Payment Terms']/parent::td/following-sibling::td[1]/descendant::input");
	By description_InLine = By.xpath("//label[text()='Description']/preceding-sibling::input");
	By quantity_inLine = By.xpath("//label[text()='Quantity']/preceding-sibling::input");
	By unitPrice_inLine = By.xpath("//label[text()='Unit Price']/preceding-sibling::input");
	By saveBtn = By.xpath("//a/span[text()='Save']");
	By completeCreateAnother_DropdownBtn = By.xpath("//a[@title='Complete and Create Another']");
	By completReview = By.xpath("//td[text()='Complete and Review']");
	By actionBtn = By.xpath("//a[text()='Actions]");
	By accountinLineHeader = By.xpath("//div[contains(@id,':d12::_ttxt')]");
	By saveDropdownBtn = By.xpath("//a[@title='Save']");
	By saveAndCloseBtn = By.xpath("//tr[@accesskey='S']/td[2]");

	// Create Receipt
	By receiptMethod = By.xpath("//label[text()='Receipt Method']/preceding-sibling::input");
	By receiptNumber = By.xpath("//label[text()='Receipt Number']/preceding-sibling::input");
	By enteredAmount = By.xpath("//label[text()='Entered Amount']/preceding-sibling::input");
	By bankName = By.xpath("//label[text()='Name']/preceding-sibling::input");
	By bankBranch = By.xpath("//label[text()='Branch']/preceding-sibling::input");
	By bankAccount = By.xpath("//label[text()='Account']/preceding-sibling::input");
	By submitAndCreateAnotherBtn = By.xpath("//a[@title='Submit and Create Another']");

	// CREATE TRANSACTIONS

	public void searchAndSelectBusinessUnit(String businessUnitVal) {
		try {
			implWait(driver);

			driver.findElement(insertBusinessUnit).click();
			driver.findElement(insertBusinessUnit).sendKeys(businessUnitVal);
			waitTime(driver);
			grep.infoTest("Selecting Business Unit: " + businessUnitVal);
			logger.info("Selecting Business Unit: " + businessUnitVal);
			waitTime(driver);
			actionEntered();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectTransactionSource(String sourceVal) {
		try {
			implWait(driver);
			By selectTransactionSource = By.xpath("//li[starts-with(text(),'" + sourceVal + "')]");

			driver.findElement(insertTransactionSource).click();
			driver.findElement(insertTransactionSource).sendKeys(sourceVal);
			waitTime(driver);
			grep.infoTest("Selecting Transaction Source: " + sourceVal);
			logger.info("Selecting Transaction Source: " + sourceVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectTransactionSource, 20);
			driver.findElement(selectTransactionSource).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectTransactionType(String typeVal) {
		try {
			implWait(driver);
			By selectTransactionType = By.xpath("//li[starts-with(text(),'" + typeVal + "')]");

			driver.findElement(insertTransactionType).click();
			driver.findElement(insertTransactionType).sendKeys(typeVal);
			waitTime(driver);
			grep.infoTest("Selecting Transaction Type: " + typeVal);
			logger.info("Selecting Transaction Type: " + typeVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectTransactionType, 20);
			driver.findElement(selectTransactionType).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickBillToNameSearchBtn() {
		implWait(driver);

		driver.findElement(billToNameSearchBtn).click();
	}

	public void enterCustomerName(String nameVal) {
		try {
			implWait(driver);

			driver.findElement(insertCustomerName).click();
			driver.findElement(insertCustomerName).sendKeys(nameVal);
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

	public void clickSelectCustomerNameFromList(String name) throws Exception {

		try {
			By selectName = By
					.xpath("//div[contains(@id,'billToNameId_afrLovInternalTableId::db')]/descendant::span[text()='"
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

	public void clickOk_inBillToNamePopup() {
		implWait(driver);
		driver.findElement(okBillToNameBtn).click();
	}

	public void searchAndSelectPaymentTerms(String paymentTermsVal) {
		try {
			implWait(driver);
			By selectPaymentTerms = By.xpath("//li[text()='" + paymentTermsVal + "']");

			driver.findElement(paymentTerms).click();
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

	public void enterDescriptionInLines(String descVal) throws Exception {
		try {
			implWait(driver);
			List<WebElement> desc = driver.findElements(description_InLine);
			if (desc.size() > 0) {

				desc.getFirst().click();
				desc.getFirst().sendKeys(descVal);

				System.out.println("Entering Line Description: " + descVal);
				grep.infoTest("Entering Line Description: " + descVal);
				logger.info("Entering Line Description: " + descVal);
				waitTime(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterQuantityInLines(String quantityVal) throws Exception {
		try {
			implWait(driver);
			List<WebElement> quantity = driver.findElements(quantity_inLine);
			if (quantity.size() > 0) {

				quantity.getFirst().click();
				quantity.getFirst().sendKeys(quantityVal);

				System.out.println("Entering Line Quantity: " + quantityVal);
				grep.infoTest("Entering Line Quantity: " + quantityVal);
				logger.info("Entering Line Quantity: " + quantityVal);
				waitTime(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterUnitPriceInLines(String unitPriceVal) throws Exception {
		try {
			implWait(driver);
			List<WebElement> unitPrice = driver.findElements(unitPrice_inLine);
			if (unitPrice.size() > 0) {

				unitPrice.getFirst().click();
				unitPrice.getFirst().sendKeys(unitPriceVal);

				System.out.println("Entering Line Unit Price: " + unitPriceVal);
				grep.infoTest("Entering Line Unit Price: " + unitPriceVal);
				logger.info("Entering Line Unit Price: " + unitPriceVal);
				waitTime(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSaveBtn_inTransactionPage() {
		implWait(driver);
		driver.findElement(saveBtn).click();
	}
	
	public void clickCompleteAndCreateBtn_inTransactionPage() {
		implWait(driver);
		driver.findElement(completeCreateAnother_DropdownBtn).click();
	}
	
	//

	public void clickInvoiceActionAndValidateBtn(String actionVal) throws Exception {
		try {
			implWait(driver);
			By invoiceActBtn = By.xpath("//td[text()='" + actionVal + "']");
			waitTime2(driver);
			driver.findElement(actionBtn).click();
			waitTime3(driver);
			driver.findElement(invoiceActBtn).click();
			waitTime1(driver);
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
}
