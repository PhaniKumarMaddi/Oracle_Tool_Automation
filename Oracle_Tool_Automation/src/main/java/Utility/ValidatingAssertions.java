package Utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

public class ValidatingAssertions extends WaitsManager {

	private static final Logger logger = LogManager.getLogger(ValidatingAssertions.class);

	GenerateReports grep = new GenerateReports();
	TestDataKeys testData = new TestDataKeys();
	SoftAssert softAsserts = new SoftAssert();

	// Validate equal assert
	public void equalsAssert(String actualResult, String expectedResult) throws Exception {
		// Validating
		softAsserts.assertEquals(actualResult, expectedResult);
		System.out.println("Expected :" + expectedResult + " and Actual :" + actualResult);
		logger.info("Expected :" + expectedResult + " and Actual :" + actualResult);
		assertPassOrFail(actualResult, expectedResult);

	}

	public void equalsAssert_int(int actualResult, int expectedResult) throws Exception {
		// Validating
		softAsserts.assertEquals(actualResult, expectedResult);
		System.out.println("Expected :" + expectedResult + " and Actual :" + actualResult);
		logger.info("Expected :" + expectedResult + " and Actual :" + actualResult);
		assertPassOrFail_int(actualResult, expectedResult);

	}

	// Validate Not equal assert
	public void notEqualsAssert(String actualResult, String expectedResult) throws Exception {
		// Validating
		softAsserts.assertNotEquals(actualResult, expectedResult);
//		System.out.println("Expected :" + expectedResult + " and Actual :" + actualResult);
//		logger.info("Expected :" + expectedResult + " and Actual :" + actualResult);
		assertNotEqualPassOrFail(actualResult, expectedResult);

	}

	// Validate assertTrue
	public void trueAssert(boolean condition) throws Exception {
		// Validating
		softAsserts.assertTrue(condition);
		System.out.println(condition);
	}

	// Validate assertFalse
	public void falseAssert(boolean condition) throws Exception {
		// Validating
		softAsserts.assertFalse(condition);
		System.out.println(condition);

	}

	// Validate assertFalse
	public void falseAssertion(boolean condition, String message) throws Exception {
		// Validating
		softAsserts.assertFalse(condition, message);
		System.out.println(condition);
		System.out.println(message);

	}

	// PASS OR FAIL
	public void assertPassOrFail(String actualResult, String expectedResult) {
		if (actualResult.equals(expectedResult)) {
			grep.passTest("Expected :" + expectedResult + " and Actual :" + actualResult + " both are same");
			logger.info("Expected :" + expectedResult + " and Actual :" + actualResult + " both are same");
		} else {
			grep.failTest("Expected and Actual are not same Expected [" + expectedResult + " ], but found ["
					+ actualResult + "]");
			logger.error("Expected and Actual are not same Expected [" + expectedResult + " ], but found ["
					+ actualResult + "]");
		}
	}

	public void assertPassOrFail_int(int actualResult, int expectedResult) {
		if (actualResult == expectedResult) {
			grep.passTest("Expected :" + expectedResult + " and Actual :" + actualResult + " both are same");
			logger.info("Expected :" + expectedResult + " and Actual :" + actualResult + " both are same");
		} else {
			grep.failTest("Expected and Actual are not same Expected [" + expectedResult + "], but found ["
					+ actualResult + "]");
			logger.error("Expected and Actual are not same Expected [" + expectedResult + "], but found ["
					+ actualResult + "]");
		}
	}

	public void assertNotEqualPassOrFail(String actualResult, String expectedResult) {
		if (!actualResult.equals(expectedResult)) {
			grep.passTest("Expected :" + expectedResult + " and Actual :" + actualResult + " both are same");
			logger.info("Expected :" + expectedResult + " and Actual :" + actualResult + " both are same");
		} else {
			grep.failTest("Expected and Actual are not same Expected [" + expectedResult + "], but found ["
					+ actualResult + "]");
			logger.error("Expected and Actual are not same Expected [" + expectedResult + "], but found ["
					+ actualResult + "]");
		}
	}

	public void assertAllFunction() {
		softAsserts.assertAll();
	}

}