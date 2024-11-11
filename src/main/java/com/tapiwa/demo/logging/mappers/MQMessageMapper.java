package com.tapiwa.demo.logging.mappers;

import com.tapiwa.demo.logging.dto.MQMessageDto;
import com.tapiwa.demo.logging.models.MQMessage;
import org.springframework.stereotype.Component;

@Component
public class MQMessageMapper {

    public MQMessageDto toDto(MQMessage message) {

        MQMessageDto dto = new MQMessageDto();
        dto.setId(message.getId());
        dto.setContent(message.getContent());

        return dto;
    }


    public  MQMessage toEntity(MQMessageDto dto) {

        MQMessage message = new MQMessage();
        message.setId(dto.getId());
        message.setContent(dto.getContent());

        return message;
    }
}
