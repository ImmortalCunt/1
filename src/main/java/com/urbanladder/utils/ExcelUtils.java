package com.urbanladder.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    private static final Logger logger = LogManager.getLogger(ExcelUtils.class);

    public static void writeComparisonToExcel(String filePath, List<Map<String, String>> products) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Product Comparison");
            
            // Create header row
            Row headerRow = sheet.createRow(0);
            if (!products.isEmpty()) {
                int cellNum = 0;
                for (String key : products.get(0).keySet()) {
                    Cell cell = headerRow.createCell(cellNum++);
                    cell.setCellValue(key);
                }
            }

            // Create data rows
            int rowNum = 1;
            for (Map<String, String> product : products) {
                Row row = sheet.createRow(rowNum++);
                int cellNum = 0;
                for (String value : product.values()) {
                    Cell cell = row.createCell(cellNum++);
                    cell.setCellValue(value);
                }
            }

            // Auto-size columns
            for (int i = 0; i < products.get(0).size(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
                logger.info("Successfully wrote comparison data to Excel file: " + filePath);
            }
        } catch (IOException e) {
            logger.error("Error writing to Excel file: " + e.getMessage());
            throw new RuntimeException("Failed to write to Excel file", e);
        }
    }
} 