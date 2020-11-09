package com.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface IExcelParsingService {

    public void parse(MultipartFile file, Long templateId);

}
