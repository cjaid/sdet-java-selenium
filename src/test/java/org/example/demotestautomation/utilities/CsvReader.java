package org.example.demotestautomation.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private String filePath;
    private String delimiter;

    public CsvReader(String filePath) {
        this(filePath, ",");
    }

    public CsvReader(String filePath, String delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    public List<String[]> readAll() throws IOException {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                data.add(values);
            }
        }

        return data;
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