package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utility.DriverManager;
import Utility.GenerateReports;
import Utility.ValidatingAssertions;
import Utility.WaitsManager;

public class Oracle_LoginPage extends WaitsManager {
	protected WebDriver driver;
	private static Logger logger = LogManager.getLogger(Oracle_LoginPage.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();

	public Oracle_LoginPage() {
		this.driver = DriverManager.getDriver();
	}

	By userName = By.xpath("//input[@id='idcs-signin-basic-signin-form-username']");
	By password = By.xpath("//input[@id='idcs-signin-basic-signin-form-password|input']");
	By signInBtn = By.xpath("//button[@class='oj-button-button']/descendant::span[text()='Sign In']");
	By homePageText = By.xpath("//a[@id='pt1:commandLink1']");
	By welcomeText = By.xpath("//td[@id='pt1:atkph1::_afrTtxt']");

	public void enterUserName(String usernameValue) throws Exception {
		try {
//			implWait(driver);
			waitForElement(userName, 60);
			boolean elementExists = !driver.findElements(userName).isEmpty();
			if (elementExists) {
				driver.findElement(userName).sendKeys(usernameValue);
			} else {
				logger.error("Username Not Available ");
				grep.failTest("Username Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void enterPassword(String passwordValue) throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(password).isEmpty();
			if (elementExists) {
				driver.findElement(password).sendKeys(passwordValue);
			} else {
				logger.error("Password Not Available ");
				grep.failTest("Password Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void clickSignIn() throws Exception {
		try {
			implWait(driver);
			boolean elementExists = !driver.findElements(signInBtn).isEmpty();
			if (elementExists) {
				waitForElementToBeClickable(signInBtn, 30);
				driver.findElement(signInBtn).click();
			} else {
				logger.error("Sign in button Not Available ");
				grep.failTest("Sign in button Not Available ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			grep.failTest("Test Failed :" + e.getMessage());
			logger.error("Test Failed :" + e.getMessage());
		}
	}

	public void validateHomePageText() throws Exception {
		String actualText = driver.findElement(homePageText).getText().trim();
		validAssert.equalsAssert(actualText, "You have a new home page!");
	}

	public void validateWelcomeText() throws Exception {

		try {
			String actualText = driver.findElement(welcomeText).getText().trim();
			validAssert.trueAssert(actualText.startsWith("Welcome"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
