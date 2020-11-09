package com.app.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;

public class ExcelUtil {

    public static Row getRow(Sheet sheet, int rowNumber) {
        Row row = null;
        int rowCount = 0;
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()) {
            rowCount++;
            row = rowIterator.next();

            if (rowCount == rowNumber) {
                return row;
            }
        }
        return row;
    }
}
