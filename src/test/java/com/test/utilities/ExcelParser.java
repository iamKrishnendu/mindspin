package com.test.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.test.common.PropertyFileHandler;


public class ExcelParser {
	
	static Logger log = Logger.getLogger(ExcelParser.class);
	private File file=null;
	private FileInputStream fin = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private Row row = null;
	private PropertyFileHandler prop = new PropertyFileHandler();
	private Map<String,List<Object>>dataMap = new HashMap<String,List<Object>>();
	private String dataSheetPath = System.getProperty("user.dir")+prop.getPropertyValues().getProperty("DataMappingSheet");
	
	public Map<String,List<Object>> getDataFromDataSheet(String sheetName) {
		
		
		try {
			file = new File(dataSheetPath);
			fin = new FileInputStream(file);
			workbook = new XSSFWorkbook(fin);
			sheet = workbook.getSheet(sheetName);
			
			for(int rowIndex=1;rowIndex<=sheet.getLastRowNum();rowIndex++) {
				
				row = sheet.getRow(rowIndex);
				List<Object>storeCellValues = new ArrayList<Object>();
				
				for(int colIndex=1;colIndex<row.getLastCellNum();colIndex++) {
					
					Cell key = row.getCell(0);
					Cell cellValue = row.getCell(colIndex);
					
					storeCellValues.add(cellValue);
					dataMap.put(key.toString() , storeCellValues);
					
				}
				
		}
			
			return dataMap;
			
		}catch(Exception e) {
			
		}
		return dataMap;
	}
	
	public String testData(String sheetName, String testDataSet, int cellIndex) {
		
		return getDataFromDataSheet(sheetName).get(testDataSet).get(cellIndex).toString();
		
	}
	
	public static void main(String[]arg) {
		ExcelParser p = new ExcelParser();
		
		System.out.println(p.testData("TestData", "TC_003", 2));
	}

}
