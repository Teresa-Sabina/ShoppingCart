/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.maven.shop.utils;

/**
 *
 * @author lourdestssantiago
 */

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
import java.util.Locale;

public class ExcelUtils {

    private HashMap<String, HashMap<String, String>> sheetData = new HashMap<>();

    public ExcelUtils(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
            
            // Loop through all sheets in the workbook
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                String sheetName = sheet.getSheetName();
                HashMap<String, String> data = new HashMap<>();
                
                for (Row row : sheet) {
                    Cell keyCell = row.getCell(0);
                    Cell valueCell = row.getCell(1);
                    
                    if (keyCell != null && valueCell != null) {
                        String key = getCellValueAsString(keyCell);
                        String value = getCellValueAsString(valueCell);
                        data.put(key, value);  
                    }
                }
                
                // Store data with sheet name as the key
                sheetData.put(sheetName, data);
            }
        }
    }
    
     private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    DateTimeFormatter inputFormatter = new DateTimeFormatterBuilder()
                          .parseCaseInsensitive()
                          .appendPattern("EEE MMM dd HH:mm:ss z yyyy")
                          .toFormatter(Locale.ENGLISH);
                    ZonedDateTime zonedDateTime = ZonedDateTime.parse(cell.getDateCellValue().toString(), inputFormatter.withZone(ZoneId.of("GMT")));
                    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH);
                    String formattedDate = outputFormatter.format(zonedDateTime);

                    return formattedDate; 
                } else {
         
                    String result = String.valueOf(cell.getNumericCellValue());
                    return cleanString(result);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

     public static String cleanString(String input) {
        int eIndex = input.indexOf('E');
        if (eIndex != -1) {
            input = input.substring(0, eIndex);
        }
        
        if (input.endsWith(".0")) {
            input = input.substring(0, input.length() - 2);
        }
        
        input = input.replace(".", "");
        
        return input;
    }
     
    public String getData(String sheetName, String key) {
        HashMap<String, String> data = sheetData.get(sheetName);
        if (data != null) {
            return data.get(key);
        }
        return null;
    }
}