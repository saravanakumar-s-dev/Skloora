package org.newfort.sk.Skloora.Utility;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileSave {

    private final String Dir = "D:\\Senthil\\Personal\\SARAVANAKUMAR\\programs\\Java\\Spring\\Skloora\\skloora-post-media";

    public String Save(MultipartFile file) throws IOException {

        String filename = UUID.randomUUID() + file.getOriginalFilename();

        Path path = Paths.get(Dir, filename);

        Files.write(path, file.getBytes());

        return "/skloora-post-media/" + filename;
    }
}