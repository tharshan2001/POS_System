package com.pos.system.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUtil {

    private static final String IMAGE_FOLDER = "uploads/products";

    /**
     * Reads an image as a byte array from the uploads/products directory.
     *
     * @param filename The name of the image file
     * @return byte array of the image
     * @throws IOException if the file is not found or cannot be read
     */
    public static byte[] getImageBytes(String filename) throws IOException {
        Path imagePath = Paths.get(IMAGE_FOLDER).resolve(filename);
        if (!Files.exists(imagePath)) {
            throw new IOException("Image not found: " + filename);
        }
        return Files.readAllBytes(imagePath);
    }
}