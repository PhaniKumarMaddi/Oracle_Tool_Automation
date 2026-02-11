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

public class Oracle_Customer_StandardReport_Page extends WaitsManager {

	protected WebDriver driver;
	private static Logger logger = LogManager.getLogger(Oracle_Customer_StandardReport_Page.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();

	public Oracle_Customer_StandardReport_Page() {
		this.driver = DriverManager.getDriver();
	}

	// Create Customer
	By insertCustName = By.xpath("//label[text()='Name']/parent::td/following-sibling::td/span/input");
	By insertAccDescription = By.xpath("//label[text()='Account Description']/parent::td/following-sibling::td/input");
	By accountAddrSet = By.xpath("//label[text()='Account Address Set']/parent::td/following-sibling::td/span/span");
	By siteName = By.xpath("//label[text()='Site Name']/parent::td/following-sibling::td/input");
	By state = By.xpath("//a[@title='State']");
	By addRow_AddressPurpose = By.xpath("//div[@title='Add Row']/a");
	By setPrimary_AddressPurpose = By.xpath("//div[@title='Set Primary']/a");
	By selectPurpose = By.xpath("//label[text()='Purpose']/preceding-sibling::select");
	By billToSite = By.xpath("//a[@title='Search: Address']");
	By saveCloseBtn = By.xpath("//button[@accesskey='S']");
	By verifyOrgRecord = By.xpath("//table[@summary='Organizations']/tbody/tr/td[4]/span/span/a");

	// AR STANDARD REPORT
	By insertRequestName = By.xpath("//label[text()='Request Name']/parent::td/following-sibling::td/input");
	By businessUnitDropdown = By.xpath("//a[@title='Search: Business Unit']");
	By accountingPeriodDropdown = By.xpath("//a[@title='Search: Accounting Period']");
	By arSubmitBtn = By.xpath("//a[@accesskey='m']");
	By submitConfirmation = By.xpath("//span[contains(@id,':requestBtns:confirmationPopup')]");
	By okBtn_InSubmitConfirmation = By.xpath("//button[text()='OK']");

	// CREATE CUSTOMER
	public void enterCustomerName(String nameVal) {
		try {
			implWait(driver);

			driver.findElement(insertCustName).click();
			driver.findElement(insertCustName).sendKeys(nameVal);
			waitTime(driver);
			grep.infoTest("Entering Customer Name: " + nameVal);
			logger.info("Entering Customer Name: " + nameVal);
			waitTime(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterAccountDescription(String descVal) {
		try {
			implWait(driver);
//			scrollView(insertAccDescription);

			driver.findElement(insertAccDescription).click();
			driver.findElement(insertAccDescription).sendKeys(descVal);
			waitTime(driver);
			grep.infoTest("Entering Account Description: " + descVal);
			logger.info("Entering Account Description: " + descVal);
			waitTime(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickAccountAddressSet(String selectOpt) throws Exception {

		implWait(driver);
		By selectAccAddr = By.xpath("//span[text()='" + selectOpt + "']");

		scrollView(insertAccDescription);
		driver.findElement(accountAddrSet).click();
		waitTime(driver);
		driver.findElement(selectAccAddr).click();
		waitTime(driver);
		grep.infoTest("Selecting " + selectOpt + " Option in Account Address ");
		logger.info("Selecting " + selectOpt + " Option in Account Address ");
		waitTime(driver);

	}

	public void enterSiteName(String nameVal) {
		try {
			implWait(driver);
			scrollView(accountAddrSet);

			driver.findElement(siteName).click();
			driver.findElement(siteName).sendKeys(nameVal);
			waitTime(driver);
			grep.infoTest("Entering Site Name: " + nameVal);
			logger.info("Entering Site Name: " + nameVal);
			waitTime(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickState(String selectOpt) throws Exception {

		implWait(driver);
		By selectState = By.xpath("//span[text()='" + selectOpt + "']");

		scrollView(siteName);
		driver.findElement(state).click();
		waitTime(driver);
		driver.findElement(selectState).click();
		waitTime(driver);
		grep.infoTest("Selecting " + selectOpt + " Option in State");
		logger.info("Selecting " + selectOpt + " Option in State");
		waitTime(driver);

	}

	public void clickAddressPurpose_AddRow() {
		try {

			waitForElement(addRow_AddressPurpose, 20);
			WebElement addRowBtn = driver.findElement(addRow_AddressPurpose);

			logger.info("Clicking Add Row button for Address Purpose.");
			grep.infoTest("Clicking Add Row button for Address Purpose.");

			addRowBtn.click();
			waitTime(driver);

		} catch (Exception e) {
			logger.error("Failed to click address Purpose Add Row button: " + e.getMessage());
			grep.failTest("Add Row button click failed.");
		}
	}

	public void clickAddressPurpose_SetPrimary() {
		try {

			waitForElement(setPrimary_AddressPurpose, 20);
			WebElement addRowBtn = driver.findElement(setPrimary_AddressPurpose);

			logger.info("Clicking Set Primary button for Address Purpose.");
			grep.infoTest("Clicking Set Primary button for Address Purpose.");

			addRowBtn.click();
			waitTime(driver);

		} catch (Exception e) {
			logger.error("Failed to click address Purpose Set Primary button: " + e.getMessage());
			grep.failTest("Set Primary button click failed.");
		}
	}

	public void selectPurpose_inAddressPurpose(String option) throws Exception {
		try {
			waitForElementToBeClickable(selectPurpose, 10);
			scrollView(selectPurpose);
//			WebElement selectBu = driver.findElement(selectPurpose);
			List<WebElement> selectPurp = driver.findElements(selectPurpose);
			WebElement getFirstPurpose = selectPurp.getFirst();
			Select sel = new Select(getFirstPurpose);
			sel.selectByVisibleText(option);
			grep.infoTest("Select " + option + " from the purpose drop down under address Purpose");
			logger.info("Select " + option + " from the purpose drop down under address Purpose");

		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}

	}

	public void clickBillToSite_AddressPurpose(String selectOpt) throws Exception {

		By selectAccAddr_billToSite = By.xpath("//span[text()='" + selectOpt + "']");

		waitForElement(billToSite, 20);
		waitTime(driver);
		List<WebElement> selectBill = driver.findElements(billToSite);
		selectBill.getFirst().click();
		waitTime(driver);
		driver.findElement(selectAccAddr_billToSite).click();
		waitTime(driver);
		grep.infoTest("Selecting " + selectOpt + " Option for Bill to Site under Account Address");
		logger.info("Selecting " + selectOpt + " Option for Bill to Site under Account Address ");
		waitTime(driver);

	}

	public void clickSaveAndClose_Customer_Btn() {
		implWait(driver);

		driver.findElement(saveCloseBtn).click();
	}

	public boolean verifyCustomerPresent(String expectedOrgName) {
		// 1. Locator for the specific column containing the links

		try {
			// 2. Retrieve all elements in that column
			List<WebElement> orgList = driver.findElements(verifyOrgRecord);

			logger.info("Found " + orgList.size() + " organizations in the table. Searching for: " + expectedOrgName);

			for (WebElement org : orgList) {
				String actualText = org.getText().trim();

				if (actualText.equalsIgnoreCase(expectedOrgName)) {
					logger.info("Record Found: " + actualText);
					grep.infoTest("Record Found: " + actualText);
					return true;
				}
			}
		} catch (Exception e) {
			logger.error("Error while searching for Organization: " + e.getMessage());
			grep.warnTest("Error while searching for Organization: " + e.getMessage());

		}

		logger.warn("Record NOT Found: " + expectedOrgName);
		grep.warnTest("Record NOT Found: " + expectedOrgName);
		return false;
	}

	// AR STANDARD REPORT
	public void enterRequestName(String nameVal) {
		try {
			implWait(driver);

			driver.findElement(insertRequestName).click();
			driver.findElement(insertRequestName).sendKeys(nameVal);
			waitTime(driver);
			grep.infoTest("Entering Request Name: " + nameVal);
			logger.info("Entering Request Name: " + nameVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickBusinessUnit(String selectOpt) throws Exception {

		implWait(driver);
		By select_BU = By.xpath("//span[text()='" + selectOpt + "']");

		driver.findElement(businessUnitDropdown).click();
		waitTime(driver);
		driver.findElement(select_BU).click();
		waitTime(driver);
		grep.infoTest("Selecting " + selectOpt + " Option in Business Unit ");
		logger.info("Selecting " + selectOpt + " Option in Business Unit ");
		waitTime(driver);

	}

	public void clickAccountingPeriod(String selectAcc) throws Exception {

		implWait(driver);
		By select_AccPeriod = By.xpath("//span[text()='" + selectAcc + "']");

		driver.findElement(accountingPeriodDropdown).click();
		waitTime2(driver);
		scrollView(select_AccPeriod);
		driver.findElement(select_AccPeriod).click();
		waitTime(driver);
		grep.infoTest("Selecting " + selectAcc + " Option in Accounting Period");
		logger.info("Selecting " + selectAcc + " Option in Accounting Period");
		waitTime(driver);

	}

	public void clickSumbit_Reconciliation_Btn() {
		implWait(driver);

		driver.findElement(arSubmitBtn).click();
	}

	public String validateSubmitConfirmationPopup() throws Exception {
		String getMsg = null;
		try {

//			implWait(driver);
			waitForElement(submitConfirmation, 20);

			String fullMessage = driver.findElement(submitConfirmation).getText().trim();
			getMsg = fullMessage.replaceAll("[^0-9]", "");

			if (getMsg.isEmpty()) {
				logger.error("No numeric found in message: " + getMsg);
				return null;
			}
			grep.infoTest("submit Confirmation Message: " + fullMessage);
			logger.info("submit Confirmation Message: " + fullMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getMsg;
	}

	public void clickOk_InSubmitConfirmation() {
		implWait(driver);

		driver.findElement(okBtn_InSubmitConfirmation).click();
	}
}
