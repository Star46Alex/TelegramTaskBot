package ru.alexstar.TelegramTaskBot.impl.controller.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.alexstar.TelegramTaskBot.controller.storage.StorageController;
import ru.alexstar.TelegramTaskBot.service.webdav.StorageService;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StorageControllerImpl implements StorageController {
    private final StorageService storageService;
    @Override
    public ResponseEntity<Boolean> uploadFile(MultipartFile file) throws IOException {
        String path = "/"+file.getOriginalFilename();
        try {
            storageService.uploadFile(path, file.getBytes());
            return ResponseEntity.ok(true);
        }
        catch (URISyntaxException |IOException e){
log.error(e.getMessage());
        }


        return ResponseEntity.ok(false);
    }
}
