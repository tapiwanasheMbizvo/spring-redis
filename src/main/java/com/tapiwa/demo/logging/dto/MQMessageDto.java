package com.tapiwa.demo.logging.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MQMessageDto implements Serializable {

    private String id;
    private String content;
}
