package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Notification;
import pro.sky.telegrambot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationService {

    private static final Pattern MESSAGE_PATTERN = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TelegramBot telegramBot;

    public void parseAndSaveTask(Long chatId, String message) {
        Matcher matcher = MESSAGE_PATTERN.matcher(message);

        if (matcher.matches()) {
            LocalDateTime notificationDateTime = LocalDateTime.parse(matcher.group(1), FORMATTER);
            String notificationText = matcher.group(3);

            Notification task = new Notification(chatId, notificationText, notificationDateTime);
            notificationRepository.save(task);

            telegramBot.execute(new SendMessage(chatId, "Задача успешно добавлена!"));
        } else {
            telegramBot.execute(new SendMessage(chatId, "Сообщение должно быть в формате: 'dd.MM.yyyy HH:mm Текст задачи'"));
        }
    }
}

