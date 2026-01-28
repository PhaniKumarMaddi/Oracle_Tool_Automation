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
	String requisitionId;

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

	@Test
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
		String[] parts = getSubTitle.trim().split(" ");
		requisitionId = parts[parts.length - 1];
		System.out.println("Requisition Id retrieved: " + requisitionId);
		grep.infoTest("Requisition Id retrieved: " + requisitionId);
		logger.info("Requisition Id retrieved: " + requisitionId);

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

	@Test
	public void oracle_PurchaseOrder_Test() throws Exception {
		Oracle_HomePage oraHome = new Oracle_HomePage();
//		oraHome.clickHomeButton();
		waitTime(driver);

		grep.testCreate("Create Purchase Order Test", "Create Purchase Order Test");
		oraHome.NavigateBackToHome();

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
		waitTime15(driver);

		grep.captureScreenshot("pass", "Search Reqisition page", "SearchProcessReqisitionPage");

		waitTime(driver);
		grep.infoTest("Select the Requisition for which you have to create a Purchase Order");
		logger.info("Select the Requisition for which you have to create a Purchase Order");

		oraHome.selectRequisitionFromList(requisitionId);
//		oraHome.selectRequisitionFromList("CRREQ100022");
		waitTime3(driver);
		grep.infoTest("Click on 'Add to Document Builder'");
		logger.info("Click on 'Add to Document Builder'");
		oraHome.clickAddToDocumentBuilderBtn();
		waitTime3(driver);
		grep.captureScreenshot("pass", "Inside Document Builder Popup", "DocumentBuilderPopup");

		grep.infoTest("Validate requisition id from Document builder popup");
		logger.info("Validate requisition id from Document builder popup");
		oraHome.getRequisitionIdFromPopup(requisitionId);
//		oraHome.getRequisitionIdFromPopup("CRREQ100022");
		waitTime(driver);
		oraHome.searchAndSelectSupplier("CR Dell");
		waitTime(driver);
		grep.captureScreenshot("pass", "Filling details in Document Builder Popup",
				"AfterFilling_DocumentBuilderFields");

		oraHome.clickOkBtn_inDocBuilder_Popup();
		waitTime5(driver);

		grep.infoTest("Click on Create");
		logger.info("Click on Create");
		oraHome.clickCreateBtn_inProcessRequsitionPage();
		waitTime(driver);
		String orderConfirmMsg = oraHome.validatePurchaseOrderCreationConfirmation();
		grep.infoTest("Purchase Order Created Confirmation Message Popup: " + orderConfirmMsg);
		logger.info("Purchase Order Created Confirmation Message Popup: " + orderConfirmMsg);
		waitTime(driver);
		grep.captureScreenshot("pass", "Purchase Order Created Message Popup", "PurchaseOrderCreatedMessage");
		waitTime(driver);
		oraHome.clickOk_inConfirmPO_popup();
		waitTime(driver);
		oraHome.validateRequisitionIdAfterCreatingPo(requisitionId);
		waitTime(driver);

		oraHome.clickSavePoBtn();
		waitTime5(driver);

		grep.infoTest("Click on Actions and Validate");
		logger.info("Click on Actions and Validate");
		oraHome.clickActionAndValidateBtn();
		waitTime3(driver);
		String orderValidationMsg = oraHome.validatePurchaseOrderCreationConfirmation();
		grep.infoTest("Order Validation Confirmation Message Popup: " + orderValidationMsg);
		logger.info("Order Validation Confirmation Message Popup: " + orderValidationMsg);
		waitTime(driver);
		grep.captureScreenshot("pass", "Purchase Order Validation Message Popup", "PurchaseOrder_ValidationMessage");
		waitTime(driver);
		oraHome.clickOk_inConfirmPO_popup();
		waitTime(driver);
		oraHome.submitPo_ForApprovalBtn();
		waitTime5(driver);
		String submitApprlMsg = oraHome.validatePurchaseOrderCreationConfirmation();
		grep.infoTest("Order Submitted for Approval Message Popup: " + submitApprlMsg);
		logger.info("Order Submitted for Approval Message Popup: " + submitApprlMsg);
		waitTime(driver);
		grep.captureScreenshot("pass", "Purchase Order Submitted for Approval Message Popup",
				"PO_SubmitApprovalMessage");
		waitTime(driver);
		oraHome.clickOk_inConfirmPO_popup();
		waitTime5(driver);

		grep.infoTest("To check summary details click on Tasks");
		logger.info("To check summary details click on Tasks");
		waitTime(driver);
		oraHome.click_Tasks_InPO();
		oraHome.click_ManageOrders_InTask();
		waitTime2(driver);
		grep.captureScreenshot("pass", "Inside Manage Orders Page", "ManageOrdersPage");
		waitTime15(driver);
		oraHome.clickSearchBtn();
		waitTime5(driver);
		grep.captureScreenshot("pass", "Searching Approved PO in manage order Page", "Searching_Approved_PO");

		waitTime(driver);
		grep.infoTest("Verify the Status is Open means PO is approved");
		logger.info("Verify the Status is Open means PO is approved");
		waitTime(driver);
		oraHome.verifyPurchaseOrderState("CRPO500019-2025", 10);

	}

}
