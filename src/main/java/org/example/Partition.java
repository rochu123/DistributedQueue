package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import static org.example.Event.EventStatus.IN_PROGRESS;
import static org.example.Event.EventStatus.PUBLISHED;

@AllArgsConstructor
@Data
public class Partition {
    private int no;
    private List<Event> eventList;


    public void publishEvent(Event event, List<ConsumerGroup> consumerGroups) {
        event.setEventStatus(IN_PROGRESS);
        this.eventList.add(event);

        for (ConsumerGroup consumerGroup : consumerGroups) {
            consumerGroup.update(event);
        }

        event.setEventStatus(PUBLISHED);
    }
}
