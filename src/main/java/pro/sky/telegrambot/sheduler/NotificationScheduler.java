package pro.sky.telegrambot.sheduler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Notification;
import pro.sky.telegrambot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class NotificationScheduler {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private TelegramBot telegramBot;

    @Scheduled(cron = "0 0/1 * * * *")
    public void sendNotifications() {
        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<Notification> tasks = notificationRepository.findByNotificationDatetime(currentTime);

        tasks.forEach(task -> {
            telegramBot.execute(new SendMessage(task.getChatId(), task.getNotificationText()));
        });
    }

}
