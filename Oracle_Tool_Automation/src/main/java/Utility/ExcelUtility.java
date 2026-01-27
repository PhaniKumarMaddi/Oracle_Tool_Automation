package Utility;

import java.io.FileInputStream;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	static String projectPath;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	public ExcelUtility(String excelFileName, String sheetName) {
		try {
			String excelPath = System.getProperty("user.dir") + "\\data\\" + excelFileName + ".xlsx";
			FileInputStream excelFile = new FileInputStream(excelPath);
			workbook = new XSSFWorkbook(excelFile);
			sheet = workbook.getSheet(sheetName);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getRowCount() {
		int rowCount = 0;
		try {
			rowCount = sheet.getPhysicalNumberOfRows();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public int getColCount() {
		int colCount = 0;
		try {
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return colCount;
	}
	
	public String getCellDataString(int rowNum, int colNum) {
		String cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		return cellData;
	}
	
	public double getCellDataNumber(int rowNum, int colNum) {
		double cellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
		return cellData;
	}
}
