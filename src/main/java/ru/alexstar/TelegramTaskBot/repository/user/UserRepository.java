package ru.alexstar.TelegramTaskBot.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexstar.TelegramTaskBot.model.User;



@Repository
public interface UserRepository extends JpaRepository<User,Integer>  {


}