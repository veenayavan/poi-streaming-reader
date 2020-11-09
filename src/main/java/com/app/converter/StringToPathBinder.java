package com.app.converter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@ConfigurationPropertiesBinding
public class StringToPathBinder implements Converter<String, Path> {

    @Override
    public Path convert(String s) {
        return Paths.get(s);
    }
}
