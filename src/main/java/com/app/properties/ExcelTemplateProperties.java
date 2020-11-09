package com.app.properties;

import java.util.List;

public class ExcelTemplateProperties {

    private String sheetName;
    private boolean displayGrid;
    private Integer rowPosition;
    private List<String> headers;
    private Integer columnPosition;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public boolean isDisplayGrid() {
        return displayGrid;
    }

    public void setDisplayGrid(boolean displayGrid) {
        this.displayGrid = displayGrid;
    }

    public Integer getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(Integer rowPosition) {
        this.rowPosition = rowPosition;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public Integer getColumnPosition() {
        return columnPosition;
    }

    public void setColumnPosition(Integer columnPosition) {
        this.columnPosition = columnPosition;
    }

    public Integer headersSize() {
        return headers.size();
    }
}
