package com.graduationProject.medicory.utils;

import com.graduationProject.medicory.exception.StorageException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorageUtil {
    public static void createDirectoryIfNotExist(String uploadDir) throws IOException {
        Path directory = Paths.get(uploadDir);
        if(!Files.exists(directory)){
            Files.createDirectories(directory);
        }
    }
    public static void saveFile(String filePath, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file.");
        }
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath);
        Files.write(path,bytes);
    }

    public static void deleteFile(String filePath) throws IOException {
        if (filePath!=null){
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
        }
    }
}
