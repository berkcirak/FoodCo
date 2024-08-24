package com.example.FoodCo.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorageService {

    private final Path rootLocation= Paths.get("uploads");

    public void store(MultipartFile file) throws IOException{
        Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
    }


}
