package com.netas.blog.service.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class ImageService {

    public static String saveBase64Image(String base64Image) {
        try {
            String[] parts = base64Image.split(",");
            String base64Data = parts.length > 1 ? parts[1] : parts[0];

            byte[] imageBytes = Base64.getDecoder().decode(base64Data);

            String fileName = System.currentTimeMillis() + ".jpg";

            String filePath = "uploads/" + fileName;

            File outputFile = new File(filePath);
            outputFile.getParentFile().mkdirs();
            Files.write(outputFile.toPath(), imageBytes);

            return filePath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save the image", e);
        }
    }


    public static void deleteImageFile(String imagePath) {
        File file = new File(imagePath);
        if (file.exists() && file.isFile()) {
            if (!file.delete()) {
                throw new RuntimeException("Failed to delete image file: " + imagePath);
            }
        }
    }

}
