package com.tapiwa.demo.logging.repositories;

import com.tapiwa.demo.logging.models.MQMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MQMessageRepository extends JpaRepository<MQMessage, String> {
}
