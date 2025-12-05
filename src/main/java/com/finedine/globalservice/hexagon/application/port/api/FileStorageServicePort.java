package com.finedine.globalservice.hexagon.application.port.api;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageServicePort {
    String storeFile(MultipartFile file, String restaurants);

    void deleteFile(String fileUrl);
}
