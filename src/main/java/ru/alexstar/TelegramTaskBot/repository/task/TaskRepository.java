package ru.alexstar.TelegramTaskBot.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexstar.TelegramTaskBot.model.User;

@Repository
public interface TaskRepository extends JpaRepository<User,Integer> {
}
