package com.finedine.globalservice.adapter.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UniqueHashGenerator {
    public static String generateHash(String dataToHash){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes); // Encode to Base64 URL-safe format
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash ID", e);
        }
    }
}
