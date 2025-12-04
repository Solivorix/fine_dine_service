package com.finedine.globalservice.hexagon.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path uploadLocation;

    public FileStorageService(@Value("${file.upload-dir:uploads}") String uploadDir) {
        this.uploadLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }

    /**
     * Store file and return the URL path
     */
    public String storeFile(MultipartFile file, String folder) {
        // Validate file
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot store empty file");
        }

        if (!isValidImageFile(file)) {
            throw new IllegalArgumentException("Only image files are allowed (jpg, png, gif, webp)");
        }

        // Get original filename and validate
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.contains("..")) {
            throw new IllegalArgumentException("Invalid filename");
        }

        // Generate unique filename
        String fileExtension = getFileExtension(originalFilename);
        String newFilename = UUID.randomUUID().toString() + fileExtension;

        try {
            // Create folder if it doesn't exist
            Path folderPath = this.uploadLocation.resolve(folder);
            Files.createDirectories(folderPath);

            // Copy file to the target location
            Path targetLocation = folderPath.resolve(newFilename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Return the URL path
            return "/uploads/" + folder + "/" + newFilename;
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + newFilename, e);
        }
    }

    /**
     * Delete file from storage
     */
    public void deleteFile(String fileUrl) {
        try {
            // Extract path from URL (e.g., "/uploads/restaurants/file.jpg" -> "restaurants/file.jpg")
            String filePath = fileUrl.replace("/uploads/", "");
            Path file = this.uploadLocation.resolve(filePath);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file", e);
        }
    }

    /**
     * Check if file is a valid image
     */
    private boolean isValidImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType.equals("image/jpeg") || contentType.equals("image/jpg") || contentType.equals("image/png") || contentType.equals("image/gif") || contentType.equals("image/webp");
    }

    /**
     * Get file extension from filename
     */
    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return "";
        }
        return filename.substring(lastDotIndex);
    }
}
