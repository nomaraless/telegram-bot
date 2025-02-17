--liquibase formated sql

--changeset nomad: 1
CREATE TABLE notification_task (
    id BIGSERIAL PRIMARY KEY,
    chat_id BIGINT NOT NULL,
    notification_text TEXT NOT NULL,
    notification_datetime TIMESTAMP NOT NULL
);
