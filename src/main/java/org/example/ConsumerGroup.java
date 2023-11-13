package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Slf4j
public class ConsumerGroup {
    private String id;
    private String name;

    public void update(Event e) {

        log.info("received message event by consumer {} {} ",this.name,e);
    }
}
