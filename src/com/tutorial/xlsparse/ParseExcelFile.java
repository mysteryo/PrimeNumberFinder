package com.tutorial.xlsparse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParseExcelFile {
    public static int columnIndex = 1;

    public static List<Double> getParsedNumbers(String path) throws IOException {
        File file = new File(path);
        String extension = path.split("\\.")[1];
        FileInputStream fis = new FileInputStream(file);
        List<Double> numbers = new ArrayList<>();
        if(extension.equals("xls")){
            numbers = readXlsFile(fis);
        }
        else if (extension.equals("xlsx")){
            numbers = readXlsxFile(fis);
        }
        else {
        	System.out.println("APPLICATION ACCEPTS ONLY .XLS OR .XLSX FILES");
        	System.exit(1);
        }
        return numbers;
    }

    private static List<Double> readXlsFile(FileInputStream fis) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        wb.close();
        return getColumnNumbers(rowIterator);
    }

    private static List<Double> readXlsxFile(FileInputStream fis) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        wb.close();
        return getColumnNumbers(rowIterator);
    }

    private static List<Double> getColumnNumbers(Iterator<Row> rowIterator){
        List<Double> doubleList = new ArrayList<>();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            Cell cell = row.getCell(columnIndex);
            String value = cell.getStringCellValue();
            try {
                doubleList.add(Double.parseDouble(value));
            }
            //just skip unparseable strings
            catch (NumberFormatException e){}
        }
        return doubleList;
    }
}
