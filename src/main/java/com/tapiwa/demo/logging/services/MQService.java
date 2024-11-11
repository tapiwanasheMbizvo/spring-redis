package com.tapiwa.demo.logging.services;


import com.tapiwa.demo.logging.dto.MQMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MQService implements  Runnable{


    private final RabbitTemplate rabbitTemplate;

    public void send(MQMessageDto message) {
        rabbitTemplate.convertAndSend("petQueue", message);
    }

    public void readFileAndSendToMQ() {

        Path path = Paths.get("assets/largeFile.txt");

        try(BufferedReader reader = Files.newBufferedReader(path)){
            String line = null;
            int i =0;
            while ((line = reader.readLine()) != null && i < 100) {
                MQMessageDto message = new MQMessageDto();
                message.setContent(line + "::" + LocalDateTime.now());
                message.setId(UUID.randomUUID().toString());

                log.info("Sending message to MQ: {}", message);
                send(message);
                i++;
            }
        } catch (IOException e) {
            log.error("Error reading file", e);

        }

    }

    @Override
    public void run() {
        readFileAndSendToMQ();
    }
}
