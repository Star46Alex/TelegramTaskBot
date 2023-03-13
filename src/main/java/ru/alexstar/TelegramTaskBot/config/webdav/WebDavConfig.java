package ru.alexstar.TelegramTaskBot.config.webdav;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "webdav")
@ConfigurationPropertiesScan
@Setter
@Getter
@Slf4j
public class WebDavConfig {
   private String root;
    private String user_name;
    private String password;
}
