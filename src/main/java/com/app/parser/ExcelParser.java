package com.app.parser;

import com.app.exception.BadRequestException;
import com.app.properties.ExcelTemplateProperties;
import com.app.properties.StorageProperties;
import com.app.service.ExcelPropertiesService;
import com.monitorjbl.xlsx.StreamingReader;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component("excelParser")
public class ExcelParser {

    @Autowired
    private StorageProperties storageProperties;

    @Autowired
    private ExcelPropertiesService excelPropertiesService;

    public void parse(String fileName, Long templateId) {
        ExcelTemplateProperties templateProperties = excelPropertiesService.getTemplateProperties(templateId);

        try (InputStream is = new FileInputStream(storageProperties.getUpload().resolve(fileName).toString());
             Workbook wb = StreamingReader
                     .builder()
                     .rowCacheSize(100)
                     .bufferSize(4096)
                     .open(is)) {

            if (wb.getNumberOfSheets() > 1) {
                throw new BadRequestException("more than 1 sheet found");
            }

            Sheet sheet = wb.getSheet(templateProperties.getSheetName());

            int rowNum = 0;

            //List<Row> rows = IteratorUtils.toList(sheet.rowIterator());
            for (Row row : sheet) {
                rowNum = row.getRowNum();

                // header
                if (rowNum == templateProperties.getRowPosition()) {
                    List<String> headers = new ArrayList<>();
                    for (int i = templateProperties.getColumnPosition(); i < templateProperties.headersSize(); i++) {
                        Cell cell = row.getCell(i);
                        headers.add(cell.getStringCellValue());
                    }
                    if (!CollectionUtils.containsAll(templateProperties.getHeaders(), headers)) {
                        throw new BadRequestException("headers mismatch");
                    }
                    System.out.println("headers matched");
                    continue;
                }

                System.out.println("current row : " + rowNum);
                for (int i = templateProperties.getColumnPosition(); i < templateProperties.headersSize(); i++) {
                    System.out.println(row.getCell(i).getStringCellValue()); //todo - map input to output
                }
            }
            System.out.println("total data count : " + rowNum);
        } catch (FileNotFoundException e) {
            System.err.println(e);

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
