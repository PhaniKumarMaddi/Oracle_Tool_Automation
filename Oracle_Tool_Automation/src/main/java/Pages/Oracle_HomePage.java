package Pages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Utility.DriverManager;
import Utility.GenerateReports;
import Utility.ValidatingAssertions;
import Utility.WaitsManager;

public class Oracle_HomePage extends WaitsManager {
	protected WebDriver driver;
	private static Logger logger = LogManager.getLogger(Oracle_HomePage.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();

	public Oracle_HomePage() {
		this.driver = DriverManager.getDriver();
	}

	// navigate to procurement
	By homeBtn = By.xpath("//a[@id='pt1:_UIShome']");
	By homePageMsg = By.xpath("//div[@id='pt1:atkfr1:0:grid:0:pgl1']");
	By navigator = By.xpath("//a[@id='pt1:_UISmmLink']");
	By selfServiceProcurement = By.cssSelector("div.oj-sp-header-general-overview-title-badge-cont");
	By recentRequisition = By.cssSelector("h2#myRequisitionslabel");
	// Non catalog form
	By createNonCatalog = By.cssSelector("oj-button#nonCatalogRequestButton");
	By nonCatalogHeader = By.cssSelector("div#title");
	By itemDescription = By
			.xpath("//span[text()='Item Description']/ancestor::oj-label[1]/following-sibling::textarea");
	By categoryField = By
			.xpath("//span[text()='Category']/ancestor::oj-label/parent::div/input[contains(@id,'oj-selectsingle')]");
	By quantity = By.xpath(
			"//div[@class='oj-form oj-enabled oj-form-cols oj-formlayout-form-across']/descendant::span[text()='Quantity']/ancestor::oj-label/following-sibling::input");
	By uomField = By
			.xpath("//span[text()='UOM']/ancestor::oj-label/parent::div/input[contains(@id,'oj-selectsingle')]");

	By price = By.xpath("//label[text()='Price']/parent::div/input");
	By addToCart = By.xpath("//button/div/span[text()='Add to Cart']");

	// confirmation popup after click add to cart
	By confirmPopupHeader = By.cssSelector("div.oj-message-category.oj-message-title");
	By confirmPopupBody = By.cssSelector("div.oj-message-summary");
	By viewCart = By.xpath("//a[@on-click='[[$listeners.onNavigateToViewCart]]']");

	// My Cart page
	By cartPageTitle = By.xpath("//h1[@id='oj_gop1_h_pageTitle']");
	By cartPageSubtitle = By.xpath("//div[@id='oj_gop1_h_pageSubtitle']");
	By cartPageItemDesc = By.cssSelector(
			"div.oj-sm-align-items-center.oj-flex.oj-sm-flex-wrap-nowrap>div.oj-typography-body-md.oj-line-clamp-3 ");
	By cartPageUOM = By.cssSelector("div.oj-flex.oj-sm-align-items-baseline>div");
	By cartPagePrice = By.cssSelector("div.oj-flex.oj-sm-align-self-flex-end");
	By subTotalAmt = By.xpath("//div[text()='Subtotal']/following-sibling::div");
	By approvalAmt = By.xpath("//div[text()='Approval Amount']/following-sibling::div");
	By tax = By.xpath("//div[text()='Nonrecoverable Tax']/following-sibling::div");
	By submitBtn = By.xpath("//button[text()='Submit']");
	// gt submit details
	By submitMsgDetails = By.cssSelector("div.oj-message-detail");

	// requisition page
	By requsitionStage = By
			.xpath("//div[@class='oj-sp-card-common-badge-container oj-sp-card-common-badge-margin-end']");
	By backToHome = By.xpath("//a[@on-click='[[onHomeClick]] ']");

	// In Process requisition for Purchase Order
	By requisitionBU = By.xpath("//label[text()=' Requisitioning BU']/preceding-sibling::select");
	By buyer = By.xpath("//label[text()=' Buyer']/preceding-sibling::input");
	By searchBtn = By.xpath("//button[text()='Search']");
	By addToDocBuilderBtn = By.xpath("//button[text()='Add to Document Builder']");
	// In Document builder
	By requisitionIdInPopup = By.xpath("//table[@summary='Selected Requisition Lines']/descendant::table[2]");
	By inputSupplier = By.xpath("//label[text()='Supplier']/parent::td/following-sibling::td/input");
	By okBtn_inPopup = By.xpath("//button[@accesskey='K']");
	By createBtn_inProcessRequisition = By.xpath("//button[text()='Create']");
	By confirmPopup_inProcessRequisition = By.cssSelector("td.x1n1");
	By okInConfirm_PO = By.xpath("//td[@class='p_AFResizable x1pn']/button[text()='OK']");
	By savePo = By.xpath("//span[text()='Save']");
	By getreqId_afterCreate = By.xpath("//label[text()='Requisition']/parent::td/following-sibling::td[1]/a");

	By actionsBtn = By.xpath("//span[text()='Actions']");
	By validateBtn = By.xpath("//td[text()='Validate']");
	By submitPoBtn = By.xpath("//div[contains(@class,'callToActionSubmit ')]");

	// tasks
	By tasks = By.xpath("//div[@title='Tasks']");
	By selectShowTask = By.xpath("//label[text()='Show Tasks']/parent::td/following-sibling::td/select");
	By procurementBU = By.xpath("//label[text()=' Procurement BU']/preceding-sibling::select");

	// Receipts
	By insertPo_Id = By.xpath("//input[@aria-label=' Purchase Order']");
	By receiveBtn = By.xpath("//button[text()='Receive']");
	By showReceiptQuantityBtn = By.xpath("//button[text()='Show Receipt Quantity']");
	By createReceiptBtn = By.xpath("//button[text()='Create Receipt']");
	By submitReceipt = By.xpath("//div[@class='callToActionSubmit xeq p_AFTextOnly']");
	By text_submitConfirmation = By.xpath("//div[@class='AFPopupSelector']/descendant::td[@class='x1o']");
	By ok_submitConfirmation = By.xpath("//td[@class='x1pn']/button[@accesskey='K']");
	By doneReceipt = By.xpath("//a[@accesskey='o']");

	public void clickHomeButton() throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(homeBtn).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(homeBtn, 30);
				driver.findElement(homeBtn).click();
			} else {
				logger.error("Home button Not Available ");
				grep.failTest("Home button Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void validateHomePageText() throws Exception {

		try {
			String actualText = driver.findElement(homePageMsg).getText().trim();
			validAssert.equalsAssert(actualText, "Good afternoon, Jack CR");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void selectTabWithNavigator(String tabName) {

		// Small pause for the sliding animation to complete
		try {
			// Locators
			By tabLocator = By.xpath("//div[starts-with(@class,'flat-tabs-item')]/a[text()='" + tabName + "']");
			By rightNavBtn = By.id("clusters-right-nav");

			boolean isTabFound = false;
			int maxClicks = 10; // Prevent infinite loops

			for (int i = 0; i < maxClicks; i++) {
				List<WebElement> tabs = driver.findElements(tabLocator);

				// Check if element exists AND is displayed to the user
				if (!tabs.isEmpty() && tabs.get(0).isDisplayed()) {
					tabs.get(0).click();
					System.out.println("Clicked on tab: " + tabName);
					isTabFound = true;
					break;
				}

				// If not found or not displayed, click the right navigator
				WebElement nextButton = driver.findElement(rightNavBtn);
				if (nextButton.isDisplayed()) {
					nextButton.click();
					System.out.println("Tab not visible, clicked Right Navigator (Attempt " + (i + 1) + ")");

					waitTime(driver);

				} else {
					break; // Navigator button hidden, reached the end
				}
			}

			if (!isTabFound) {
				throw new RuntimeException("Could not find/click the tab: " + tabName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void selectFromQuickActions(String actionVal) throws Exception {
		try {
			By selectQuickAction = By
					.xpath("//div[@class='flat-quickactions-container']/div/a[text()='" + actionVal + "']");
			implWait(driver);
			driver.findElement(selectQuickAction).click();
			waitTime1(driver);
			grep.infoTest("Selecting Quick Action: " + actionVal);
			logger.info("Selecting Quick Action: " + actionVal);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickNavigator() {
		implWait(driver);
		driver.findElement(navigator).click();
	}

	public void selectNavigationTab(String value) {
		By selectInNavigation = By.xpath("//div[@title='" + value + "']");
		scrollView(selectInNavigation);
		driver.findElement(selectInNavigation).click();
	}

	public void selectSubCategoryInNavigator(String subCatg) {
		By selectSubCategory = By.xpath("//a[@title='" + subCatg + "']");
		implWait(driver);
		driver.findElement(selectSubCategory).click();
	}

	public void validatePurchaseRequisitionsPage() throws Exception {

		try {
			implWait(driver);
			String actualText = driver.findElement(selfServiceProcurement).getText().trim();
			waitTime(driver);

			grep.infoTest("Purchase Requisition Header: " + actualText);
			validAssert.equalsAssert(actualText, "Self Service Procurement");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateMyRecentRequisitionsTitle() throws Exception {

		try {
			implWait(driver);
			String actualText = driver.findElement(recentRequisition).getText().trim();
			grep.infoTest("Recent Requsition Title : " + actualText);
			validAssert.equalsAssert(actualText, "My recent requisitions");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickCreateNonCatalogBtn() {
		implWait(driver);
		driver.findElement(createNonCatalog).click();
	}

	public void validateNonCatalogHeaderTitle() throws Exception {

		try {
			implWait(driver);
			String actualText = driver.findElement(nonCatalogHeader).getText().trim();
			grep.infoTest("Create Catalog popup header: " + actualText);
			validAssert.equalsAssert(actualText, "Create Noncatalog Request");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterItemDescription(String descValue) {
		try {
			implWait(driver);
//			waitForElement(itemDescription, 20);

			driver.findElement(itemDescription).click();
			driver.findElement(itemDescription).sendKeys(descValue);
			waitTime(driver);
			grep.infoTest("Entering Item Description: " + descValue);
			logger.info("Entering Item Description: " + descValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterCategoryField(String categoryVal) throws Exception {
		try {
//			By selectCategory = By.xpath("//div[@id='lovDropdown_oj-selectsingle-9_layer']/descendant::li/descendant::span[text()='" + categoryVal + "'][1]");
			implWait(driver);
			WebElement catg = driver.findElement(categoryField);
			waitTime1(driver);
			catg.sendKeys(categoryVal);
			waitTime2(driver);
			actionEntered();
			waitTime(driver);
			grep.infoTest("Selecting Category: " + categoryVal);
			logger.info("Selecting Category: " + categoryVal);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterQuantity(String quantityValue) {
		try {
			implWait(driver);
			WebElement input = driver.findElement(quantity);
			waitTime(driver);

			input.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
//			input.sendKeys(Keys.DELETE);
			waitTime2(driver);
			input.sendKeys(quantityValue);
			waitTime(driver);
			grep.infoTest("Enter Quantity field: " + quantityValue);
			logger.info("Enter Quantity field: " + quantityValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterUnitOfMeasureField(String uomValue) throws Exception {
		try {
			By unitOfMeasureList = By.xpath("//li[@role='row' and normalize-space(.)='Each Each Each']");
//			By unitOfMeasureList = By.xpath("//li[@role='row']/div/div[1]/oj-highlight-text[1]/span");

			implWait(driver);
			driver.findElement(uomField).sendKeys(uomValue);
			waitTime1(driver);
//			driver.findElement(unitOfMeasureList).click();

			List<WebElement> options = driver.findElements(unitOfMeasureList);

			for (WebElement option : options) {
				String optionText = option.getText().trim();
				System.out.println(optionText);
				waitTime(driver);

				if (optionText.equalsIgnoreCase(uomValue) || optionText.contains(uomValue)) {
					option.click();
					break;
				}
			}
			waitTime(driver);
			grep.infoTest("Enter Unit of Measure: " + uomValue);
			logger.info("Enter Unit of Measure: " + uomValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterPrice(String priceValue) {
		try {
			implWait(driver);
			WebElement input = driver.findElement(price);
			waitTime(driver);

			input.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
//			input.sendKeys(Keys.DELETE);
			waitTime2(driver);
			input.sendKeys(priceValue);
			waitTime(driver);
			actionTab();
			waitTime(driver);
			grep.infoTest("Enter Price: " + priceValue);
			logger.info("Enter Price: " + priceValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickAddToCartBtn() {
		implWait(driver);
		driver.findElement(addToCart).click();
	}

	public void validateAddToCartConfiramtionPopup() throws Exception {

		try {
			implWait(driver);

			// 1. Validate the Header Text
			String actualHeader = waitVisible(confirmPopupHeader).getText();
			validAssert.equalsAssert(actualHeader, "Confirmation");

			// 2. Validate the Body Text
			String actualBody = waitVisible(confirmPopupBody).getText();
			validAssert.equalsAssert(actualBody, "Request added to cart");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickNavigateToCart() throws Exception {
		try {
			implWait(driver);
			driver.findElement(viewCart).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validateCartPageDetails(String expectedTitle, String expectedDesc, String expectedUOM,
			String expectedPrice) throws Exception {
		try {

			implWait(driver);
			// 1. Validate Page Title
			String actualTitle = driver.findElement(cartPageTitle).getText().trim();
			System.out.println("INFO: Cart Page Title retrieved: " + actualTitle);
			logger.info("Cart Page Title retrieved: " + actualTitle);
			grep.infoTest("Cart Page Title retrieved: " + actualTitle);
			validAssert.equalsAssert(actualTitle, expectedTitle);
			waitTime(driver);

			// 2. Validate Page Subtitle
			String actualSubtitle = driver.findElement(cartPageSubtitle).getText().trim();
			System.out.println("Cart Page Subtitle retrieved: " + actualSubtitle);
			logger.info("Cart Page Subtitle retrieved: " + actualSubtitle);
			grep.infoTest("Cart Page Subtitle retrieved: " + actualSubtitle);
			validAssert.trueAssert(actualSubtitle.startsWith("Requisition CRRE"));
			waitTime(driver);

			// 3. Validate Item Description
			String actualDesc = driver.findElement(cartPageItemDesc).getText().trim();
			System.out.println("Item Description retrieved: " + actualDesc);
			logger.info("Item Description retrieved: " + actualDesc);
			grep.infoTest("Item Description retrieved: " + actualDesc);
			validAssert.equalsAssert(actualDesc, expectedDesc);
			waitTime(driver);

			// 4. Validate Unit of Measure (UOM)
			String actualUOM = driver.findElement(cartPageUOM).getText().trim();
			System.out.println("UOM retrieved: " + actualUOM);
			logger.info("UOM retrieved: " + actualUOM);
			grep.infoTest("UOM retrieved: " + actualUOM);
			validAssert.equalsAssert(actualUOM, expectedUOM);
			waitTime(driver);

//			waitTime1(driver);
//			// 5. Validate Price (Extracting the numeric value only)
//			WebElement element = driver.findElement(cartPagePrice);
//
//			String rawPriceText = element.getText();
//			System.out.println(rawPriceText);
//
//			String numericPrice = rawPriceText.replace("$", "").split(",")[0].trim();
//
//			// Regex: find the first number before any comma or space
//			System.out.println("Cleaned Price value: " + numericPrice);
//
//			logger.info("Raw Price retrieved: " + numericPrice);
//			grep.infoTest("Raw Price retrieved: " + numericPrice);
//			validAssert.equalsAssert(numericPrice, expectedPrice);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSubTitleDetails() throws Exception {
		String getSubTitle = null;

		try {

			waitTime1(driver);
			getSubTitle = driver.findElement(cartPageSubtitle).getText().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getSubTitle;
	}

	public int validatePriceDetails() throws Exception {
		int numericPrice = 0;

		try {

			waitTime1(driver);
			// 5. Validate Price (Extracting the numeric value only)
			WebElement element = driver.findElement(cartPagePrice);

			String rawPriceText = element.getText();
			System.out.println(rawPriceText);

			numericPrice = Integer.parseInt(rawPriceText.replace("$", "").split(",")[0].trim());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return numericPrice;
	}

	public void validateRequisitionSummary(int unitPrice, int quantity) throws Exception {
		try {

			// 1. Retrieve and Clean Subtotal
			String rawSubtotal = driver.findElement(subTotalAmt).getText();
			int subtotal = Integer.parseInt(rawSubtotal.replace("$", "").split(",")[0].trim());
			System.out.println("Cleaned Subtotal: " + subtotal);

			logger.info("Subtotal :" + subtotal);
			grep.infoTest("Subtotal :" + subtotal);

			// 2. Retrieve and Clean Tax
			String rawTax = driver.findElement(tax).getText();
			int taxVal = Integer.parseInt(rawTax.replace("$", "").split(",")[0].trim());
			System.out.println("Cleaned Tax: " + taxVal);
			logger.info("Tax :" + taxVal);
			grep.infoTest("Tax :" + taxVal);

			// 3. Retrieve and Clean Approval Amount
			String rawApproval = driver.findElement(approvalAmt).getText();
			int approval = Integer.parseInt(rawApproval.replace("$", "").split(",")[0].trim());
			System.out.println("Cleaned Approval Amount: " + approval);
			logger.info("Approval Amount: " + approval);
			grep.infoTest("Approval Amount: " + approval);

			// --- CALCULATIONS & ASSERTIONS ---

			// Validation 1: Price * 3 = Subtotal
			int expectedSubtotal = unitPrice * quantity;
			logger.info("Validating Subtotal: " + expectedSubtotal);
			grep.infoTest("Validating Subtotal: " + expectedSubtotal);

			validAssert.equalsAssert_int(subtotal, expectedSubtotal);
			System.out.println("SUCCESS: Price * 3 matches Subtotal (" + subtotal + ")");

			// Validation 2: Subtotal + Tax = Approval Amount
			int expectedApproval = subtotal + taxVal;
			logger.info("Validating Approval Amount:  " + expectedApproval);
			grep.infoTest("Validating Approval Amount:  " + expectedApproval);
			validAssert.equalsAssert_int(approval, expectedApproval);
			System.out.println("SUCCESS: Subtotal + Tax matches Approval Amount (" + approval + ")");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickSubmitCartBtn() {
		implWait(driver);
		driver.findElement(submitBtn).click();
	}

	public void validateSubmitRequisitionPopup() throws Exception {

		try {
			implWait(driver);

			// 1. Validate the Header Text
			String actualHeader = waitVisible(confirmPopupHeader).getText();
			validAssert.equalsAssert(actualHeader, "Confirmation");

			// 2. Validate the Body Text
			String actualBody = waitVisible(confirmPopupBody).getText();
			validAssert.equalsAssert(actualBody, "Requisition submitted");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getSubmitRequisitionMessage() throws Exception {
		String getMsg = null;

		try {

			waitTime1(driver);
			getMsg = driver.findElement(submitMsgDetails).getText().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getMsg;
	}

	public void validateSubmittedRequisitionState(String expectedState) throws Exception {

		try {
			implWait(driver);
			List<WebElement> state = driver.findElements(requsitionStage);
			if (state.size() > 0) {
				String actualState = state.getFirst().getText().trim();
				validAssert.equalsAssert(actualState, expectedState);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyRequisitionApproveState(int maxRetries) throws Exception {

		try {
			implWait(driver);

			boolean success = false;
			for (int i = 0; i < maxRetries; i++) {
				refreshPage();

				List<WebElement> state = driver.findElements(requsitionStage);
				if (!state.isEmpty()) {
					String currentStatus = state.getFirst().getText().trim();

					if (currentStatus.equalsIgnoreCase("Approved")) {
						success = true;
						break;
					}
				} else {
					System.out.println("Attempt " + (i + 1) + ": Status element not found on page.");
				}
				waitTime10(driver);
			}
			if (!success) {
				throw new RuntimeException(
						"Timeout: Status did not reach 'Approved ' after " + maxRetries + " retries.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void NavigateBackToHome() throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(backToHome).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(backToHome, 30);
				driver.findElement(backToHome).click();
			} else {
				logger.error("Home button Not Available ");
				grep.failTest("Home button Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	// Process Requisition
	public void selectRequisitionBU(String option) throws Exception {
		try {
			waitForElementToBeClickable(requisitionBU, 10);
			WebElement selectBu = driver.findElement(requisitionBU);
			Select sel = new Select(selectBu);
			sel.selectByVisibleText(option);
			grep.infoTest("Select " + option + " from the drop down under Requisitioning BU");
			logger.info("Select " + option + " from the drop down under Requisitioning BU");

		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}

	}

	public void clearBuyer() throws Exception {
		try {
			waitForElementToBeClickable(buyer, 10);
			WebElement buy = driver.findElement(buyer);
			buy.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
			waitTime(driver);

		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}

	}

	public void clickSearchBtn() {
		implWait(driver);
		driver.findElement(searchBtn).click();
	}

	public void selectRequisitionFromList(String reqId) throws Exception {
		try {
			By selectReq = By.xpath("//a[text()='" + reqId + "']/ancestor::span/parent::td/following-sibling::td[1]");
			implWait(driver);
			driver.findElement(selectReq).click();

		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}

	}

	public void clickAddToDocumentBuilderBtn() {
		implWait(driver);
		driver.findElement(addToDocBuilderBtn).click();
	}

	public void getRequisitionIdFromPopup(String reqid) throws Exception {
		try {
			String actualText = driver.findElement(requisitionIdInPopup).getText().trim();
			validAssert.equalsAssert(actualText, reqid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAndSelectSupplier(String supplierValue) {
		try {
			implWait(driver);
			By selectSupplier = By.xpath("//li[@data-afr-value='" + supplierValue + "']");

			driver.findElement(inputSupplier).click();
			driver.findElement(inputSupplier).sendKeys(supplierValue);
			waitTime(driver);
			grep.infoTest("Entering Supplier: " + supplierValue);
			logger.info("Entering Supplier: " + supplierValue);
			waitTime2(driver);
			driver.findElement(selectSupplier).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOkBtn_inDocBuilder_Popup() {
		implWait(driver);
		driver.findElement(okBtn_inPopup).click();
	}

	public void clickCreateBtn_inProcessRequsitionPage() {
		implWait(driver);

		scrollView(createBtn_inProcessRequisition);
		driver.findElement(createBtn_inProcessRequisition).click();
	}

	public String validatePurchaseOrderCreationConfirmation() throws Exception {

		String getMsg = null;
		try {
			implWait(driver);

			getMsg = driver.findElement(confirmPopup_inProcessRequisition).getText().trim();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getMsg;

	}

	public void clickOk_inConfirmPO_popup() {
		implWait(driver);

		driver.findElement(okInConfirm_PO).click();
	}

	public void validateRequisitionIdAfterCreatingPo(String reqId) throws Exception {

		try {
			implWait(driver);

			String getId = driver.findElement(getreqId_afterCreate).getText().trim();
			waitTime(driver);
			grep.infoTest("Get Requisition Id after creating Purchase Order: " + getId);
			logger.info("Get Requisition Id after creating Purchase Order: " + getId);
			validAssert.equalsAssert(getId, reqId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickSavePoBtn() {
		implWait(driver);

		driver.findElement(savePo).click();
	}

	public void clickActionAndValidateBtn() throws Exception {
		try {
			implWait(driver);

			driver.findElement(actionsBtn).click();
			waitTime1(driver);
			driver.findElement(validateBtn).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void submitPo_ForApprovalBtn() throws Exception {
		try {
			implWait(driver);

			driver.findElement(submitPoBtn).click();
			waitTime(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getOrderId(String rawText) throws Exception {
		String poId = null;
		try {
			String regex = "CRPO[A-Z0-9-]*\\d+";

			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(rawText);

			if (matcher.find()) {
				poId = matcher.group();
				System.out.println("Extracted PO ID: " + poId);

			} else {
				System.out.println("Could not find the Purchase Order ID in the text: " + rawText);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return poId;
	}

	public void click_Tasks_InPO() throws Exception {
		try {
			implWait(driver);

			driver.findElement(tasks).click();
			waitTime(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectTasks_InTaskPage(String task) throws Exception {
		try {
			By selectTask = By.xpath("//a[text()='" + task + "']");
			implWait(driver);

			driver.findElement(selectTask).click();
			waitTime(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void selectShowTasksDropdown(String taskOption) throws Exception {
		try {

			implWait(driver);
			waitForElementToBeClickable(selectShowTask, 10);
			WebElement selectTask = driver.findElement(selectShowTask);
			Select sel = new Select(selectTask);
			sel.selectByVisibleText(taskOption);
			grep.infoTest("Select " + taskOption + " from the drop down under Procurement BU");
			logger.info("Select " + taskOption + " from the drop down under Procurement BU");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void selectProcurementBU(String option) throws Exception {
		try {
			waitForElementToBeClickable(procurementBU, 10);
			WebElement selectBu = driver.findElement(procurementBU);
			Select sel = new Select(selectBu);
			sel.selectByVisibleText(option);
			grep.infoTest("Select " + option + " from the drop down under Procurement BU");
			logger.info("Select " + option + " from the drop down under Procurement BU");

		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void verifyPurchaseOrderState(String poId, int maxRetries) throws Exception {

		try {
			implWait(driver);
//			By approvedPOStatus = By.xpath("//a[text()='CRPO500018-2025']/ancestor::table[2]/tbody/tr/td[7]");
			By approvedPOStatus = By.xpath("//a[text()='" + poId + "']/ancestor::table[2]/tbody/tr/td[7]");

			boolean success = false;
			for (int i = 0; i < maxRetries; i++) {
//				refreshPage();
				clickSearchBtn();

				List<WebElement> state = driver.findElements(approvedPOStatus);
				if (!state.isEmpty()) {
					String currentStatus = state.getFirst().getText().trim();

					if (currentStatus.equalsIgnoreCase("Open")) {
						success = true;
						System.out.println("Current status for " + poId + " is " + currentStatus);
						break;

					} else {
						System.out.println(
								"Attempt " + (i + 1) + ": Status not valid for " + poId + " clicking on refresh.");
					}
				}
				waitTime60(driver);
			}
			if (!success) {
				throw new RuntimeException("Timeout: Status did not reach 'Open ' after " + maxRetries + " retries.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// receipt
	public void enterPurchaseOrderId(String orderIdVal) {
		try {
			implWait(driver);

			driver.findElement(insertPo_Id).click();
			driver.findElement(insertPo_Id).sendKeys(orderIdVal);
			waitTime(driver);
			grep.infoTest("Entering Purchase order id: " + orderIdVal);
			logger.info("Entering Purchase order id: " + orderIdVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickReceiveBtn() {
		implWait(driver);
		driver.findElement(receiveBtn).click();
	}

	public void clickShowReceiptBtn() {
		implWait(driver);
		driver.findElement(showReceiptQuantityBtn).click();
	}

	public void clickCreateReceiptBtn() {
		implWait(driver);
		driver.findElement(createReceiptBtn).click();
	}

	public void clickSubmitReceiptBtn() {
		implWait(driver);
		driver.findElement(submitReceipt).click();
	}

	public String validateReceiptNum_inSubmitConfirmationPopup() throws Exception {

		String getMsg = null;
		try {
			implWait(driver);

			getMsg = driver.findElement(text_submitConfirmation).getText().trim();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getMsg;

	}

	public void clickOk_inSubmitConfirmationPopup() {
		implWait(driver);
		driver.findElement(ok_submitConfirmation).click();
	}

	public void clickDoneReceiptBtn() {
		implWait(driver);
		driver.findElement(doneReceipt).click();
	}

}
