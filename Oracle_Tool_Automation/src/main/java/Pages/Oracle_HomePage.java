package Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	By meLink = By.xpath("//a[@id='groupNode_my_information']");
	By navigator = By.xpath("//a[@id='pt1:_UISmmLink']");
	By procurementNavigation = By.xpath("//div[@title='Procurement']");
	By purchaseRequisitions = By.xpath("//a[@title='Purchase Requisitions (New)']");
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
	By cartPageItemDesc = By.cssSelector("div.oj-typography-body-md.oj-line-clamp-3 ");
	By cartPageUOM = By.cssSelector("div.oj-flex.oj-sm-align-items-baseline>div");
	By cartPagePrice = By.cssSelector("div.oj-flex.oj-sm-align-self-flex-end");

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

	public void clickMeLink() {
		implWait(driver);
		driver.findElement(meLink).click();
	}

	public void clickNavigator() {
		implWait(driver);
		driver.findElement(navigator).click();
	}

	public void clickProcurementNavigation() {
		scrollView(procurementNavigation);
		driver.findElement(procurementNavigation).click();
	}

	public void clickPurchaseRequisitions() {
		implWait(driver);
		driver.findElement(purchaseRequisitions).click();
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

	public void validateCartPageDetails(String expectedTitle, String expectedSubtitle, String expectedDesc,
			String expectedUOM, String expectedPrice) throws Exception {
		try {

			// 1. Validate Page Title
			String actualTitle = driver.findElement(cartPageTitle).getText().trim();
			System.out.println("INFO: Cart Page Title retrieved: " + actualTitle);
			logger.info("Cart Page Title retrieved: " + actualTitle);
			grep.infoTest("Cart Page Title retrieved: " + actualTitle);
			validAssert.equalsAssert(actualTitle, expectedTitle);

			// 2. Validate Page Subtitle
			String actualSubtitle = driver.findElement(cartPageSubtitle).getText().trim();
			System.out.println("Cart Page Subtitle retrieved: " + actualSubtitle);
			logger.info("Cart Page Subtitle retrieved: " + actualSubtitle);
			grep.infoTest("Cart Page Subtitle retrieved: " + actualSubtitle);
			validAssert.equalsAssert(actualSubtitle, expectedSubtitle);

			// 3. Validate Item Description
			String actualDesc = driver.findElement(cartPageItemDesc).getText().trim();
			System.out.println("Item Description retrieved: " + actualDesc);
			logger.info("Item Description retrieved: " + actualDesc);
			grep.infoTest("Item Description retrieved: " + actualDesc);
			validAssert.equalsAssert(actualDesc, expectedDesc);

			// 4. Validate Unit of Measure (UOM)
			String actualUOM = driver.findElement(cartPageUOM).getText().trim();
			System.out.println("UOM retrieved: " + actualUOM);
			logger.info("UOM retrieved: " + actualUOM);
			grep.infoTest("UOM retrieved: " + actualUOM);
			validAssert.equalsAssert(actualUOM, expectedUOM);

			// 5. Validate Price (Extracting the numeric value only)
			WebElement element = driver.findElement(cartPagePrice);
			String rawPriceText = (String) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].childNodes[0].textContent;", element);

//			String numericPrice = rawPriceText.replaceAll("[^0-9].* ", "").trim();
			String numericPrice = rawPriceText.replace("$", "").split(",")[0].trim();
			

			// Regex: find the first number before any comma or space

//			System.out.println("Raw Price retrieved: " + rawPriceText.replace("\n", " "));
			System.out.println("Cleaned Price value: " + numericPrice);

			logger.info("Raw Price retrieved: " + numericPrice);
			grep.infoTest("Raw Price retrieved: " + numericPrice);
			validAssert.equalsAssert(numericPrice, expectedPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
