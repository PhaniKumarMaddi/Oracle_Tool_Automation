package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Utility.DriverManager;
import Utility.GenerateReports;
import Utility.PropertiesFile;
import Utility.WaitsManager;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class TestInitializer extends WaitsManager {

	private static final Logger logger = LogManager.getLogger(TestInitializer.class);
	private PropertiesFile configFile;
	public GenerateReports grep;
	protected WebDriver driver;

	@BeforeTest(description = "Setup and Login To Browser")
	@Parameters({ "FileName" })
	public void setup(@Optional("OraclePage.html") String nameForReport) throws Exception {

		grep = new GenerateReports(); // report class

		configFile = new PropertiesFile(System.getProperty("user.dir") + "//config//config.properties");
		String browserType = configFile.getProperty("Browser");

		if (browserType.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");

			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.password_manager_leak_detection", false);

			options.setExperimentalOption("prefs", prefs);
			WebDriverManager.chromedriver().clearDriverCache().setup();
			driver = new ChromeDriver(options);

			System.out.println("Launching Chrome Browser");
			logger.info("Launching Chrome Browser");

		}
		if (browserType.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Launching Firefox Browser");
			logger.info("Launching Firefox Browser");
		}
		if (browserType.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Launching Edge Browser");
			logger.info("Launching Edge Browser");
		}
		DriverManager.setDriver(driver);
		driver.manage().window().maximize();

		String url = configFile.getProperty("oracle_url");

		grep.setupExtentReport(nameForReport);
		grep.testCreate("Login Page", "Login test");

		Oracle_LoginPage login = new Oracle_LoginPage();

		grep.infoTest("Report Name :" + nameForReport);
		logger.info("Report Name :" + nameForReport);

		driver.get(url);

		System.out.println("Web Page URL: " + url);
		grep.infoTest("Web Page URL: " + url);
		logger.info("Web Page URL: " + url);

		grep.captureScreenshot("pass", "Inside Login Page ", "Oracle_Loginpage");
		waitTime(driver);
		login.enterUserName("Test01");
		login.enterPassword("Welcome123");
		login.clickSignIn();
		waitTime2(driver);

		for (int i = 0; i <= 3; i++) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_MINUS);
			robot.keyRelease(KeyEvent.VK_MINUS);
			robot.keyRelease(KeyEvent.VK_CONTROL);

		}

		grep.infoTest("Logged in to Oracle Application");
		logger.info("Logged in to Oracle Application");

		login.validateHomePageText();
		login.validateWelcomeText();
		grep.captureScreenshot("pass", "Inside Landing Page ", "Oracle_LandingPage");
	}

	@AfterTest(description = "Quit Browser")
	public void tearDown() {
		grep = new GenerateReports(); // report class
		grep.flushReport(); // flush report

//		driver.quit();
		System.out.println("Testing Ended");
		System.out.println("******");

		grep.infoTest("Testing Ended");
		grep.infoTest("***********");

		logger.info("Testing Ended");
		logger.info("***********");

	}
}
