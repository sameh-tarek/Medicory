package com.graduationProject.medicory.utils;

import com.graduationProject.medicory.exception.StorageException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorageUtil {
    private static void createDirectoryIfNotExist(String uploadDir) throws IOException {
        Path directory = Path.of(uploadDir);
        if(!Files.exists(directory)){
            Files.createDirectories(directory);
        }
    }
    private static void saveFile(String filePath, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file.");
        }
        byte[] bytes = file.getBytes();
        Path path = Path.of(filePath);
        Files.write(path,bytes);
    }

    public static void deleteFile(String filePath) throws IOException {
        if (filePath!=null){
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
        }
    }

    public static String uploadFile(MultipartFile file, String uploadDir, long maxFileSize) throws IOException {
        if(file.isEmpty()){
            throw new StorageException("Failed to store empty file.");
        }
        if(!isValidSize(file,maxFileSize)){
            throw new StorageException("File size should be less than "+ (maxFileSize/(1024*1024)) +"MB");
        }

        String fileName = file.getOriginalFilename();
        String path = uploadDir + fileName;

        createDirectoryIfNotExist(uploadDir);
        saveFile(path,file);

        return path;
    }

    private static boolean isValidSize(MultipartFile file,long maxFileSize) {
        return file.getSize()<=maxFileSize;
    }
}
