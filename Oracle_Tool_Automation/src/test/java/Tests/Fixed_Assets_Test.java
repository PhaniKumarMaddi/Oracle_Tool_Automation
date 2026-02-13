package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Pages.Oracle_Assets_Page;
import Pages.Oracle_HomePage;
import Pages.TestInitializer;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class Fixed_Assets_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Fixed_Assets_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();

	@Test
	public void oracle_Fixed_Assets_Test() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		Oracle_Assets_Page oraAp = new Oracle_Assets_Page();

		waitTime(driver);
		grep.testCreate("Verify Creation of Assets Test", "Verify Creation of Assets");
		oraHome.clickHomeButton();

		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab(dataTest.fixedAssetsNavTab);
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.assetsCatg);
		waitTime3(driver);
		grep.infoTest("Navigating to Assets");
		logger.info("Navigating to Assets");
		waitTime(driver);
		oraHome.click_Tasks_InPO();
		waitTime(driver);
		oraHome.selectTasks_InTaskPage(dataTest.addAssetTask);
		waitTime(driver);

		grep.captureScreenshot("pass", "Navigating Add Assets Page", "Inside_Add_AssetsPage");
		waitTime(driver);
		oraAp.clickSearchAssetCategoryBtn();
		waitTime(driver);
		grep.infoTest("Selecting Major Category");
		logger.info("Selecting Major Category");
		waitTime(driver);
		oraAp.clickMajorCategoryDropdownBtn();
		waitTime(driver);
		oraAp.clickSearch_InDropdown();
		waitTime(driver);
		oraAp.enterMajor_MinorValue(dataTest.majorCategory);
		waitTime(driver);
		oraAp.clickSearchBtn();
		waitTime(driver);
		oraAp.selectMajor_MinorValue_FromList(dataTest.majorCategory);
		waitTime(driver);
		oraAp.clickOk_inSelectMajorPopup();
		waitTime(driver);

		grep.infoTest("Selecting Minor Category");
		logger.info("Selecting Minor Category");
		waitTime(driver);
		oraAp.clickMinorCategoryDropdownBtn();
		waitTime(driver);
		oraAp.clickSearch_InDropdown();
		waitTime(driver);
		oraAp.enterMajor_MinorValue(dataTest.minorCategory);
		waitTime(driver);
		oraAp.clickSearchBtn();
		waitTime(driver);
		oraAp.selectMajor_MinorValue_FromList(dataTest.minorCategory);
		waitTime(driver);
		oraAp.clickOk_inSelectMinorPopup();
		waitTime(driver);

		grep.captureScreenshot("pass", "Selecting Major and Minor Values", "Major_Minor_Values");
		waitTime(driver);
		oraAp.clickOk_inCategoryPopup();
		waitTime3(driver);
		oraAp.enterAssetDescription(dataTest.assetDescription);
		waitTime(driver);
		oraAp.enterAssetCost(dataTest.assetCost);
		waitTime(driver);
		grep.infoTest("Selecting Asset Expenses Account");
		logger.info("Selecting Asset Expenses Account");
		waitTime(driver);
		oraAp.clickSearchAssetExpensesAccountBtn();
		waitTime(driver);
		oraAp.searchAndSelectExpensesAccount(dataTest.journalLine_company, dataTest.journalLine_dept_2,
				dataTest.journalLine_future);
		waitTime(driver);
		grep.infoTest("Selecting Asset Location");
		logger.info("Selecting Asset Location");
		waitTime(driver);
		oraAp.clickSearchAssetLocationBtn();
		waitTime1(driver);
		oraAp.enterCountry_inLocationPopup(dataTest.assetCountry);
		waitTime1(driver);
		oraAp.enterState_inLocationPopup(dataTest.assetState);
		waitTime1(driver);
		oraAp.enterCity_inLocationPopup(dataTest.assetCity);
		waitTime1(driver);
		oraAp.enterBuilding_inLocationPopup(dataTest.assetBuilding);
		waitTime1(driver);
		oraAp.enterFuture_inLocationPopup(dataTest.assetFuture);
		waitTime1(driver);
		oraAp.clickOk_inCategoryPopup();
		waitTime5(driver);

		grep.captureScreenshot("pass", "Filled Asset details in Add Asset Popup", "afterFilling_inAssetPopup");
		waitTime(driver);
		oraAp.clickNextBtn_inAssetPopup();
		waitTime(driver);
		oraAp.enterAssetNumber(dataTest.assetNumber);
		waitTime(driver);
		oraAp.clickSearchAssetKeyBtn();
		waitTime(driver);
		oraAp.clickFA_AssetKeyDropdownBtn(dataTest.assetKey);
		waitTime(driver);
		oraAp.clickOk_inCategoryPopup();
		waitTime(driver);
		oraAp.clickSaveAndClose_AssetBtn();
		waitTime(driver);
		oraAp.getAssetSave_ConfirmationMsg();
		waitTime(driver);
		oraAp.clickSubmitBtn_inAsset();
		waitTime(driver);
		oraAp.clickReadyToPostBtn_inAsset();
		waitTime(driver);
		oraAp.selectExistingAsset(dataTest.assetNumber);
		waitTime(driver);
		oraAp.clickPostAllBtn_inAsset();
		waitTime(driver);
		grep.captureScreenshot("pass", "Asset Posted Successfully", "psotinngAssetSuccesful");
		waitTime(driver);

		oraHome.click_Tasks_InPO();
		waitTime(driver);
		oraHome.selectTasks_InTaskPage(dataTest.inquireAssetTask);
		waitTime(driver);
		
	}

}
