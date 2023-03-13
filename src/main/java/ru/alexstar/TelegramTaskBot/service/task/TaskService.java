package ru.alexstar.TelegramTaskBot.service.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alexstar.TelegramTaskBot.dto.TaskDto;
import ru.alexstar.TelegramTaskBot.mapper.TaskMapper;
import ru.alexstar.TelegramTaskBot.model.Task;
import ru.alexstar.TelegramTaskBot.repository.task.TaskRepository;



@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;


    public TaskDto saveTask(String textTask){
        Task task=new Task();
        task.setDescription(textTask);
      return taskMapper.toTaskDto(taskRepository.save(task));
    }
//    public List<TaskDto> getAllActiveTask(){
//        List<TaskDto> result =new ArrayList<>();
//        if()
//        for(User user: userRepository.findAll())
//        {
//            result.add(userMapper.toUserDto(user));
//        }
//        return result;
public TaskDto getById(Long id){
return taskMapper.toTaskDto(taskRepository.findById(id).orElseThrow(NullPointerException::new));
}
//
//    }
}
