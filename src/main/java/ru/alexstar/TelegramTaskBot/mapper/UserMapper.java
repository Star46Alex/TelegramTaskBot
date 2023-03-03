package ru.alexstar.TelegramTaskBot.mapper;

import org.mapstruct.Mapper;
import ru.alexstar.TelegramTaskBot.dto.UserDto;
import ru.alexstar.TelegramTaskBot.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(UserDto userDto);

}
