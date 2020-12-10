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

    public static List<Integer> getParsedNumbers(String path) throws IOException {
        File file = new File(path);
        String extension = path.split("\\.")[1];
        FileInputStream fis = new FileInputStream(file);
        List<Integer> numbers;
        if(extension.equals("xls")){
            numbers = readXlsFile(fis);
        }
        else {
            numbers = readXlsxFile(fis);
        }
        return numbers;
    }

    private static List<Integer> readXlsFile(FileInputStream fis) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        return getColumnNumbers(rowIterator);
    }

    private static List<Integer> readXlsxFile(FileInputStream fis) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        return getColumnNumbers(rowIterator);
    }

    private static List<Integer> getColumnNumbers(Iterator<Row> rowIterator){
        List<Integer> intList = new ArrayList<>();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            Cell cell = row.getCell(columnIndex);
            String value = cell.getStringCellValue();
            try {
                intList.add(Integer.parseInt(value));
            }catch (NumberFormatException e){
                System.out.println("Cannot parse '"+value+"'");
            }
        }
        return intList;
    }
}
