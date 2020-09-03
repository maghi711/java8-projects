package com.aadavan.excel;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ExcelCheck {
    public static void main(String[] args) {
        try {
            File file = new File("D:\\code\\Trident_BlankUploadMCC.csv");
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            List<String[]> allElements = csvReader.readAll();
            List<String[]> header = new ArrayList<>();
            for (int i = 0; i < allElements.size(); i++) {
                String[] row = allElements.get(i);
                if (i == 0) {
                    header.add(row);
                    break;
                }
            }
            csvReader.close();
            FileWriter sw = new FileWriter(file);
            CSVWriter writer = new CSVWriter((sw), ',', CSVWriter.NO_QUOTE_CHARACTER);
            writer.writeAll(header);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
