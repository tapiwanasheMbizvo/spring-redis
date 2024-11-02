package com.tapiwa.demo.logging.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PetDto  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
}
