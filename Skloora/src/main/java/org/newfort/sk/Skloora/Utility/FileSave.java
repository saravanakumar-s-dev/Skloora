package org.newfort.sk.Skloora.Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSave {

    private final String Dir = "D:\\Senthil\\Personal\\SARAVANAKUMAR\\Photos\\uploads";

    public String Save(MultipartFile file) throws IOException {

        String filename = UUID.randomUUID() + file.getOriginalFilename();

        Path path = Paths.get(Dir, filename);

        Files.write(path, file.getBytes());

        return "/uploads/" + filename;
    }
}