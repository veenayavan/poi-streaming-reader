package com.app.util;

import com.app.builder.WorkbookBuilder;
import com.app.properties.ExcelTemplateProperties;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;

public class ExcelTemplateUtil {

    public static XSSFWorkbook generateWorkBook(ExcelTemplateProperties properties) {
        return WorkbookBuilder.get()
                .withDisplayGrid(properties.isDisplayGrid())
                .withHeaderRowPosition(properties.getRowPosition())
                .withHeaderColumnPosition(properties.getColumnPosition())
                .withHeaderCellStyle(null) //todo - add cell style based on client if needed
                .withHeaders(properties.getHeaders())
                .withSheetName(properties.getSheetName())
                .build();
    }

    public static void writeWorkbook(XSSFWorkbook workbook, OutputStream outputStream) throws IOException {
        workbook.write(outputStream);
    }

}
