package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Pages.Oracle_HomePage;
import Pages.TestInitializer;
import Utility.GenerateReports;
import Utility.ValidatingAssertions;

public class Oracle_ProcurementTest extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Oracle_ProcurementTest.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();

//	@DataProvider(name = "ProcurementTest")
//	public Object[][] getData() {
//		// Get Excel Test Data passing Excel File Name and Sheet Name
//		Object data[][] = ExcelDataProvider.testData("Oracle_TestData", "Procurement");
//		return data;
//	}

//	@Test(dataProvider = "ProcurementTest")
//	public void oracle_Procurement_Test(String itemDesc, String categoryValue, String quantityValue, String uomValue,
//			String priceValue) throws Exception {
//		String quantity_Value = String.valueOf(quantityValue);
//		String price_Value = String.valueOf(priceValue);

	@Test(enabled = false)
	public void oracle_Procurement_Test() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		grep.testCreate("Verify Navigate to Procurement Page Functionality Test", "Navigate to Procurement Page");
		oraHome.clickHomeButton();
		waitTime(driver);

		grep.infoTest("Clicking on Home icon");
		logger.info("Clicking on Home icon");

		grep.captureScreenshot("pass", "Inside Home Page ", "Oracle_HomePage");
		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.clickProcurementNavigation();
		grep.captureScreenshot("pass", "Expanding procurement in Navigator ", "Expand_ProcurementNavigation");
		waitTime(driver);
		oraHome.clickPurchaseRequisitions();
		waitTime1(driver);
		oraHome.validatePurchaseRequisitionsPage();
		oraHome.validateMyRecentRequisitionsTitle();
		waitTime(driver);
		grep.captureScreenshot("pass", "Inside Purchase Requisition Page", "PurchaseRequisitionPage");
		waitTime(driver);
		oraHome.clickCreateNonCatalogBtn();
		waitTime(driver);

		grep.testCreate("Verify Create NonCatalog Request Page Functionality Test",
				"Create NonCatalog Request Page Functionality Test");
		waitTime(driver);

		oraHome.validateNonCatalogHeaderTitle();
		waitTime2(driver);
		grep.captureScreenshot("pass", "Inside Non Catalog Request Page", "NonCatalogRequestPage");

		waitTime(driver);
		oraHome.enterItemDescription("Mouse");
//		oraHome.enterItemDescription(itemDesc);
		waitTime(driver);
		oraHome.enterCategoryField("CR.MISC");
//		oraHome.enterCategoryField(categoryValue);
		waitTime(driver);
		oraHome.enterQuantity("3");
//		oraHome.enterQuantity(quantity_Value);
		waitTime(driver);
		oraHome.enterUnitOfMeasureField("Each");
//		oraHome.enterUnitOfMeasureField(uomValue);
		waitTime(driver);
		oraHome.enterPrice("30");
//		oraHome.enterPrice(price_Value);
		waitTime2(driver);

		grep.captureScreenshot("pass", "Filling Non Catalog Request Page", "Filling_NonCatalogRequestPage");

		waitTime(driver);

		grep.infoTest("Click Add to Cart Button");
		logger.info("Click Add to Cart Button");
		oraHome.clickAddToCartBtn();

		waitTime(driver);

		grep.infoTest("Verify and Click on view cart Button");
		logger.info("Verify and Click on view cart Button");
		oraHome.validateAddToCartConfiramtionPopup();
		waitTime2(driver);
		grep.captureScreenshot("pass", "Add Procurement to cart test", "AddProcurementToCart_Test");
		waitTime(driver);

		oraHome.clickNavigateToCart();
		waitTime(driver);
		grep.testCreate("Verify Product Details in Cart Page Test", "Verify Product Details in Cart Page");
		waitTime(driver);
		oraHome.validateCartPageDetails("Cart", "Mouse", "Each", "30");
		waitTime(driver);

		String getSubTitle = oraHome.getSubTitleDetails();

		System.out.println("Cart Page Subtitle retrieved: " + getSubTitle);

		int getPrice = oraHome.validatePriceDetails();

		logger.info("Raw Price retrieved: " + getPrice);
		grep.infoTest("Raw Price retrieved: " + getPrice);
		validAssert.equalsAssert_int(getPrice, 30);
		waitTime(driver);
		oraHome.validateRequisitionSummary(getPrice, 3);

		waitTime(driver);
		grep.captureScreenshot("pass", "Verify Product Details in Cart Page Test", "ProcurementDetailsInCart_Test");
		waitTime(driver);
		oraHome.clickSubmitCartBtn();
		waitTime(driver);
		oraHome.validateSubmitRequisitionPopup();
		String getDetails = oraHome.getSubmitRequisitionMessage();
		logger.info("After Submitting Requisition: " + getDetails);
		grep.infoTest("After Submitting Requisition: " + getDetails);
		waitTime(driver);
		validAssert.trueAssert(getDetails.startsWith("Requisition CRRE"));
		waitTime(driver);

		grep.captureScreenshot("pass", "Submit Requisition Test", "SubmitRequsition_Test");
		waitTime2(driver);

		oraHome.validateSubmittedRequisitionState("Pending approval");
		grep.captureScreenshot("pass", "Submit Requisition in Pending state Test", "Pending_SubmitRequsition_Test");
//		waitTime15(driver);
//		waitTime5(driver);
//		refreshPage();
		waitTime2(driver);
		oraHome.verifyRequisitionApproveState(5);

		oraHome.validateSubmittedRequisitionState("Approved");
		grep.captureScreenshot("pass", "Submit Requisition in Approved state Test", "Approved_SubmitRequsition_Test");

		waitTime(driver);
	}

	@Test()
	public void oracle_PurchaseOrder_Test() throws Exception {
		Oracle_HomePage oraHome = new Oracle_HomePage();
		oraHome.clickHomeButton();
		waitTime(driver);

		grep.testCreate("Create Purchase Order Test", "Create Purchase Order Test");
//		oraHome.NavigateBackToHome();

		logger.info("Navigating to Procurement Tab in homepage ");
		grep.infoTest("Navigating to Procurement Tab in homepage ");
		oraHome.selectTabWithNavigator("Procurement");
		waitTime(driver);
		grep.captureScreenshot("pass", "Navigating to Procurement Tab in homepage ", "ProcurementTabInhomepage");
		waitTime(driver);

		logger.info("Click on 'Process Reqisition' under Quick actions");
		grep.infoTest("Click on 'Process Reqisition' under Quick actions");

		oraHome.selectFromQuickActions("Process Requisitions");

		logger.info("Inside 'Process Reqisition' page");
		grep.infoTest("Inside 'Process Reqisition' Page");
		waitTime(driver);
		grep.captureScreenshot("pass", "Inside 'Process Reqisition' page", "InsideProcessReqisitionPage");
		oraHome.selectRequisitionBU("CRITICAL RIVER BU");
		oraHome.clearBuyer();
		waitTime(driver);

		grep.infoTest("Click Search Button");
		logger.info("Click Search Button");
		oraHome.clickSearchBtn();
		waitTime5(driver);
		
		grep.captureScreenshot("pass", "Search Reqisition page", "SearchProcessReqisitionPage");
	}

}
