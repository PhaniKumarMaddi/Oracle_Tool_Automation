package Utility;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<WebDriver>();

	/**
	 * This method will return the WebDriver to be used in accessing the elements of
	 * Pages in the Web Browser.
	 * 
	 * @return driver - WebDriver to be used
	 */

	public static WebDriver getDriver() {
		return DRIVER_THREAD_LOCAL.get();

	}

	/**
	 * This method will use the determined WebDriver from TestInitializer.java
	 * program.
	 * 
	 * @param driver - WebDriver from TestInitializer.java program
	 */

	public static void setDriver(WebDriver driver) {
		DRIVER_THREAD_LOCAL.set(driver);
	}

}
