package ru.alexstar.TelegramTaskBot.controller.storage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RequestMapping("/api/storage")
public interface StorageController {
    @PostMapping(value = "upload", consumes = {MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<Boolean> uploadFile(@RequestParam("file")
                                      MultipartFile file) throws IOException;
}
