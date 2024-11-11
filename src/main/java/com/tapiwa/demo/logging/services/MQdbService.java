package com.tapiwa.demo.logging.services;


import com.tapiwa.demo.logging.dto.MQMessageDto;

public interface MQdbService {

    MQMessageDto saveMessage(MQMessageDto messageDto);

}
