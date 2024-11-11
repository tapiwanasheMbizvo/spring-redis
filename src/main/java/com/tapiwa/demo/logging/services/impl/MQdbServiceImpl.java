package com.tapiwa.demo.logging.services.impl;

import com.tapiwa.demo.logging.dto.MQMessageDto;
import com.tapiwa.demo.logging.mappers.MQMessageMapper;
import com.tapiwa.demo.logging.models.MQMessage;
import com.tapiwa.demo.logging.repositories.MQMessageRepository;
import com.tapiwa.demo.logging.services.MQdbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MQdbServiceImpl implements MQdbService, Runnable {

    private final MQMessageRepository repository;
    private final MQMessageMapper mapper;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public MQMessageDto saveMessage(MQMessageDto messageDto) {
        return mapper.toDto(repository.save(mapper.toEntity(messageDto)));
    }

    @Override
    public void run() {
        while (true) {
            try {
                List<Object> messages = (List<Object>) rabbitTemplate.receiveAndConvert("petQueue", 10);
                if (messages != null && !messages.isEmpty()) {
                    for (Object obj : messages) {
                        MQMessage message = (MQMessage) obj;
                        log.info("Received message from queue: {}", message);
                        saveMessage(mapper.toDto(message));
                    }
                }
                Thread.sleep(1000); // Poll every second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Thread was interrupted", e);
            } catch (Exception e) {
                log.error("Error processing message from queue", e);
            }
        }
    }

}