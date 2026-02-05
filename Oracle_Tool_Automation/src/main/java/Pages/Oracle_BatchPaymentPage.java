package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utility.DriverManager;
import Utility.GenerateReports;
import Utility.ValidatingAssertions;
import Utility.WaitsManager;

public class Oracle_BatchPaymentPage extends WaitsManager {
	protected WebDriver driver;
	private static Logger logger = LogManager.getLogger(Oracle_BatchPaymentPage.class);
	GenerateReports grep = new GenerateReports();
	ValidatingAssertions validAssert = new ValidatingAssertions();

	public Oracle_BatchPaymentPage() {
		this.driver = DriverManager.getDriver();
	}

	By submitPaymentProcessTitle = By.xpath("//div[@title='Submit Payment Process Request']");
	By inputName= By.xpath("//input[contains(@name,'ap1:inputText1')]");
	By templateName= By.xpath("//input[contains(@name,'ap1:templateNameId')]");
	
	

	
	public void validateSubmitPaymentProcessPageTitle() throws Exception {

		try {
			implWait(driver);
			String actualText = driver.findElement(submitPaymentProcessTitle).getText().trim();
			waitTime(driver);

			grep.infoTest("Submit Payment Process Request Page Header: " + actualText);
			logger.info("Submit Payment Process Request Page Header: " + actualText);
			
			validAssert.equalsAssert(actualText, "Submit Payment Process Request");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
