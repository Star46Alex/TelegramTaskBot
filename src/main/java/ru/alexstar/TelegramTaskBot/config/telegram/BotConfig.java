package ru.alexstar.TelegramTaskBot.config.telegram;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bot")
@ConfigurationPropertiesScan
@Setter
@Getter
@Slf4j
public class BotConfig {
    private String name;
    private String token;


}
