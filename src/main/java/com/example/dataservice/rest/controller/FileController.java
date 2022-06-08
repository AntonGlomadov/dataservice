package com.example.dataservice.rest.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/content")
public class FileController {
    @PostMapping(path = "/save")
    public List<String> handleFileUpload(@RequestParam("file") MultipartFile[] data) {
        return Arrays.stream(data).map(file -> {
            String filePathPart = "data/"+UUID.randomUUID().toString().replace("-", "") + ".jpeg";
            try {
                file.transferTo(new File("/upload/" + filePathPart));
            } catch (IOException e) {
               return null;
            }
            return filePathPart;
        }).collect(Collectors.toList());
    }

}
