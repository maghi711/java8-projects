package com.aadavan.excel;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class ExcelCheck {
    public static void main(String[] args) {
        try {
            File file = new File("D:\\code\\Trident_BlankUploadMCC.csv");
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            List<String[]> allElements = csvReader.readAll();
            for (int i = 1; i < allElements.size(); i++) {
                String[] row = allElements.get(i);
                if (allElements.isEmpty()) {
                    System.out.println("The record doesnt exists in CSV file");
                } else {
                    allElements.remove(i);
                    System.out.println("The record exists in CSV file");
                }
            }
            csvReader.close();
            FileWriter sw = new FileWriter(file);
            CSVWriter writer = new CSVWriter((sw), ',', CSVWriter.NO_QUOTE_CHARACTER);
            writer.writeAll(allElements);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
