package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {

    private String topicName;
    private String message;

    private EventStatus eventStatus;


    public Event(String topicName, String message) {
        this.topicName = topicName;
        this.message = message;
    }

    enum EventStatus {
        IN_PROGRESS,
        PUBLISHED
    }
}
