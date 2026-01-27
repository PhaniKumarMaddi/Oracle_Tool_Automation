package Utility;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitsManager {

	By dtElement;
	LocalTime currentTime;
	public static String dateFormat = "MM-dd-yyyy";
	public static String timeFormat = "HH:mm:ss";

	protected static WebDriver driver;

	/**
	 * Retrieve the WebDriver used from the DriverManager.
	 */
	public WaitsManager() {
		WaitsManager.driver = DriverManager.getDriver();
	}

	/**
	 * This method will switch the focus from the current window to the new window.
	 */
	public void switchToNewWindow() {
		// Retrieve the instances of windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Switch the focus from the current window to the new window
		String currentWindowHandle = driver.getWindowHandle();
		windowHandles.remove(currentWindowHandle);
		String newWindowHandle = windowHandles.iterator().next();
		driver.switchTo().window(newWindowHandle);
	}

	/**
	 * This method will switch the focus to the main window.
	 * 
	 * @param mainWindowHandle - Main Window
	 */
	public void switchToMainWindow(String mainWindowHandle) {
		// Switch the focus to the main window
		driver.switchTo().window(mainWindowHandle);
	}

	public void isWindowPresent(By locator, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForElement(By locator, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForElementToBeClickable(By locator, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForElementToBePopulated(By locator, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		WebElement webElement = driver.findElement(locator);
		wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(webElement, "value", "")));
	}

	public WebElement waitVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForWindow(int numberOfWindows, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfWindows));
	}

	public static boolean isAlertPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public void acceptalert() {
		try {
			driver.switchTo().alert().accept();

		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}

	public void populateDateField(LocalDate date, By dateElement) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		String formattedDate = date.format(formatter);
		dtElement = dateElement;
		waitForElement(dtElement, 10);
		driver.findElement(dtElement).sendKeys(formattedDate);
	}

	public String getFormattedTime(LocalTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
		String formattedTime = time.format(formatter);

		return formattedTime;
	}

	public void waitTime(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
	}

	public void waitTime1(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
	}

	public void waitTime2(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
	}

	public void waitTime3(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
	}

	public void waitTime5(WebDriver driver) throws InterruptedException {
		Thread.sleep(5000);
	}

	public void waitTime15(WebDriver driver) throws InterruptedException {
		Thread.sleep(15000);
	}

	public void waitTime10(WebDriver driver) throws InterruptedException {
		Thread.sleep(10000);
	}

	public void waitTime30(WebDriver driver) throws InterruptedException {
		Thread.sleep(30000);
	}

	public void waitTime60(WebDriver driver) throws InterruptedException {
		Thread.sleep(30000);
	}

	public void implWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void closeCurrentTab() {
		driver.close();
	}

	public void switchToLastTab() {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.getLast());
	}

	public void switchToFirstTab() {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.getFirst());
		driver.switchTo().defaultContent();
	}

	public void clickNewTab() {
//		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
//		driver.switchTo().window(tabs.get(1)); 
		((JavascriptExecutor) driver).executeScript("window.open()");
	}

	public void enterURL(String url) {
		driver.get(url);
	}

	public String getTitleMethod() {
		String title = driver.getTitle();
		return title;
	}

	public String getURL() {
		String urlVal = driver.getCurrentUrl();
		return urlVal;
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void refreshPageUrl() {
		String currentURL = driver.getCurrentUrl();
		driver.get(currentURL);
	}

	public void actionEntered() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ARROW_RIGHT).build().perform();
		act.sendKeys(Keys.ENTER).build().perform();
	}
	
	public void actionTab() {
		Actions act = new Actions(driver);
//		act.sendKeys(Keys.ARROW_RIGHT).build().perform();
		act.sendKeys(Keys.TAB).build().perform();
	}

//	public void actionsRelated() {
//		Actions act = new Actions(driver);
//		WebElement element = driver.findElement(By.id("Test"));
//		// move to element and click 
//		act.moveToElement(element).click().perform();
//		// move to element and hover 
//		act.moveToElement(element).build().perform();
//		// double click 
//		act.doubleClick(element).build().perform();
//		// context click
//		act.contextClick(element).build().perform();
//		//  drag and drop 
//		act.dragAndDrop(element, element).build().perform();
//		// Copy and paste 
//		// control a
//		act.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
//		// control c
//		act.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
//		// control v
//		act.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
//	}

	public void switchToMainFrame() {
		// Switch the focus to the main frame
		driver.switchTo().defaultContent();
	}

	public void switchToFrame(String frameName) {
		switchToMainFrame();
		// Switch the focus to the UI Map Pop-up
		driver.switchTo().frame(frameName);
	}

	public void scrollView(By locator) {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
