package Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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
	By extendedAmt = By.xpath("//span[contains(@id,'extendedAmt')]");

	By saveBtn = By.xpath("//a/span[text()='Save']");
	By completeCreateAnother_DropdownBtn = By.xpath("//a[@title='Complete and Create Another']");

	By actionBtn = By.xpath("//a[text()='Actions']");
	By ok_ActionConfirm = By.xpath("//button[@accesskey='K']");
	By saveDropdownBtn = By.xpath("//a[@title='Save']");
	By saveAndCloseBtn = By.xpath("//tr[@accesskey='S']/td[2]");
	By transactionConfirmation = By.xpath("//div[@class='AFPopupSelector']/descendant::td[@class='x1n1']");
	By okBtn_InTransactionConfirmation = By.xpath("//button[text()='OK']");

	// Create Receipt
	By receiptMethod = By.xpath("//label[text()='Receipt Method']/preceding-sibling::input");
	By receiptNumber = By.xpath("//label[text()='Receipt Number']/preceding-sibling::input");
	By enteredAmount = By.xpath("//label[text()='Entered Amount']/preceding-sibling::input");
	By bankName = By.xpath("//label[text()='Name']/following-sibling::span/span/a");
	By bankBranch = By.xpath("//label[text()='Branch']/following-sibling::span/span/a");
	By bankAccount = By.xpath("//label[text()='Account']/following-sibling::span/span/a");
	By submitAndCreateAnotherBtn = By.xpath("//a[@title='Submit and Create Another']");
	By application_ReceiptDetails = By.xpath("//div[contains(@id,':showDetailItem2')]/div/a[text()='Application']");
	By openReceivablesBtn = By.xpath("//button[text()='Add Open Receivables']");
	By transactionCustomerName = By.xpath("//label[text()=' Transaction Customer Name']/preceding-sibling::input");
	By transactionCustNameSearch = By.xpath("//label[text()=' Transaction Customer Name']/following-sibling::a");
	By doneOpenReceivable = By.xpath("//button[@accesskey='o']");
	By save_ReceiptBtn = By.xpath("//button[text()='Save']");

	// Manage Transactions
	By transactionDate = By.xpath("//label[text()=' Transaction Date']/following-sibling::a");
	By selectDate = By.xpath("//td[@class='x120 p_AFSelected']");
	By businessUnitDropdown = By.xpath("//a[@title='Search:  Business Unit']");
	By searchInDropdwon = By.xpath("//a[text()='Search...']");
	By insertBu_inPopup = By.xpath(
			"//label[text()=' Business Unit']/preceding-sibling::input[contains(@name,'afrLovInternalQueryId:')]");

	By search_inSelectPopup = By.xpath("//button[contains(@id,'_afrLovInternalQueryId::search') and text()='Search']");
	By ok_inSelectPopup = By.xpath("//button[contains(@id,'lovDialogId::ok') and text()='OK']");

	By transactionSourceDropdown = By.xpath("//a[@title='Search:  Transaction Source']");
	By insertName_inPopup = By
			.xpath("//label[text()=' Name']/preceding-sibling::input[contains(@name,'afrLovInternalQueryId:')]");

	By okBtn_InManageTransaction = By
			.xpath("//div[@class='AFPopupSelector']/descendant::td[@class='p_AFResizable x1pn']/button");

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
			By selectPaymentTerms = By.xpath("//li[starts-with(text(),'" + paymentTermsVal + "')]");

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

	public String getExtendedAmount() throws Exception {
		String getAmt = null;
		try {

			implWait(driver);

			getAmt = driver.findElement(extendedAmt).getText().trim();
//			getMsg = fullMessage.replaceAll("[^0-9]", "");

			if (getAmt.isEmpty()) {
				logger.error("No numeric transaction number found in message: " + getAmt);
				return getAmt;
			}
			grep.infoTest("Extended Amount: " + getAmt);
			logger.info("Extended Amount: " + getAmt);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAmt;
	}

	public void clickSaveBtn_inTransactionPage() {
		implWait(driver);
		driver.findElement(saveBtn).click();
	}

	public void clickCompleteAndCreateBtn_inTransactionPage(String selectOpt) throws Exception {

		implWait(driver);
		By completReviewBtn = By.xpath("//td[text()='" + selectOpt + "']");

		driver.findElement(completeCreateAnother_DropdownBtn).click();
		driver.findElement(completReviewBtn).click();
		waitTime(driver);
		grep.infoTest("Selecting " + selectOpt + " Option");
		logger.info("Selecting " + selectOpt + " Option");
		waitTime(driver);

	}

	// actions

	public void clickActionAndValidate_inTransactionBtn(String actionVal) throws Exception {
		try {
			implWait(driver);
			By transactionActBtn = By.xpath("//td[text()='" + actionVal + "']");
			waitTime2(driver);
			driver.findElement(actionBtn).click();
			waitTime3(driver);
			driver.findElement(transactionActBtn).click();
			waitTime1(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOk_inActionConfirmation() {
		try {
//			implWait(driver);
			waitForElement(ok_ActionConfirm, 20);
			driver.findElement(ok_ActionConfirm).click();
			waitTime(driver);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickSaveAndCloseBtn_inTransactionPage() {
		try {
			implWait(driver);
			driver.findElement(saveDropdownBtn).click();
			waitTime(driver);
			driver.findElement(saveAndCloseBtn).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String validateTransactionConfirmationPopup() throws Exception {
		String getMsg = null;
		try {

			implWait(driver);

			String fullMessage = driver.findElement(transactionConfirmation).getText().trim();
			getMsg = fullMessage.replaceAll("[^0-9]", "");

			if (getMsg.isEmpty()) {
				logger.error("No numeric transaction number found in message: " + getMsg);
				return null;
			}
			grep.infoTest("Transaction Confirmation Message: " + fullMessage);
			logger.info("Transaction Confirmation Message: " + fullMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getMsg;
	}

	public void clickOk_InTransactionConfirmation() {
		implWait(driver);

		driver.findElement(okBtn_InTransactionConfirmation).click();
	}

	// CREATE RECEIPT

	public void searchAndSelectReceiptMethod(String receiptVal) {
		try {
			implWait(driver);
			By selectReceiptMethod = By.xpath("//li[starts-with(text(),'" + receiptVal + "')]");

			driver.findElement(receiptMethod).click();
			driver.findElement(receiptMethod).sendKeys(receiptVal);
			waitTime(driver);
			grep.infoTest("Selecting Receipt Method: " + receiptVal);
			logger.info("Selecting Receipt Method: " + receiptVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectReceiptMethod, 20);
			driver.findElement(selectReceiptMethod).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterReceiptNumber(String numVal) {
		try {
			implWait(driver);

			driver.findElement(receiptNumber).click();
			driver.findElement(receiptNumber).sendKeys(numVal);
			waitTime(driver);
			grep.infoTest("Entering Receipt Number: " + numVal);
			logger.info("Entering Receipt Number: " + numVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterAmount_inReceipt(String nameVal) {
		try {
			implWait(driver);

			driver.findElement(enteredAmount).click();
			driver.findElement(enteredAmount).sendKeys(nameVal);
			waitTime(driver);
			grep.infoTest("Entering Amount: " + nameVal);
			logger.info("Entering Amount: " + nameVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectBankName(String bankVal) {
		try {
			implWait(driver);
			By selectBankName = By.xpath("//span[starts-with(text(),'" + bankVal + "')]");

			driver.findElement(bankName).click();
//			driver.findElement(bankName).sendKeys(bankVal);
			waitTime(driver);
			grep.infoTest("Selecting Bank Name: " + bankVal);
			logger.info("Selecting Bank Name: " + bankVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectBankName, 20);
			driver.findElement(selectBankName).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectBankBranch(String bankVal) {
		try {
			implWait(driver);
			By selectBankBranch = By.xpath("//span[starts-with(text(),'" + bankVal + "')]");

			driver.findElement(bankBranch).click();
//			driver.findElement(bankBranch).sendKeys(bankVal);
			waitTime(driver);
			grep.infoTest("Selecting Bank branch: " + bankVal);
			logger.info("Selecting Bank branch: " + bankVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectBankBranch, 20);
			driver.findElement(selectBankBranch).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectBankAccount(String bankAccVal) {
		try {
			implWait(driver);
			By selectBankAccount = By.xpath("//span[starts-with(text(),'" + bankAccVal + "')]");

			driver.findElement(bankAccount).click();
//			driver.findElement(bankAccount).sendKeys(bankAccVal);
			waitTime(driver);
			grep.infoTest("Selecting Bank Account:a " + bankAccVal);
			logger.info("Selecting Bank Account: " + bankAccVal);
			waitTime2(driver);
			waitForElementToBeClickable(selectBankAccount, 20);
			driver.findElement(selectBankAccount).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSubmitCreateAnotherBtn_inReceiptPage(String submitVal) {
		try {
			By selectSubmitOption = By.xpath("//td[text()='" + submitVal + "']");

			implWait(driver);
			driver.findElement(submitAndCreateAnotherBtn).click();
			waitTime(driver);
			driver.findElement(selectSubmitOption).click();
			waitTime(driver);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickApplicationTab_inReceiptDetails() {
		try {
			waitForElement(application_ReceiptDetails, 20);
			driver.findElement(application_ReceiptDetails).click();
			waitTime(driver);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickAddOpenReceivablesBtn_inReceiptDetails() {
		try {
			waitForElement(openReceivablesBtn, 20);
			driver.findElement(openReceivablesBtn).click();
			waitTime(driver);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void enterTransactionCustomerName_Receipt(String nameVal) {
		try {
//			implWait(driver);
			waitForElement(transactionCustomerName, 20);
			driver.findElement(transactionCustomerName).click();
			driver.findElement(transactionCustomerName).sendKeys(nameVal);
			waitTime(driver);
			grep.infoTest("Entering Transaction Customer Name: " + nameVal);
			logger.info("Entering Transaction Customer Name: " + nameVal);
			actionEntered();
			waitTime(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickTransactionCustomerNameSearch_Receipt(String nameVal) {
		try {
			implWait(driver);

			driver.findElement(transactionCustNameSearch).click();
			waitTime(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSelectTransactionCustomerNameFromList(String name) throws Exception {

		try {
			By selectName = By
					.xpath("//div[contains(@id,'frLovInternalTableId::db')]/descendant::span[text()='" + name + "']");

			implWait(driver);
			List<WebElement> state = driver.findElements(selectName);
			if (state.size() > 0) {
				state.getFirst().click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void scrollToLastVisibleRow() {
		By descending = By.xpath(
				"//span[text()='Receipt Reference Number']/parent::div/preceding-sibling::div/descendant::a[@title='Sort Descending']");
		By hoveEle = By.xpath("//span[text()='Receipt Reference Number']");

		try {
			WebElement lastRow = driver.findElement(hoveEle);
			Actions actions = new Actions(driver);
			actions.moveToElement(lastRow).build().perform();
			waitTime(driver);

			driver.findElement(descending).click();

			logger.info("Scrolled to the last visible record in the table.");
		} catch (Exception e) {
			logger.error("Could not find the last row to scroll: " + e.getMessage());
		}
	}

	public void clickSelectReceiptFromList(String receiptVal) throws Exception {

		try {
			By selectReceipt = By.xpath("//span[text()='" + receiptVal + "']");

//			implWait(driver);
			waitForElement(selectReceipt, 30);
			scrollView(selectReceipt);
			List<WebElement> receipt = driver.findElements(selectReceipt);
			if (receipt.size() > 0) {
				receipt.getFirst().click();
				grep.infoTest("Selecting receipt :" + receiptVal);
				logger.info("Selecting receipt :" + receiptVal);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickDoneBtn_inOpenReceivables() throws Exception {
		try {
			implWait(driver);
			driver.findElement(doneOpenReceivable).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickSaveReceiptBtn() {
		implWait(driver);

		driver.findElement(save_ReceiptBtn).click();
	}

//	MANAGE TRANSACTIONS

	public void selectTransactionDate() throws Exception {
		try {
			implWait(driver);
			driver.findElement(transactionDate).click();
			waitTime(driver);
			driver.findElement(selectDate).click();
			waitTime(driver);
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public void clickBUDropdownButton() throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(businessUnitDropdown).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(businessUnitDropdown, 30);
				driver.findElement(businessUnitDropdown).click();
			} else {
				logger.error("Business unit dropdown button Not Available ");
				grep.failTest("Business unit dropdown button Not Available ");
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

	public void enterBU(String nameVal) {
		try {
			implWait(driver);

			driver.findElement(insertBu_inPopup).click();
			driver.findElement(insertBu_inPopup).sendKeys(nameVal);
			waitTime(driver);
			grep.infoTest("Entering Business Unit: " + nameVal);
			logger.info("Entering Business Unit: " + nameVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSelect_NameFromList(String name) throws Exception {

		try {
			By selectName = By
					.xpath("//div[contains(@id,'afrLovInternalTableId::db')]/descendant::span[text()='" + name + "']");

			implWait(driver);
			List<WebElement> state = driver.findElements(selectName);
			if (state.size() > 0) {
				state.getFirst().click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickSearch_inSelect_Popup() {
		implWait(driver);
		driver.findElement(search_inSelectPopup).click();
	}

	public void clickOk_inSelect_Popup() {
		implWait(driver);
		driver.findElement(ok_inSelectPopup).click();
	}

	public void clickTransactionSourceDropdownButton() throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(transactionSourceDropdown).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(transactionSourceDropdown, 30);
				driver.findElement(transactionSourceDropdown).click();
			} else {
				logger.error("Transaction Source dropdown button Not Available ");
				grep.failTest("Transaction Source dropdown button Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void enterTransactionSourceName_inpopup(String nameVal) {
		try {
			implWait(driver);

			driver.findElement(insertName_inPopup).click();
			driver.findElement(insertName_inPopup).sendKeys(nameVal);
			waitTime(driver);
			grep.infoTest("Entering Transaction Source name: " + nameVal);
			logger.info("Entering Transaction Source name: " + nameVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSelect_TransactionNumber(String number) throws Exception {

		try {
			By selectTransactionNumber = By.xpath("//a[text()='" + number + "']");

//			implWait(driver);
			waitForElement(selectTransactionNumber, 30);
			scrollView(selectTransactionNumber);
			List<WebElement> state = driver.findElements(selectTransactionNumber);
			if (state.size() > 0) {
				state.getFirst().click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOk_InManageTransactionConfirmation() {
		implWait(driver);

		driver.findElement(okBtn_InManageTransaction).click();
	}
}
