package pl.zzmudzio.tools;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFile {
    public static List<String[]> readTestDataValue(String testDataName) throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src\\test\\java\\TestData.csv"))) {
            return csvReader
                    .readAll()
                    .stream()
                    .filter((String[] singleLine) -> singleLine[0].equals(testDataName))
                    .collect(Collectors.toList());
        }
    }
}