package Tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Pages.Oracle_HomePage;
import Pages.TestInitializer;
import Utility.GenerateReports;

public class Oracle_ProcurementTest extends TestInitializer {
	private static final Logger logger = LogManager.getLogger(Oracle_ProcurementTest.class);
	GenerateReports grep = new GenerateReports();

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
		grep.infoTest("Entering Item Description");
		logger.info("Entering Item Description");
		waitTime(driver);
		oraHome.enterItemDescription("Mouse");
		waitTime(driver);
		grep.infoTest("Selecting Category");
		logger.info("Selecting Category");
		oraHome.enterCategoryField("CR.MISC");
		waitTime(driver);
		grep.infoTest("Enter Quantity field");
		logger.info("Enter Quantity field");
		oraHome.enterQuantity("3");
		waitTime(driver);
		grep.infoTest("Enter Unit of Measure");
		logger.info("Enter Unit of Measure");
		oraHome.enterUnitOfMeasureField("Each");
		waitTime(driver);
		grep.infoTest("Enter Price");
		logger.info("Enter Price");
		oraHome.enterPrice("30");
		waitTime2(driver);

		waitTime(driver);
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
		grep.testCreate("Verify Product Details in Cart Page Test",
				"Verify Product Details in Cart Page");
		waitTime(driver);
		

	}

}
