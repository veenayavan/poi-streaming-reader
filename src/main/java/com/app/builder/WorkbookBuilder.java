package com.app.builder;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class WorkbookBuilder {

    private boolean displayGrid;
    private Integer headerRowPosition;
    private Integer headerColumnPosition;
    private String sheetName;
    private List<String> headers;
    private CellStyle headerCellStyle;

    public static WorkbookBuilder get() {
        return new WorkbookBuilder();
    }

    public WorkbookBuilder withDisplayGrid(boolean displayGrid) {
        this.displayGrid = displayGrid;
        return this;
    }

    public WorkbookBuilder withHeaderRowPosition(Integer headerRowPosition) {
        this.headerRowPosition = headerRowPosition;
        return this;
    }

    public WorkbookBuilder withHeaderColumnPosition(Integer headerColumnPosition) {
        this.headerColumnPosition = headerColumnPosition;
        return this;
    }

    public WorkbookBuilder withSheetName(String sheetName) {
        this.sheetName = sheetName;
        return this;
    }

    public WorkbookBuilder withHeaderCellStyle(CellStyle headerCellStyle) {
        this.headerCellStyle = headerCellStyle;
        return this;
    }

    public WorkbookBuilder withHeaders(List<String> headers) {
        this.headers = headers;
        return this;
    }

    public XSSFWorkbook build() {
        XSSFRow headerRow;
        XSSFSheet sheet;
        XSSFWorkbook workbook = new XSSFWorkbook();
        sheet = this.sheetName == null ? workbook.createSheet() : workbook.createSheet(this.sheetName);
        sheet.setDisplayGridlines(this.displayGrid);

        if (!CollectionUtils.isEmpty(headers)) {
            headerRow = headerRowPosition == null ? sheet.createRow(1) : sheet.createRow(this.headerRowPosition);
            headerColumnPosition = headerColumnPosition != null ? headerColumnPosition : 1;
            for (int i = 0; i < headers.size(); i++) {
                XSSFCell cell = headerRow.createCell(headerColumnPosition + i);
                cell.setCellValue(headers.get(i));
                if (headerCellStyle != null) {
                    cell.setCellStyle(headerCellStyle);
                }
            }
        }

        return workbook;
    }
}