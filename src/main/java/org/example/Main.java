package org.example;

import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        ConsumerGroup c1 = new ConsumerGroup(UUID.randomUUID().toString(), "CG1");
        ConsumerGroup c2 = new ConsumerGroup(UUID.randomUUID().toString(), "CG2");
        Topic t1 = new Topic("topic1", List.of(c1, c2));
        Producer producer = new Producer(UUID.randomUUID().toString(), "PG1");

        DistributedQueue distributedQueue = new DistributedQueue(List.of(producer), List.of(c1, c2), List.of(t1));

        distributedQueue.publishEventToTopic("PG1","topic1","testing");
        distributedQueue.publishEventToTopic("PG1","topic1","testing123");
        distributedQueue.publishEventToTopic("PG1","topic1","testing456");
        distributedQueue.publishEventToTopic("PG1","topic1","testing789");

        System.out.println("Hello world!");
    }
}