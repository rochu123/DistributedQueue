package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Data
public class DistributedQueue {

    private List<Producer> producers;
    private List<ConsumerGroup> consumers;

    private List<Topic> topics;


    public void addProducer(Producer producer) {
        this.producers.add(producer);
    }

    public void publishEventToTopic(String producerName, String topicName, String message) {
        Producer producer = findProducerByName(producerName);
        if (Objects.nonNull(producer)) {
            Event event = producer.produceEvent(topicName, message);
            for (Topic topic : topics) {
                if (topic.getName().equals(topicName)) {
                    topic.publishEventToTopic(event);
                    break;
                }
            }
        } else {
            System.out.println("Producer with ID " + producerName + " not found.");
        }
    }


    private Producer findProducerByName(String producerName) {
        for (Producer producer : producers) {
            if (producer.getName().equals(producerName)) {
                return producer;
            }
        }
        return null;
    }
//    public void publishEventToTopic(Topic topic, Event event) {
//        topic.publishEventToTopic(event);
//    }
}
