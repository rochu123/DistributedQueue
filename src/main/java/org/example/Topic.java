package org.example;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Data
@Slf4j
public class Topic {

    private String id;
    private String name;
    private List<ConsumerGroup> consumers;

    private List<Partition> partitions;


    public Topic(String name, List<ConsumerGroup> consumerGroups) {
        this.name = name;
        this.consumers = consumerGroups;
        this.id = UUID.randomUUID().toString();
        this.partitions = new ArrayList<>();
        createPartitions();
    }

    private void createPartitions() {
        for (int i = 0; i < 4; i++) {
            Partition partition = new Partition(i + 1, new ArrayList<>());
            this.partitions.add(partition);
        }
    }

    public void add(ConsumerGroup c) {
        this.consumers.add(c);
    }


    public void publishEventToTopic(Event e) {
        CompletableFuture.runAsync(() -> {
            Random random = new Random();
            int randomIndex = random.nextInt(this.partitions.size());
            Partition partition = partitions.get(randomIndex);
            partition.publishEvent(e, this.consumers);
        });

        log.info("Event published asynchronously to topic {}", this.name);

    }
}
