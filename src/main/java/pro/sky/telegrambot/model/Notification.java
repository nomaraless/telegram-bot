package pro.sky.telegrambot.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "notification_task")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "notification_text", nullable = false)
    private String notificationText;

    @Column(name = "notification_datetime", nullable = false)
    private LocalDateTime notificationDatetime;

    public Notification(Long chatId, String notificationText, LocalDateTime notificationDatetime) {
        this.chatId = chatId;
        this.notificationText = notificationText;
        this.notificationDatetime = notificationDatetime;
    }

    public Notification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public LocalDateTime getNotificationDatetime() {
        return notificationDatetime;
    }

    public void setNotificationDatetime(LocalDateTime notificationDatetime) {
        this.notificationDatetime = notificationDatetime;
    }
}
