package com.tapiwa.demo.logging.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "_mq_messages")
@Entity
@Data
public class MQMessage {
    @Id
    private String id;
    private String content;
}
