package com.tapiwa.demo.logging.controllers.fileControllers;

import com.tapiwa.demo.logging.controllers.response.HttpResponse;
import com.tapiwa.demo.logging.services.MQService;
import com.tapiwa.demo.logging.services.impl.MQdbServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
@Slf4j
public class StartFileSendingToMq {


    private final MQService mqService;
    private final MQdbServiceImpl mQdbService;

    @GetMapping("/send")
    public ResponseEntity<HttpResponse> sendFileToMQ() {

        log.info("Sending file to MQ");
        Thread thread = new Thread(mqService);
        thread.start();
        log.info("File sent to MQ");

        return ResponseEntity.ok(HttpResponse.builder()
                .message("File sent to MQ")
                        .localDateTime(LocalDateTime.now())
                .build());
    }

    @GetMapping("/start")
    public ResponseEntity<HttpResponse> startFileSendingToMQ() {

        log.info("Starting db statff");
        Thread thread = new Thread(mQdbService);
        thread.start();
        log.info("File sent to DB");

        return ResponseEntity.ok(HttpResponse.builder()
                .message("DB Started")
                        .localDateTime(LocalDateTime.now())
                .build());
    }


}
