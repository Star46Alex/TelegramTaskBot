package ru.alexstar.TelegramTaskBot.service.bot;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.alexstar.TelegramTaskBot.config.telegram.BotConfig;
import ru.alexstar.TelegramTaskBot.dto.UserDto;
import ru.alexstar.TelegramTaskBot.mapper.UserMapper;
import ru.alexstar.TelegramTaskBot.service.user.UserService;

@Slf4j
@Getter
@RequiredArgsConstructor
@Component
public class BotService extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }


    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText());
        String messageText= update.getMessage().getText();
        long chatId= update.getMessage().getChatId();
        switch (messageText) {
            case "/start":
                startCommandReceived(chatId, update.getMessage().getChat().getUserName());
                userService.create(update.getMessage().getChat().getUserName(),update.getMessage().getChat().getId());

                break;
            case "/giveTasks":


                    default:
                        var senderId = update.getMessage().getChatId().toString();
                        var adminId = botConfig.getAdmin();
                        if (senderId.equals(adminId))
                        {
                            for(UserDto userDto:userService.getAll()){
                                userDto.getName();
                               var u= userDto.getTeleUserID();
                               if(!senderId.equals(String.valueOf(u)))
                               {
                                   sendMessage(u, update.getMessage().getText());
                               }


                            }


                } else   {
                            sendMessage(chatId, "sorry command was not recognized");

                        }
        }

    }
    private void startCommandReceived(long chatId,String name){
        String answer="Hi, "+name+" nice to meet you!";
        sendMessage(chatId,answer);

    }

    private void sendMessage(long chatId,String textToSend){
        SendMessage message= new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
//    private void sendTask(long chatId,String textToSend){
//        SendMessage message= new SendMessage();
//        message.setChatId(String.valueOf(chatId));
//        message.setText(textToSend);
//        try {
//            execute(message);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
//    }

}