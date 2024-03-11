package org.btn.Utility.excel;

import com.google.gson.Gson;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;

public class utils {


        public static void main(String[] args) {
            File excelFile = new File(".\\src\\main\\java\\org\\btn\\Utility\\excel\\exceltest.xlsx");
            String outputJsonFilePath = ".\\src\\main\\java\\org\\btn\\Utility\\excel\\test excel.json";


            // Kolom yang ingin disimpan dalam JSON
            String[] columnsToExport = {"Column1", "Column3", "Column5"};

            try {
                FileInputStream fis = new FileInputStream(excelFile);
                Workbook workbook = WorkbookFactory.create(fis);
                Sheet sheet = workbook.getSheetAt(0);

                List<Map<String, String>> data = new ArrayList<>();
                Iterator<Row> rowIterator = sheet.iterator();
                Row headerRow = rowIterator.next();

                while (rowIterator.hasNext()) {
                    Row currentRow = rowIterator.next();
                    Iterator<Cell> cellIterator = currentRow.iterator();

                    Map<String, String> rowData = new HashMap<>();
                    int cellIndex = 0;
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        String columnName = headerRow.getCell(cellIndex).getStringCellValue();
                        if (isColumnToExport(columnName, columnsToExport)) {
                            rowData.put(columnName, currentCell.getStringCellValue());
                        }
                        cellIndex++;
                    }
                    data.add(rowData);
                }

                Gson gson = new Gson();
                String json = gson.toJson(data);

                // Menyimpan data JSON ke file
                FileWriter writer = new FileWriter(outputJsonFilePath);
                writer.write(json);
                writer.close();

                System.out.println("Data telah berhasil disimpan dalam file JSON.");

                workbook.close();
                fis.close();
            } catch (IOException | InvalidFormatException e) {
                e.printStackTrace();
            }
        }

        // Fungsi untuk mengecek apakah nama kolom ada dalam array kolom yang ingin diekspor
        private static boolean isColumnToExport(String columnName, String[] columnsToExport) {
            for (String column : columnsToExport) {
                if (column.equals(columnName)) {
                    return true;
                }
            }
            return false;
        }
}