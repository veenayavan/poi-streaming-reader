package com.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {

    void save(MultipartFile file);

    void delete(MultipartFile file);

    void delete(String file);

}
