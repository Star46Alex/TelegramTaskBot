package ru.alexstar.TelegramTaskBot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private  String tgName;
    private String firstName;
    private String lastName;
    private String division;
    private long teleUserID;
}
