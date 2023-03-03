package ru.alexstar.TelegramTaskBot.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private  String name;
    private String description;
    private Boolean isDone;
}
