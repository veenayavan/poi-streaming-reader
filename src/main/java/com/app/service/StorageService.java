package com.app.service;

import com.app.exception.InternalServerException;
import com.app.properties.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@Service("storageService")
public class StorageService implements IStorageService {

    @Autowired
    private StorageProperties storageProperties;

    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), storageProperties.getUpload().resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new InternalServerException("exception while saving file", e);
        }
    }

    @Override
    public void delete(MultipartFile file) {
        try {
            Files.delete(storageProperties.getUpload().resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new InternalServerException("exception while deleting file", e);
        }
    }

    @Override
    public void delete(String fileName) {
        try {
            Files.delete(storageProperties.getUpload().resolve(fileName));
        } catch (IOException e) {
            throw new InternalServerException("exception while deleting file", e);
        }
    }


}
