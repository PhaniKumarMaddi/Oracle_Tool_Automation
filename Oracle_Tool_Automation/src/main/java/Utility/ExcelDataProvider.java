package Utility;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;

public class ExcelDataProvider {
	//
	static WebDriver driver;
	public static ExtentReports extent;
	
	
	public static Object[][] testData(String excelFile, String sheetName) {
	    ExcelUtility excel = new ExcelUtility(excelFile, sheetName);

	    int rowCount = excel.getRowCount();
	    int colCount = excel.getColCount();

	    Object data[][] = new Object[rowCount-1][colCount];

	    for (int i = 1; i < rowCount; i++) {
	        for (int j = 0; j < colCount; j++) {
	            String cellData = excel.getCellDataString(i, j);
	            System.out.println("Cell [" + i + ", " + j + "]: " + cellData);
	            data[i-1][j] = cellData;
	        }
	    }
	    return data;
	}

}
