package ru.alexstar.TelegramTaskBot.keyboards;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.alexstar.TelegramTaskBot.model.ButtonDivisionEnum;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@RequiredArgsConstructor
public class InlineKeyboardMaker {
   // private final List<List<InlineKeyboardButton>> rowsInline;


    public InlineKeyboardMarkup getInlineButtonsToStart(InlineKeyboardMarkup inlineKeyboardMarkup,List<List<InlineKeyboardButton>> rowsInline) {
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        var energyButton = new InlineKeyboardButton();
        var connectionButton = new InlineKeyboardButton();
        var climateButton = new InlineKeyboardButton();
        var fireServiceButton = new InlineKeyboardButton();
        energyButton.setText(ButtonDivisionEnum.ENERGY_BUTTON.getButtonDivision());
        connectionButton.setText(ButtonDivisionEnum.CONNECTION_BUTTON.getButtonDivision());
        climateButton.setText(ButtonDivisionEnum.CLIMATE_BUTTON.getButtonDivision());
        fireServiceButton.setText(ButtonDivisionEnum.FIRE_SERVICE_BUTTON.getButtonDivision());
        energyButton.setCallbackData(ButtonDivisionEnum.ENERGY_BUTTON.toString());
        connectionButton.setCallbackData(ButtonDivisionEnum.CONNECTION_BUTTON.toString());
        climateButton.setCallbackData(ButtonDivisionEnum.CLIMATE_BUTTON.toString());
        fireServiceButton.setCallbackData(ButtonDivisionEnum.FIRE_SERVICE_BUTTON.toString());
        rowInline1.add(energyButton);
        rowInline1.add(connectionButton);
        rowInline2.add(climateButton);
        rowInline2.add(fireServiceButton);
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        inlineKeyboardMarkup.setKeyboard(rowsInline);
        return inlineKeyboardMarkup;
    }
    public InlineKeyboardMarkup getInlineButtonsToTask(InlineKeyboardMarkup inlineKeyboardMarkup,List<List<InlineKeyboardButton>> rowsInline) {
//        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        var yesButton = new InlineKeyboardButton();
        yesButton.setText("Да");
        yesButton.setCallbackData("YES_BUTTON");
        rowInline.add(yesButton);
        rowsInline.add(rowInline);
        return inlineKeyboardMarkup;
    }


}

