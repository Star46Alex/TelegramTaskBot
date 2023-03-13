package ru.alexstar.TelegramTaskBot.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alexstar.TelegramTaskBot.model.Task;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
Optional<Task> findById(Long id);
}
