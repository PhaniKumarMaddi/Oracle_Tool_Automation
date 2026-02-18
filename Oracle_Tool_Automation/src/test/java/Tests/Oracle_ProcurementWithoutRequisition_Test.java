package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.Oracle_HomePage;
import Pages.TestInitializer;
import Utility.ExcelDataProvider;
import Utility.GenerateReports;
import Utility.TestDataKeys;
import Utility.ValidatingAssertions;

public class Oracle_ProcurementWithoutRequisition_Test extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Oracle_ProcurementWithoutRequisition_Test.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();
	TestDataKeys dataTest = new TestDataKeys();
	String orderId;

	@DataProvider(name = "ProcurementTest")
	public Object[][] getData() {
		// Get Excel Test Data passing Excel File Name and Sheet Name
		Object data[][] = ExcelDataProvider.testData("Oracle_TestData", "Procuremen_WithoutRequisition");
		return data;
	}

//	@Test
//	public void oracle_ProcurementWithout_Requisition_Test() throws Exception {
	@Test(dataProvider = "ProcurementTest")
	public void oracle_ProcurementWithout_Requisition_Test(String shipTo, String itemLineInPO, String quantityValue,
			String dateValue, String monthValue, String yearValue) throws Exception {

		waitTime(driver);
		oracle_PurchaseOrderWithout_Requisition_Test(shipTo, itemLineInPO, quantityValue, dateValue, monthValue,
				yearValue);
		create_Receipt_PurchaseOrder_WithoutRequisition_Test();
		oracle_PutAwayReceipt_WithoutRequisition_Test();
	}

	public void oracle_PurchaseOrderWithout_Requisition_Test(String shipToVal, String itemLineInPOVal,
			String quantityVal, String dateVal, String monthVal, String yearVal) throws Exception {
		Oracle_HomePage oraHome = new Oracle_HomePage();

		waitTime(driver);
		oraHome.clickHomeButton();
		waitTime(driver);

		grep.testCreate("Create Purchase Order Test Without Requisition",
				"Create Purchase Order Test Without Requisition");

		logger.info("Navigating to Procurement Tab in homepage ");
		grep.infoTest("Navigating to Procurement Tab in homepage ");
		waitTime(driver);

		oraHome.selectTabWithNavigator(dataTest.procurementNavTab);
		waitTime(driver);
		grep.captureScreenshot("pass", "Navigating to Procurement Tab in homepage ", "ProcurementTabInhomepage");
		waitTime(driver);

		logger.info("Click on 'Purchase Orders' under Quick actions");
		grep.infoTest("Click on 'Purchase Orders' under Quick actions");

		oraHome.selectFromQuickActions(dataTest.purchaseOrderAction);

		logger.info("Inside 'Purchase Orders' page");
		grep.infoTest("Inside 'Purchase Orders' Page");
		waitTime(driver);
		grep.captureScreenshot("pass", "Inside 'Purchase Orders' page", "InsidePurchaseOrdersPage");
		waitTime(driver);
		oraHome.click_Tasks_InPO();
		oraHome.selectTasks_InTaskPage(dataTest.createOrderTask);
		oraHome.searchAndSelectSupplier(dataTest.selectDellSupplier);
		waitTime(driver);
		oraHome.clickCreateBtn_inProcessRequsitionPage();
		waitTime2(driver);
//		oraHome.searchAndSelectDefaultShipToLocation("CRITICAL RIVER LOCATION");
		oraHome.searchAndSelectDefaultShipToLocation(shipToVal);
		waitTime(driver);
		oraHome.clickAddRowButton();
		waitTime2(driver);
//		oraHome.searchAndSelectItemInPO_Line("CR1002");
//		waitTime(driver);
//		oraHome.enterQuantity_InRowLine("2");
//		waitTime1(driver);
//		oraHome.enterRequestDeliveryDate("03", "02", "2026");
		oraHome.searchAndSelectItemInPO_Line(itemLineInPOVal);
		waitTime(driver);
		oraHome.enterQuantity_InRowLine(quantityVal);
		waitTime1(driver);
		oraHome.enterRequestDeliveryDate(dateVal, monthVal, yearVal);
		waitTime1(driver);
		grep.captureScreenshot("pass", "Entering Valid Values in Row Line", "enterValuesInRowLine");
		waitTime(driver);

		oraHome.clickSavePoBtn();
		waitTime5(driver);

		grep.infoTest("Click on Actions and Validate");
		logger.info("Click on Actions and Validate");
		oraHome.clickActionAndValidateBtn();
		waitTime5(driver);
		String orderValidationMsg = oraHome.validatePurchaseOrderCreationConfirmation();
		grep.infoTest("Order Validation Confirmation Message Popup: " + orderValidationMsg);
		logger.info("Order Validation Confirmation Message Popup: " + orderValidationMsg);
		waitTime(driver);
		validAssert.equalsAssert(orderValidationMsg, "No errors or warnings were found.");
		waitTime(driver);
		grep.captureScreenshot("pass", "Purchase Order Validation Message Popup Without Requisition",
				"PurchaseOrder_ValidationMessage_WithoutRequisition");
		waitTime(driver);
		oraHome.clickOk_inConfirmPO_popup();
		waitTime(driver);
		oraHome.submitPo_ForApprovalBtn();
		waitTime5(driver);
		String submitApprlMsg = oraHome.validatePurchaseOrderCreationConfirmation();
		grep.infoTest("Order Submitted for Approval Message Popup: " + submitApprlMsg);
		logger.info("Order Submitted for Approval Message Popup: " + submitApprlMsg);
		waitTime(driver);
		validAssert.trueAssert(submitApprlMsg.startsWith("The document (Purchase Order)"));
		waitTime(driver);
		grep.captureScreenshot("pass", "Purchase Order Submitted for Approval Message Popup Without Requisition",
				"PO_SubmitApprovalMessage_WithoutRequisition");
		waitTime(driver);
		oraHome.clickOk_inConfirmPO_popup();
		waitTime5(driver);
		orderId = oraHome.getOrderId(submitApprlMsg);
		waitTime2(driver);
		grep.infoTest("Retrieving Order Id :" + orderId);
		logger.info("Retrieving Order Id :" + orderId);
		waitTime10(driver);

		grep.infoTest("To check summary details click on Tasks");
		logger.info("To check summary details click on Tasks");
		waitTime(driver);
		oraHome.click_Tasks_InPO();
		waitTime10(driver);
		oraHome.selectTasks_InTaskPage(dataTest.manageOrderTask);
		waitTime2(driver);
		grep.captureScreenshot("pass", "Inside Manage Orders Page Without Requisition",
				"ManageOrdersPage_WithoutRequisition");
		waitTime10(driver);
		oraHome.clickSearchBtn();
		waitTime2(driver);
		grep.captureScreenshot("pass", "Searching Approved PO in manage order Page Without Requisition",
				"Searching_Approved_PO_WithoutRequisition");

		waitTime2(driver);
		grep.infoTest("Verify the Status is Open means PO is approved");
		logger.info("Verify the Status is Open means PO is approved");
		waitTime(driver);

		oraHome.verifyPurchaseOrderState(orderId, 3, "Open");
		waitTime2(driver);
	}

	public void create_Receipt_PurchaseOrder_WithoutRequisition_Test() throws Exception {
		Oracle_HomePage oraHome = new Oracle_HomePage();

		oraHome.clickHomeButton();
		waitTime(driver);

		grep.testCreate("Create Receipt for Purchase Order with Inventory Without Requisition",
				"Create Receipt for Purchase Order with Inventory Without Requisition");
		waitTime(driver);
		oraHome.clickNavigator();
		waitTime(driver);
		oraHome.selectNavigationTab(dataTest.supplyChainNavTab);
		grep.captureScreenshot("pass", "Expanding Supply Chain Execution in Navigator ",
				"Expand_SupplyChainExecution_WithoutRequisition");
		waitTime(driver);
		oraHome.selectSubCategoryInNavigator(dataTest.inventoryMgmtCatg);
		waitTime1(driver);
		oraHome.click_Tasks_InPO();
		waitTime(driver);
		oraHome.selectShowTasksDropdown("Receipts");
		waitTime(driver);
		oraHome.selectTasks_InTaskPage(dataTest.receiveExptdShipmentsTask);
		waitTime(driver);
		oraHome.enterPurchaseOrderId(orderId);
//		oraHome.enterPurchaseOrderId("CRPO500046-2025");
		waitTime(driver);
		oraHome.clickSearchBtn();
		waitTime3(driver);
		grep.captureScreenshot("pass", "Search Order Id in receipt page",
				"SearchOrderId_InReceiptPage_WithoutRequisition");

		waitTime(driver);
		grep.infoTest("Select the Purchase Order for which we need to create receipt");
		logger.info("Select the Purchase Order for which we need to create receipt");
		waitTime(driver);

		oraHome.selectRequisitionFromList(orderId);
//		oraHome.selectRequisitionFromList("CRPO500046-2025");
		grep.infoTest("Click on Receive");
		logger.info("Click on Receive");
		oraHome.clickReceiveBtn();
		waitTime3(driver);
		grep.infoTest("Click on Show Receipt quantity to know quantity details");
		logger.info("Click on Show Receipt quantity to know quantity details");
		oraHome.clickShowReceiptBtn();
		waitTime3(driver);
		grep.infoTest("Click on 'Create Receipt' from right side menu");
		logger.info("Click on 'Create Receipt' from right side menu");
		oraHome.clickCreateReceiptBtn();
		waitTime2(driver);
		grep.captureScreenshot("pass", "After Creating Receipt", "createReceipt_Page_WithoutRequisition");
		waitTime2(driver);

		grep.infoTest("Click on 'submit' at the top to generate receipt");
		logger.info("Click on 'submit' at the top to generate receipt");
		oraHome.clickSubmitReceiptBtn();
		waitTime2(driver);
		String submitReceiptMsg = oraHome.validateReceiptNum_inSubmitConfirmationPopup();
		grep.infoTest("Receipt Submitted Confirmation Popup: " + submitReceiptMsg);
		logger.info("Receipt Submitted Confirmation popup: " + submitReceiptMsg);
		waitTime(driver);
		grep.captureScreenshot("pass", "After Submitting Receipt", "SubmitReceipt_Page_WithoutRequisition");
		waitTime2(driver);
		oraHome.clickOk_inSubmitConfirmationPopup();
		waitTime2(driver);

		grep.infoTest("Click on 'Done' at the top to generate receipt");
		logger.info("Click on 'Done' at the top to generate receipt");
		waitTime(driver);
		oraHome.clickDoneReceiptBtn();
		waitTime2(driver);

	}

	public void oracle_PutAwayReceipt_WithoutRequisition_Test() throws Exception {
		waitTime(driver);
		Oracle_HomePage oraHome = new Oracle_HomePage();
		oraHome.click_Tasks_InPO();
		waitTime(driver);
		oraHome.selectTasks_InTaskPage(dataTest.putAwayReceipsTask);
		waitTime(driver);
		grep.captureScreenshot("pass", "Inside Put Away Receipts Page", "putAwayreceipts_Page_WithoutRequisition");
		waitTime(driver);
		oraHome.enterPurchaseOrderId(orderId);
//		oraHome.enterPurchaseOrderId("CRPO500046-2025");
		waitTime(driver);
		oraHome.clickSearchBtn();
		grep.captureScreenshot("pass", "Search Order Id in put away page",
				"SearchOrderId_InPutAwayPage_WithoutRequisition");

		waitTime1(driver);
		grep.infoTest("Select the Purchase Order for which we need to create receipt");
		logger.info("Select the Purchase Order for which we need to create receipt");
		waitTime(driver);

		oraHome.selectRequisitionFromList(orderId);
//		oraHome.selectRequisitionFromList("CRPO500046-2025");
		grep.infoTest("Click on Put Away");
		logger.info("Click on Put Away");
		oraHome.clickPutAwayBtn();
		waitTime2(driver);

		grep.infoTest("Click on 'Sub Inventory' and select the CR STORE option");
		logger.info("Click on 'Sub Inventory' and select the CR STORE option");
		oraHome.searchAndSelectPutAwaySubInventory("CRSTORE");
		waitTime(driver);

		grep.infoTest("Click on 'submit' at the top to generate put away");
		logger.info("Click on 'submit' at the top to generate  put away");
		oraHome.clickSubmitReceiptBtn();
		waitTime2(driver);
		String submitPutAwayMsg = oraHome.validateReceiptNum_inSubmitConfirmationPopup();
		grep.infoTest("Receipt Submitted Confirmation Popup: " + submitPutAwayMsg);
		logger.info("Receipt Submitted Confirmation popup: " + submitPutAwayMsg);
		waitTime(driver);
		grep.captureScreenshot("pass", "After Submitting Put Away", "SubmitPutAway_Page_WithoutRequisition");
		waitTime2(driver);
		oraHome.clickOk_inSubmitConfirmationPopup();
		waitTime2(driver);
		oraHome.clickHomeFromPutAway();

		logger.info("Navigating to Procurement Tab in homepage ");
		grep.infoTest("Navigating to Procurement Tab in homepage ");
//		oraHome.selectTabWithNavigator("Procurement");
		waitTime(driver);
		logger.info("Click on 'Purchase Orders' under Quick actions");
		grep.infoTest("Click on 'Purchase Orders' under Quick actions");
		oraHome.selectFromQuickActions("Purchase Orders");
		logger.info("Inside 'Purchase Orders' page");
		grep.infoTest("Inside 'Purchase Orders' Page");
		waitTime(driver);
		oraHome.click_Tasks_InPO();
		waitTime1(driver);
		oraHome.selectTasks_InTaskPage(dataTest.manageOrderTask);
		waitTime10(driver);
		oraHome.clickSearchBtn();
		waitTime2(driver);
		grep.infoTest("Verify the Status is Open means PO is approved");
		logger.info("Verify the Status is Open means PO is approved");
		waitTime(driver);

		oraHome.verifyPurchaseOrderState(orderId, 3, "Closed for Receiving");

		grep.captureScreenshot("pass", "Searching Closed for Receiving PO in manage order Page Without Requisition",
				"Searching_ClosedReceiving_PO_WithoutRequisition");
	}

}
