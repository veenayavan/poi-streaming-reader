package com.app.service;

import com.app.parser.ExcelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelParsingService implements IExcelParsingService {

    @Autowired
    private ExcelParser excelParser;

    @Autowired
    private IStorageService storageService;

    public void parse(MultipartFile file, Long templateId) {
        storageService.save(file);
        excelParser.parse(file.getOriginalFilename(), templateId);
        storageService.delete(file);
    }
}
