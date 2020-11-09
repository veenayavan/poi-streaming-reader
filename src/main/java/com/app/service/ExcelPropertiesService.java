package com.app.service;

import com.app.exception.InternalServerException;
import com.app.properties.ExcelProperties;
import com.app.properties.ExcelTemplateProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("excelPropertiesService")
public class ExcelPropertiesService {

    @Autowired
    private ExcelProperties excelProperties;

    public ExcelTemplateProperties getTemplateProperties(Long templateId) {
        Map<Long, ExcelTemplateProperties> config = excelProperties.getConfig();
        if (config == null) {
            throw new InternalServerException("no excel template configuration found");
        }

        ExcelTemplateProperties templateProperties = config.get(templateId);
        if (templateProperties == null) {
            throw new InternalServerException(String.format("no template found for id : %d", templateId));
        }
        return templateProperties;
    }

}
