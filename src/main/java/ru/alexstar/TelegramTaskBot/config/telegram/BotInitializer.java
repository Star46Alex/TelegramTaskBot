package ru.alexstar.TelegramTaskBot.config.telegram;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.alexstar.TelegramTaskBot.service.bot.BotService;

@RequiredArgsConstructor
@Component
public class BotInitializer {

   private final BotService botService;
    @Bean
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi=new TelegramBotsApi(DefaultBotSession.class);
        try{
            telegramBotsApi.registerBot(botService);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}

