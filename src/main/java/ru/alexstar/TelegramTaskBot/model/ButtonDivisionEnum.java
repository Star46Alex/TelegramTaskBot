package ru.alexstar.TelegramTaskBot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ButtonDivisionEnum {
    ENERGY_BUTTON("Энергетика"),
    CONNECTION_BUTTON("Связь"),
    CLIMATE_BUTTON("Климатика"),
    FIRE_SERVICE_BUTTON("Пожарные");
    private final String buttonDivision;

}
