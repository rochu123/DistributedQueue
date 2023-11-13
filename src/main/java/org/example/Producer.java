package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import static org.example.Event.EventStatus.IN_PROGRESS;

@Data
@AllArgsConstructor
public class Producer {
    private String id;
    private String name;

    public Event produceEvent(String topicName, String message) {
        return new Event(topicName, message, IN_PROGRESS);
    }
}
