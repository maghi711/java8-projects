package com.aadavan.trident.qa;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ReadExcel {

    public static void main(String[] args) {
        ReadExcel excel = new ReadExcel();
        Map map = excel.loadExcelLines("C:\\Users\\mahesh.j\\Documents\\SBIC-TestData.xlsx");
    }
        public Map loadExcelLines(String fileName)
        {
            Map<String, Map<Integer, String>> updateMap = new LinkedHashMap<>();
            String sheetName = null;
            FileInputStream fis = null;
            try
            {
                fis = new FileInputStream(fileName);
                XSSFWorkbook workBook = new XSSFWorkbook(fis);
                LinkedList<String> vReqHeaders = new LinkedList<>();
                Map<String, Map<Integer, String>> vReqMap = new LinkedHashMap<>();
                Map<Integer, Map<String, Map<Integer, String>>> processMap = new HashMap<>();
                initMap(processMap);
                int k = 0;
                for (int i = 0; i < workBook.getNumberOfSheets(); i++)
                {
                    XSSFSheet sheet = workBook.getSheetAt(i);
                    sheetName = workBook.getSheetName(i);
                    Iterator rows = sheet.rowIterator();
                    boolean header = false;
                    boolean first = true;
                    while (rows.hasNext()) {
                        XSSFRow row = (XSSFRow) rows.next();
                        int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                        System.out.println("physicalNumberOfCells = " + physicalNumberOfCells);
                        if (physicalNumberOfCells == 1) {
                            first = true;
                            k++;
                            continue;
                        }
                        Iterator cells = row.cellIterator();
                        if (first) {
                                header = true;
                                vReqHeaders = new LinkedList<>();
                        }
                        int j = 0;
                        while (cells.hasNext())
                        {
                            XSSFCell cell = (XSSFCell) cells.next();
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            if (header) {
                                vReqHeaders.add(cell.getStringCellValue());
                            }
                            else {
                                Map<String, Map<Integer, String>> totalMap = processMap.get(k);
                                addToMap(vReqHeaders.get(j), totalMap, cell.getStringCellValue());
                                j++;
                            }
                        }
                        if (header) {
                            header = false;
                            first = false;
                        }
                    }
                }
                //System.out.println("processMap = " + processMap);
                System.out.println(processMap.get(1));
                System.out.println(processMap.get(2));
                System.out.println(processMap.get(3));
                System.out.println(processMap.get(4));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally  {
                if (fis != null) {
                    try {
                        fis.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return updateMap;
        }

    private void initMap(Map<Integer, Map<String, Map<Integer, String>>> processMap) {
        processMap.put(1, new LinkedHashMap<>()); // VREQ
        processMap.put(2, new LinkedHashMap<>()); // PREQ
        processMap.put(3, new LinkedHashMap<>()); // RREQ
        processMap.put(4, new LinkedHashMap<>()); // UREQ
    }

    private void addToMap(String headerName, Map<String, Map<Integer, String>> totalMap, String stringCellValue) {
        if (totalMap.isEmpty()) {
            Map<Integer, String> vMap = new LinkedHashMap<>();
            vMap.put(1, stringCellValue);
            totalMap.put(headerName, vMap);
        } else {
            Map<Integer, String> integerStringMap = totalMap.get(headerName);
            if (integerStringMap != null && !integerStringMap.isEmpty()) {
                int size = integerStringMap.size();
                size++;
                integerStringMap.put(size, stringCellValue);
            } else {
                Map<Integer, String> vMap = new LinkedHashMap<>();
                vMap.put(1, stringCellValue);
                totalMap.put(headerName, vMap);
            }
        }
    }
}
