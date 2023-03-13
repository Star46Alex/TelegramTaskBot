package ru.alexstar.TelegramTaskBot.mapper;

import org.mapstruct.Mapper;
import ru.alexstar.TelegramTaskBot.dto.TaskDto;
import ru.alexstar.TelegramTaskBot.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
       TaskDto toTaskDto(Task task);
        Task toTask(TaskDto taskDto);



    }

