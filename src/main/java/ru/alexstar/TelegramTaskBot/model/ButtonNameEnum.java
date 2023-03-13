package ru.alexstar.TelegramTaskBot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ButtonNameEnum {
    GET_TASKS_BUTTON("Создать задание"),
//    GET_DICTIONARY_BUTTON("Скачать словарь"),
//    UPLOAD_DICTIONARY_BUTTON("Загрузить мой словарь"),
    HELP_BUTTON("Помощь");
    private final String buttonName;
}
