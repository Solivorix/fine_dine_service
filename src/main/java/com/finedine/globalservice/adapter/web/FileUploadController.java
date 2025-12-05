package com.finedine.globalservice.adapter.web;

import com.finedine.globalservice.hexagon.application.port.api.FileStorageServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileStorageServicePort fileStorageServicePort;

    /**
     * Upload restaurant image
     */
    @PostMapping("/restaurant-image")
    public ResponseEntity<Map<String, String>> uploadRestaurantImage(
            @RequestParam("file") MultipartFile file) {

        try {
            String fileUrl = fileStorageServicePort.storeFile(file, "restaurants");

            Map<String, String> response = new HashMap<>();
            response.put("success", "true");
            response.put("fileUrl", fileUrl);
            response.put("message", "File uploaded successfully");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "false");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Upload menu item image
     */
    @PostMapping("/item-image")
    public ResponseEntity<Map<String, String>> uploadItemImage(
            @RequestParam("file") MultipartFile file) {

        try {
            String fileUrl = fileStorageServicePort.storeFile(file, "items");

            Map<String, String> response = new HashMap<>();
            response.put("success", "true");
            response.put("fileUrl", fileUrl);
            response.put("message", "File uploaded successfully");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "false");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Delete uploaded image
     */
    @DeleteMapping("/image")
    public ResponseEntity<Map<String, String>> deleteImage(
            @RequestParam("url") String fileUrl) {

        try {
            fileStorageServicePort.deleteFile(fileUrl);

            Map<String, String> response = new HashMap<>();
            response.put("success", "true");
            response.put("message", "File deleted successfully");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("success", "false");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
