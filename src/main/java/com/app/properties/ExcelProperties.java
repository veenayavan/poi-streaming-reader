package com.app.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "excel.template")
public class ExcelProperties {

    private Map<Long, ExcelTemplateProperties> config;

    public Map<Long, ExcelTemplateProperties> getConfig() {
        return config;
    }

    public void setConfig(Map<Long, ExcelTemplateProperties> config) {
        this.config = config;
    }
}
