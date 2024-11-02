package com.tapiwa.demo.logging.controllers.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class HttpResponse {
    protected LocalDateTime localDateTime;
    protected String message;
    protected HttpStatus httpStatus;
    protected Map<?, ?> data;

}
