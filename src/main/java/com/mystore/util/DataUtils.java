package com.mystore.util;

import com.mystore.datamanager.ScenarioData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataUtils {

    private static final String EXCEL_FILE_PATH = "testdata/TestData.xlsx";
    private DataUtils () {
    }

    public static void readTestDataFromExcelSheet (String testCaseId, String sheetName) {
        try (InputStream inputStream = ResourceLoader.getResourceAsStream(EXCEL_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet " + sheetName + " not found in Excel file.");
            }
            // Step 1: Read the header row and map headers to column indices
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Sheet " + sheetName + " has no header row.");
            }
            Map<String, Integer> headerMap = new HashMap<>();
            for (Cell cell : headerRow) {
                headerMap.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
            }
            // Step 2: Find the row matching the given testCaseId
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip header row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell testCaseIdCell = row.getCell(headerMap.get("TC_ID"));
                if (testCaseIdCell != null && testCaseIdCell.getStringCellValue().equalsIgnoreCase(testCaseId)) {
                    // Step 3: Populate ScenarioData dynamically based on column names
                    for (Map.Entry<String, Integer> entry : headerMap.entrySet()) {
                        String columnName = entry.getKey();
                        int columnIndex = entry.getValue();
                        Cell cell = row.getCell(columnIndex);
                        if (cell != null) {
                            String cellValue = cell.getStringCellValue();
                            ScenarioData.setData(columnName, cellValue);
                        }
                    }
                    break; // Exit loop once the desired row is found
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading data from Excel file: " + e.getMessage(), e);
        }
    }
}
