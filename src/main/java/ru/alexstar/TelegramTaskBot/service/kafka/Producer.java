//package ru.alexstar.TelegramTaskBot.service.kafka;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//import ru.alexstar.TelegramTaskBot.model.User;
//import ru.alexstar.TelegramTaskBot.repository.UserRepository;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class Producer {
//    private final KafkaTemplate<String, String> kafkaTemplate;
//        private static final String TOPIC = "users";
//        protected final UserRepository userRepository;
//
//
//        public void sendMessage(User user) {
//            if (user.getName() == null || user.getDescription().isEmpty()) log.info("#### Empty name/description message");
//            log.info("#### Producing message [user={}]", user);
//            kafkaTemplate.send(TOPIC, "Writing in log -> " + user);
//        }
//    }
//
