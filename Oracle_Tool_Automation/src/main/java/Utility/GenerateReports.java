package Utility;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenerateReports {

	public static WebDriver driver;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public GenerateReports() {
		GenerateReports.driver = DriverManager.getDriver();
	}

	public void setupExtentReport(String fileName) {
		String path = System.getProperty("user.dir") + System.getProperty("file.separator") + fileName;
		// helper class that help to configure the report
		sparkReporter = new ExtentSparkReporter(path);

		// Configure the report
		sparkReporter.config().setDocumentTitle(fileName);
		sparkReporter.config().setReportName("Web Automation Results");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setEncoding("utf-8");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("User", System.getProperty("user.name"));
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
	}

	public String takeScreenShot(WebDriver driver, String FileName) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = ".//Screenshots//" + FileName + ".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;
	}

	public void testCreate(String testCaseName, String testDescription) {

		test = extent.createTest(testCaseName, testDescription);
	}

	public void infoTest(String infoDescription) {
		test.info(infoDescription);
	}

	public void passTest(String passDescription) {
		test.pass(passDescription);
	}

	public void failTest(String failDescription) {
		test.fail(failDescription);
	}

	public void warnTest(String warnDescription) {
		test.warning(warnDescription);
	}

//	public void captureScreenshot(String status, String info, String screenshotName) throws Exception {
//
//		String screenshotPath = takeScreenShot(driver, screenshotName);
//		switch (status.toLowerCase()) {
//		case "pass":
//			test.pass(info + " Passed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//			break;
//		case "fail":
//			test.fail(info + " Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//			break;
//		case "info":
//			test.info(info + " Info", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//			break;
//		default:
//			throw new IllegalArgumentException("Invalid status: " + status);
//		}
//	}

	public String getBase64Screenshot(WebDriver driver) {
		// Capture screenshot as Base64 string
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	public void captureScreenshot(String status, String info, String screenshotName) throws Exception {

		String base64Code = getBase64Screenshot(driver);

		switch (status.toLowerCase()) {
		case "pass":
			test.pass(info + " Passed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build());
			break;
		case "fail":
			test.fail(info + " Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build());
			break;
		case "info":
			test.info(info + " Info", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build());
			break;
		default:
			throw new IllegalArgumentException("Invalid status: " + status);
		}
	}

	public void flushReport() {
		extent.flush();
	}

}
