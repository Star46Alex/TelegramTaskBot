package ru.alexstar.TelegramTaskBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableConfigurationProperties({
//		WebDavConfig.class
//})
public class TelegramTaskBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramTaskBotApplication.class, args);
	}

}
