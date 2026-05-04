package org.example.demotestautomation.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    private String filePath;
    private String sheetName;

    public ExcelReader(String filePath, String sheetName) {
        this.filePath = filePath;
        this.sheetName = sheetName;
    }

    public List<String[]> readAll() throws IOException {
        List<String[]> data = new ArrayList<>();

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        for (Row row : sheet) {
            int numCols = row.getLastCellNum();
            String[] rowData = new String[numCols];

            for (int i = 0; i < numCols; i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                rowData[i] = getCellValue(cell);
            }

            data.add(rowData);
        }

        workbook.close();
        fis.close();

        return data;
    }

    private String getCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case BLANK -> "";
            default -> "";
        };
    }

    public void printAll() throws IOException {
        List<String[]> data = readAll();

        for (String[] row : data) {
            for (String value : row) {
                System.out.print(value + " | ");
            }
            System.out.println();
        }
    }
}