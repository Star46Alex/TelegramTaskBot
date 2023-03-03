package ru.alexstar.TelegramTaskBot.service.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alexstar.TelegramTaskBot.repository.task.TaskRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
//    public List<TaskDto> getAllActiveTask(){
//        List<TaskDto> result =new ArrayList<>();
//        if()
//        for(User user: userRepository.findAll())
//        {
//            result.add(userMapper.toUserDto(user));
//        }
//        return result;
//
//    }
}
