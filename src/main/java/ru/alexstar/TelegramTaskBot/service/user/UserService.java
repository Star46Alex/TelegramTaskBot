package ru.alexstar.TelegramTaskBot.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alexstar.TelegramTaskBot.dto.UserDto;
import ru.alexstar.TelegramTaskBot.mapper.UserMapper;
import ru.alexstar.TelegramTaskBot.model.User;
import ru.alexstar.TelegramTaskBot.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto create(String userName,long teleUserId,String division) {
        UserDto userDto = new UserDto();
        userDto.setTgName(userName);
        userDto.setTeleUserID(teleUserId);
        userDto.setDivision(division);
User user =userRepository.save(userMapper.toUser(userDto));
        return userMapper.toUserDto(user);
    }
    public void deleteById(int id) {
        userRepository.findById(id).orElseThrow(RuntimeException::new);

    }
    public List<UserDto> getAll() {
        List<UserDto> result =new ArrayList<>();
        for(User user: userRepository.findAll())
        {
            result.add(userMapper.toUserDto(user));
        }
       return result;
    }

    public UserDto getById(int id) {
        User User = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return userMapper.toUserDto(User);
    }
    public UserDto getByTeleUserID(long id){
        User user = userRepository.findByTeleUserID(id);
        return userMapper.toUserDto(user);

    }
    public UserDto getByDivision(String division){
        User user = userRepository.findByDivision(division);
        return userMapper.toUserDto(user);

    }
}
