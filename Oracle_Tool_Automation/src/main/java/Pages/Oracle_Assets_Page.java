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

public class Oracle_Assets_Page extends WaitsManager {
	protected WebDriver driver;
	private static Logger logger = LogManager.getLogger(Oracle_Assets_Page.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();

	public Oracle_Assets_Page() {
		this.driver = DriverManager.getDriver();
	}

	By searchAssetCatg = By.xpath("//a/img[@alt='Select: Category']");
	// Major
	By majorCatgDropdown = By.xpath("//a[@title='Search: Major']");
	By searchInDropdown = By.xpath("//a[text()='Search...']");
	By insertValue = By.xpath("//label[text()=' Value']/preceding-sibling::input");
	By searchBtn = By.xpath("//button[text()='Search']");
	By ok_inSelectMajor = By.xpath("//button[contains(@id,'value00::lovDialogId::ok') and text()='OK']");

	// Minor
	By minorCatgDropdown = By.xpath("//a[@title='Search: Minor']");
	By ok_inSelectMinor = By.xpath("//button[contains(@id,'value10::lovDialogId::ok') and text()='OK']");

	By ok_inCategoryPopup = By.xpath("//button[@accesskey='k']");
	By assetDesc = By.xpath("//input[contains(@id,'descriptionId::content')]");
	By assetCost = By.xpath("//label[text()='Cost']/parent::td/following-sibling::td/span/input");

	// expense popup
	By searchExpenseAccount = By.xpath("//a/img[@alt='Select: Expense Account']");
	By enterCompany_inExpAccPopup = By.xpath("//label[text()='COMPANY']/preceding-sibling::input");
	By enterDepartment_inExpAccPopup = By.xpath("//label[text()='DEPTARTMENT']/preceding-sibling::input");
	By enterFuture_inExpAccPopup = By.xpath("//label[text()='FUTURE']/preceding-sibling::input");
	By okBtn_inExpAccPopup = By.xpath("//button[@accesskey='k']");

	// location
	By searchLocation = By.xpath("//a/img[@alt='Select: Location']");
	By country_inLocationPopup = By.xpath("//label[text()='Country']/preceding-sibling::input");
	By state_inLocationPopup = By.xpath("//label[text()='State']/preceding-sibling::input");
	By city_inLocationPopup = By.xpath("//label[text()='City']/preceding-sibling::input");
	By building_inLocationPopup = By.xpath("//label[text()='Building']/preceding-sibling::input");
	By future_inLocationPopup = By.xpath("//label[text()='Future']/preceding-sibling::input");
	By nextBtn = By.xpath("//button[@accesskey='x']");

	// Asset Details
	By assetNumber = By.xpath("//label[text()='Asset Number']/parent::td/following-sibling::td/input");
	By searchAssetKey = By.xpath("//img[@alt='Select: Asset Key']");
	By faAssetKey_Dropdown = By.xpath("//a[@title='Search: FA_Asset Key']");
	By saveAndCloseAsset = By.xpath("//a[@title='Save and Close']");
	By saveAsset = By.xpath("//td[text()='Save']");

	By confirmMsg = By.xpath("//div[contains(@id,'panelGroupLayout21')]");
	By okBtn_inConfirmationPopup = By.xpath("//button[@accesskey='K']");
	By submitBtn_inAsset = By.xpath("//a[@accesskey='m']");
	By readyToPost = By.xpath("//span[text()='Ready to Post']");
	By postAllBtn = By.xpath("//button[text()='Post All']");
	By refreshBtn = By.xpath("//img[@alt='Refresh']");

	// inquire asset
	By inquireAssetNum = By.xpath("//input[@aria-label=' Asset Number']");
	By done_inInquireAsset = By.xpath("//button[@accesskey='o']");
	By depricationBtn = By.xpath("//img[@alt='Select : Depreciation']");
	By calculateDepricationBtn = By.xpath("//span[text()='Calculate Depreciation']/parent::a");

	public void clickSearchAssetCategoryBtn() throws Exception {
		try {
//				implWait(driver);
			waitForElementToBeClickable(searchAssetCatg, 20);
			boolean elementExists = !driver.findElements(searchAssetCatg).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(searchAssetCatg, 30);
				driver.findElement(searchAssetCatg).click();
			} else {
				logger.error("Search Category Not Available ");
				grep.failTest("Search Category Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	// MAJOR
	public void clickMajorCategoryDropdownBtn() throws Exception {
		try {
//				implWait(driver);
			waitForElementToBeClickable(majorCatgDropdown, 20);
			boolean elementExists = !driver.findElements(majorCatgDropdown).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(majorCatgDropdown, 30);
				driver.findElement(majorCatgDropdown).click();
				waitTime(driver);
				driver.findElement(majorCatgDropdown).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void clickSearch_InDropdown() throws Exception {
		try {
//				implWait(driver);
			waitForElementToBeClickable(searchInDropdown, 20);
			boolean elementExists = !driver.findElements(searchInDropdown).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(searchInDropdown, 30);
				driver.findElement(searchInDropdown).click();
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

	public void enterMajor_MinorValue(String idVal) {
		try {
			implWait(driver);

			driver.findElement(insertValue).click();
			driver.findElement(insertValue).sendKeys(idVal);
			waitTime(driver);
			grep.infoTest("Entering Value for Major: " + idVal);
			logger.info("Entering Value for Major: " + idVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSearchBtn() {
		implWait(driver);
		driver.findElement(searchBtn).click();
	}

	public void selectMajor_MinorValue_FromList(String majorVal) throws Exception {

		try {
			By selectMajor = By.xpath("//span[text()='" + majorVal + "']");

			waitForElementToBeClickable(selectMajor, 30);
			List<WebElement> state = driver.findElements(selectMajor);
			if (state.size() > 0) {
				state.getFirst().click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickOk_inSelectMajorPopup() {
		implWait(driver);
		driver.findElement(ok_inSelectMajor).click();
	}

	// MINOR
	public void clickMinorCategoryDropdownBtn() throws Exception {
		try {
//				implWait(driver);
			waitForElementToBeClickable(minorCatgDropdown, 20);
			boolean elementExists = !driver.findElements(minorCatgDropdown).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(minorCatgDropdown, 30);
				driver.findElement(minorCatgDropdown).click();
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void clickOk_inSelectMinorPopup() {
		implWait(driver);
		driver.findElement(ok_inSelectMinor).click();
	}

	public void clickOk_inCategoryPopup() {
		implWait(driver);
		driver.findElement(ok_inCategoryPopup).click();
	}

	public void enterAssetDescription(String desc) {
		try {
			waitForElementToBeClickable(assetDesc, 30);
			driver.findElement(assetDesc).click();
			driver.findElement(assetDesc).sendKeys(desc);
			waitTime(driver);
			grep.infoTest("Entering Asset Description: " + desc);
			logger.info("Entering Asset Description: " + desc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterAssetCost(String costVal) {
		try {
			implWait(driver);

			driver.findElement(assetCost).click();
			driver.findElement(assetCost).sendKeys(costVal);
			waitTime(driver);
			grep.infoTest("Entering Asset Cost: " + costVal);
			logger.info("Entering Asset Cost: " + costVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSearchAssetExpensesAccountBtn() throws Exception {
		try {
//				implWait(driver);
			waitForElementToBeClickable(searchExpenseAccount, 20);
			boolean elementExists = !driver.findElements(searchExpenseAccount).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(searchExpenseAccount, 30);
				driver.findElement(searchExpenseAccount).click();
			} else {
				logger.error("Search Expense Account Not Available ");
				grep.failTest("Search Expense Account Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void searchAndSelectExpensesAccount(String companyVal, String deptVal, String futureVal) {
		try {
			implWait(driver);

			By selectCompanyVal_inExpAccPopup = By.xpath("//div[@title='" + companyVal + "']");
			By selectDeptVal_inExpAccPopup = By.xpath("//div[@title='" + deptVal + "']");
			By selectFutureValue_inExpAccPopup = By.xpath("//div[@title='" + futureVal + "']");

			waitForElementToBeClickable(enterCompany_inExpAccPopup, 120);
			waitTime2(driver);
			driver.findElement(enterCompany_inExpAccPopup).sendKeys(companyVal);
			waitTime(driver);
			grep.infoTest("Selecting Company Value: " + companyVal);
			logger.info("Selecting Company Value: " + companyVal);
			waitTime(driver);
			driver.findElement(selectCompanyVal_inExpAccPopup).click();
			waitTime(driver);
			waitForElementToBeClickable(enterDepartment_inExpAccPopup, 20);
			driver.findElement(enterDepartment_inExpAccPopup).sendKeys(deptVal);
			waitTime(driver);
			grep.infoTest("Selecting Department Value: " + deptVal);
			logger.info("Selecting Department Value: " + deptVal);
			waitTime(driver);
			driver.findElement(selectDeptVal_inExpAccPopup).click();
			waitTime(driver);
			waitForElementToBeClickable(enterFuture_inExpAccPopup, 20);
			driver.findElement(enterFuture_inExpAccPopup).sendKeys(futureVal);
			waitTime(driver);
			grep.infoTest("Selecting Future Value: " + futureVal);
			logger.info("Selecting Future Value: " + futureVal);
			waitTime(driver);
			driver.findElement(selectFutureValue_inExpAccPopup).click();
			waitTime2(driver);
			driver.findElement(okBtn_inExpAccPopup).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// LOCATION
	public void clickSearchAssetLocationBtn() throws Exception {
		try {
//				implWait(driver);
			waitForElementToBeClickable(searchLocation, 60);
			boolean elementExists = !driver.findElements(searchLocation).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(searchLocation, 30);
				driver.findElement(searchLocation).click();
			} else {
				logger.error("Search Location Not Available ");
				grep.failTest("Search Location Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

//	public void enterCountry_inLocationPopup(String countryVal) throws Exception {
//		try {
//			By selectCountry = By.xpath("//div[@title='" + countryVal + "']");
////				implWait(driver);
//			waitForElementToBeClickable(country_inLocationPopup, 20);
//			boolean elementExists = !driver.findElements(country_inLocationPopup).isEmpty();
//			if (elementExists) {
//				WebElement country = driver.findElement(country_inLocationPopup);
//				WebElement select_Country = driver.findElement(selectCountry);
//
//				country.sendKeys(countryVal);
//				waitTime(driver);
//				if (select_Country.isDisplayed()) {
//					select_Country.click();
//				} else {
//					country.clear();
//					country.sendKeys(countryVal);
//					select_Country.click();
//				}
//
//				waitTime(driver);
//				grep.infoTest("Selecting Country Value: " + countryVal);
//				logger.info("Selecting  Country: " + countryVal);
//				waitTime2(driver);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			grep.failTest("Test Failed :" + e.getMessage());
//			logger.error("Test Failed :" + e.getMessage());
//		}
//	}
	public void enterCountry_inLocationPopup(String countryVal) throws Exception {
		try {
			By selectCountry = By.xpath("//div[@title='" + countryVal + "']");
//				implWait(driver);
			waitForElementToBeClickable(country_inLocationPopup, 20);
			boolean elementExists = !driver.findElements(country_inLocationPopup).isEmpty();
			if (elementExists) {
				driver.findElement(country_inLocationPopup).sendKeys(countryVal);
				waitTime5(driver);
				waitForElementToBeClickable(selectCountry, 20);
//				driver.findElement(country_inLocationPopup).click();
				driver.findElement(selectCountry).click();
				waitTime(driver);
				grep.infoTest("Selecting Country Value: " + countryVal);
				logger.info("Selecting  Country: " + countryVal);
				waitTime2(driver);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
//			grep.failTest("Test Failed :" + e.getMessage());
//			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void enterState_inLocationPopup(String stateVal) throws Exception {
		try {
			By selectState = By.xpath("//div[@title='" + stateVal + "']");
//				implWait(driver);
			waitForElementToBeClickable(state_inLocationPopup, 20);
			boolean elementExists = !driver.findElements(state_inLocationPopup).isEmpty();
			if (elementExists) {
				driver.findElement(state_inLocationPopup).sendKeys(stateVal);
				waitTime2(driver);
				driver.findElement(selectState).click();
				waitTime(driver);
				grep.infoTest("Selecting State Value: " + stateVal);
				logger.info("Selecting  State Value: " + stateVal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void enterCity_inLocationPopup(String cityVal) throws Exception {
		try {
			By selectCity = By.xpath("//div[@title='" + cityVal + "']");
//				implWait(driver);
			waitForElementToBeClickable(city_inLocationPopup, 20);
			boolean elementExists = !driver.findElements(city_inLocationPopup).isEmpty();
			if (elementExists) {
				driver.findElement(city_inLocationPopup).sendKeys(cityVal);
				waitTime(driver);
				driver.findElement(selectCity).click();
				waitTime(driver);
				grep.infoTest("Selecting City Value: " + cityVal);
				logger.info("Selecting  City Value: " + cityVal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void enterBuilding_inLocationPopup(String buildingVal) throws Exception {
		try {
			By selectBuilding = By.xpath("//div[@title='" + buildingVal + "']");
//				implWait(driver);
			waitForElementToBeClickable(building_inLocationPopup, 20);
			boolean elementExists = !driver.findElements(building_inLocationPopup).isEmpty();
			if (elementExists) {
				driver.findElement(building_inLocationPopup).sendKeys(buildingVal);
				waitTime(driver);
				driver.findElement(selectBuilding).click();
				waitTime(driver);
				grep.infoTest("Selecting Building Value: " + buildingVal);
				logger.info("Selecting  Building Value: " + buildingVal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void enterFuture_inLocationPopup(String futureVal) throws Exception {
		try {
			By selectFuture = By.xpath("//div[@title='" + futureVal + "']");
//				implWait(driver);
			waitForElementToBeClickable(future_inLocationPopup, 20);
			boolean elementExists = !driver.findElements(future_inLocationPopup).isEmpty();
			if (elementExists) {
				driver.findElement(future_inLocationPopup).sendKeys(futureVal);
				waitTime(driver);
				driver.findElement(selectFuture).click();
				waitTime(driver);
				grep.infoTest("Selecting Future Value: " + futureVal);
				logger.info("Selecting Future Value: " + futureVal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void clickNextBtn_inAssetPopup() {
		waitForElementToBeClickable(nextBtn, 30);
		driver.findElement(nextBtn).click();
	}

	public void enterAssetNumber(String numVal) {
		try {
			implWait(driver);

			driver.findElement(assetNumber).click();
			driver.findElement(assetNumber).sendKeys(numVal);
			waitTime(driver);
			grep.infoTest("Entering Asset Number: " + numVal);
			logger.info("Entering Asset Number: " + numVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSearchAssetKeyBtn() throws Exception {
		try {
			scrollView(searchAssetKey);
			boolean elementExists = !driver.findElements(searchAssetKey).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(searchAssetKey, 30);
				driver.findElement(searchAssetKey).click();
			} else {
				logger.error("Search Key Not Available ");
				grep.failTest("Search Key Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void clickFA_AssetKeyDropdownBtn(String key) throws Exception {
		try {
			By selectKey = By.xpath("//span[text()='" + key + "']");
			waitForElementToBeClickable(faAssetKey_Dropdown, 20);
			driver.findElement(faAssetKey_Dropdown).click();
			waitTime1(driver);
			driver.findElement(faAssetKey_Dropdown).click();

			waitForElement(selectKey, 10);
			driver.findElement(selectKey).click();
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void clickSaveAndClose_AssetBtn() throws InterruptedException {
		implWait(driver);

		driver.findElement(saveAndCloseAsset).click();
		waitTime(driver);
		driver.findElement(saveAsset).click();
	}

	public void getAssetSave_ConfirmationMsg() throws Exception {
		try {
			// 1. Wait for the popup text to be visible
			waitForElement(confirmMsg, 40);
			WebElement popupElement = driver.findElement(confirmMsg);
			waitTime(driver);
			String fullText = popupElement.getText().trim(); // Example: "Process 4796079 was submitted."

			logger.info("Asset Saved Confirmation Message: " + fullText);
			grep.infoTest("Asset Saved Confirmation Message: " + fullText);
			waitTime(driver);
			validAssert.equalsAssert(fullText, "Your changes were saved.");
			waitTime(driver);
			driver.findElement(okBtn_inConfirmationPopup);

		} catch (Exception e) {
			logger.error("Failed to retrieve Process ID: " + e.getMessage());
		}
	}

	public void clickSubmitBtn_inAsset() {
		waitForElementToBeClickable(submitBtn_inAsset, 30);
		driver.findElement(submitBtn_inAsset).click();
	}

	public void clickReadyToPostBtn_inAsset() {
		waitForElementToBeClickable(readyToPost, 30);
		driver.findElement(readyToPost).click();
	}

	public void selectExistingAsset(String assetVal) throws Exception {
		try {
			By selectAsset = By.xpath("//span[text()='" + assetVal + "']");
			waitForElementToBeClickable(selectAsset, 20);
			driver.findElement(selectAsset).click();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		waitForElementToBeClickable(readyToPost, 30);
		driver.findElement(readyToPost).click();
	}

	public void clickPostAllBtn_inAsset() {
		waitForElementToBeClickable(postAllBtn, 30);
		driver.findElement(postAllBtn).click();
	}

	public void clickRefreshBtn_inAsset() {
		waitForElementToBeClickable(refreshBtn, 30);
		driver.findElement(refreshBtn).click();
	}

	public void enterAssetNumber_inInquireAsset(String assetVal) {
		try {
			implWait(driver);

			driver.findElement(inquireAssetNum).click();
			driver.findElement(inquireAssetNum).sendKeys(assetVal.toUpperCase());
			waitTime(driver);
			grep.infoTest("Entering Value for Asset Number: " + assetVal);
			logger.info("Entering Value for Asset Number: " + assetVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickDone_inInquireAssetPage() {
		implWait(driver);
		driver.findElement(done_inInquireAsset).click();
	}

	public void clickDepricationArrowBtn_inAssets() {
		waitForElementToBeClickable(depricationBtn, 30);
		driver.findElement(depricationBtn).click();
	}

	public void clickCalculateDepricationBtn_inAssets() {
		waitForElementToBeClickable(calculateDepricationBtn, 30);
		driver.findElement(calculateDepricationBtn).click();
	}

	public void waitForProcessSuccess(int timeoutInMinutes) throws Exception {
		By taskNameLoc = By
				.xpath("//span[text()='Create Accounting for Assets']/ancestor::td/following-sibling::td[2]");
		long endTime = System.currentTimeMillis() + (timeoutInMinutes * 60L * 1000L);
		boolean isSuccess = false;

		logger.info("Monitoring status for Asset Process");

		while (System.currentTimeMillis() < endTime) {
			try {
				WebElement statusElement = driver.findElement(taskNameLoc);
				scrollView(taskNameLoc);
				waitTime(driver);
				String currentStatus = statusElement.getText().trim();

				logger.info("Current Status of Asset process :" + currentStatus);
				grep.infoTest("Current Status of Asset process : " + currentStatus);

				if (currentStatus.equalsIgnoreCase("Succeeded") || currentStatus.equalsIgnoreCase("Success")) {
					isSuccess = true;
					grep.infoTest("Sub Process completed successfully.");
					logger.info("Sub Process completed successfully.");
					break;
				}
				if (currentStatus.equalsIgnoreCase("Error") || currentStatus.equalsIgnoreCase("Failed")) {
					grep.warnTest("Sub Process failed with status: " + currentStatus);
				}

			} catch (Exception e) {
				System.out.println("Process row not found yet. Refreshing...");
			}
			try {
				driver.findElement(refreshBtn).click();

				waitTime10(driver);
			} catch (Exception refEx) {
				logger.warn("Could not click refresh button.");
			}

		}

		if (!isSuccess) {
			grep.warnTest("Timeout: Create Processing For Asset Processs did not succeed within " + timeoutInMinutes
					+ " minutes.");
			logger.error("Timeout: Create Processing For Asset Processs did not succeed within " + timeoutInMinutes
					+ " minutes.");
		}
	}
}