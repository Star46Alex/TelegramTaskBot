package ru.alexstar.TelegramTaskBot.service.bot;


import com.vdurmont.emoji.EmojiParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.alexstar.TelegramTaskBot.config.telegram.BotConfig;
import ru.alexstar.TelegramTaskBot.dto.UserDto;
import ru.alexstar.TelegramTaskBot.keyboards.InlineKeyboardMaker;
import ru.alexstar.TelegramTaskBot.keyboards.ReplyKeyboardMaker;
import ru.alexstar.TelegramTaskBot.mapper.UserMapper;
import ru.alexstar.TelegramTaskBot.model.DivisionEnum;
import ru.alexstar.TelegramTaskBot.repository.task.TaskRepository;
import ru.alexstar.TelegramTaskBot.service.task.TaskService;
import ru.alexstar.TelegramTaskBot.service.user.UserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@RequiredArgsConstructor
@Component
public class BotService extends TelegramLongPollingBot {
    private final BotConfig botConfig;
    private final UserService userService;
    private final TaskService taskService;
    private final UserMapper userMapper;

    private final ReplyKeyboardMaker replyKeyboardMaker;
    private final InlineKeyboardMaker inlineKeyboardMaker;
    private final TaskRepository taskRepository;
    static final String CLIMATE_BUTTON="CLIMATE_BUTTON";
    static final String CONNECTION_BUTTON="CONNECTION_BUTTON";
    static final String FIRE_SERVICE_BUTTON="FIRE_SERVICE_BUTTON";
    static final String ENERGY_BUTTON="ENERGY_BUTTON";


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
        botMenu();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            if (messageText.contains("/task_en") && chatId == botConfig.getAdmin()) {

                var textToTask = EmojiParser.parseToUnicode(messageText.substring(messageText.indexOf(" ")));
                taskService.saveTask(textToTask);
                for (UserDto userDto : userService.getAll()) {
                    var teleUserID = userDto.getTeleUserID();

                    if (chatId != teleUserID) {
                        sendMessage(userDto.getTeleUserID(), textToTask);
                        createTask(teleUserID, update.getMessage().getText());
                    }
                }
            } else {
                switch (messageText) {
                    case "/task":
                        var senderrId = update.getMessage().getChatId().toString();
                        var adminnId = botConfig.getAdmin();
                        if (senderrId.equals(adminnId)) {
                            for (UserDto userDto : userService.getAll()) {
                                userDto.getTgName();
                                var u = userDto.getTeleUserID();

                            }
                            createTask(update.getMessage().getChatId(), update.getMessage().getText());
                        }
                        break;
                    case "/start":

                        choosingDivision(chatId, update.getMessage().getText());


                        break;
                    default:
                        var senderId = update.getMessage().getChatId().toString();
                        var adminId = botConfig.getAdmin();
                        if (senderId.equals(adminId)) {
                            for (UserDto userDto : userService.getAll()) {
                                userDto.getTgName();
                                var u = userDto.getTeleUserID();
                                if (!senderId.equals(String.valueOf(u))) {
                                    sendMessage(u, update.getMessage().getText());
                                }
                            }
                        } else {
                            sendMessage(chatId, "sorry command was not recognized: " + messageText);

                        }
                }
            }
        } else if (update.hasCallbackQuery()) {
            String callbackQuery = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            switch (callbackQuery) {
                case "YES_Button": {

                    for (UserDto userDto : userService.getAll()) {
                        var teleUserID = userDto.getTeleUserID();
                        var name = userDto.getFirstName();
                        String text;
                        if (chatId != teleUserID) {
                            text = "Задание уже взято " + name;
                        } else {
                            text = "Вы взяли задание";
                        }
                        executeEditMessageText(text, chatId, messageId);
                    }
                }
                break;
                case CONNECTION_BUTTON: {
                    registerUser(chatId,update, DivisionEnum.CONNECTION);
                }
                break;
                case FIRE_SERVICE_BUTTON: {
                    registerUser(chatId,update, DivisionEnum.FIRE_SERVICE);
                }
                break;
                case ENERGY_BUTTON: {
                    registerUser(chatId,update, DivisionEnum.ENERGY);
                }
                break;
                case CLIMATE_BUTTON: {
                    registerUser(chatId,update, DivisionEnum.CLIMATE);
                }
                break;

            }
        }
//            else if (callbackQuery.equals(ButtonDivisionEnum.CONNECTION_BUTTON.toString())) {
//                sendMessage(chatId, "ist btn");
//            }


        }


    private void startCommandReceived(long chatId, String name) {
        String answer = "привет, " + name + " ты зарегестрирован!";
        sendMessage(chatId, answer);

    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        executeMessage(message);
    }

    public List<BotCommand> botMenu() {
        List<BotCommand> botCommandList = new ArrayList<>();
        botCommandList.add(new BotCommand("/start", "Старт"));
        botCommandList.add(new BotCommand("/task", "создание дела"));
        botCommandList.add(new BotCommand("/reg", "регистрация пользователя"));
        try {
            execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(),
                    null
            ));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        return botCommandList;
    }

    public void createTask(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        text = "Взять дело";
        message.setText(text);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        inlineKeyboardMaker.getInlineButtonsToTask(inlineKeyboardMarkup,
                rowsInline).setKeyboard(rowsInline);
        message.setReplyMarkup(inlineKeyboardMarkup);
        executeMessage(message);

    }

    public void choosingDivision(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        text = "Выберите подразделение";
        message.setText(text);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        inlineKeyboardMaker.getInlineButtonsToStart(inlineKeyboardMarkup, rowsInline).setKeyboard(rowsInline);
        message.setReplyMarkup(inlineKeyboardMarkup);
        executeMessage(message);
    }

    public void executeEditMessageText(String text, long chatId, long messageId) {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(String.valueOf(chatId));
        messageText.setText(text);
        messageText.setMessageId((int) messageId);
        try {
            execute(messageText);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void registerUser(long chatId,Update update, DivisionEnum division) {
        UserDto checkUser = userService.getByTeleUserID(chatId);
        if (checkUser == null) {
            startCommandReceived(chatId, update.getCallbackQuery().getMessage().getChat().getFirstName());
            userService.create(update.getCallbackQuery().getMessage().getChat().getUserName(), update.getCallbackQuery().getMessage().getChat().getId(), division.toString());

        }

    }
//    private void sendTasks()
}