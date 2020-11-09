package com.app.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
@ConfigurationProperties(prefix = "file.path")
public class StorageProperties {

    private Path upload;

    public Path getUpload() {
        return upload;
    }

    public void setUpload(Path upload) {
        this.upload = upload;
    }
}
