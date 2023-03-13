package ru.alexstar.TelegramTaskBot.service.webdav;

import com.github.sardine.Sardine;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;
import ru.alexstar.TelegramTaskBot.config.webdav.WebDavConfig;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final Sardine sardine;
    private final WebDavConfig webDavConfig;
    public void uploadFile(String path,byte[] bytes) throws URISyntaxException, IOException {
        String fullPath=new URIBuilder(webDavConfig.getRoot()).setPath(path).toString();
        sardine.put(fullPath,bytes);
    }
}
