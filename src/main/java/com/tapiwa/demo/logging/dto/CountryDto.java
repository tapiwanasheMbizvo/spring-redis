package com.tapiwa.demo.logging.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class    CountryDto  implements Serializable {

    private static final long serialVersionUID = 165464653453453L;
    private Long id;
    private String countryName;
    private String countryCode;
    private String dialingCode;
    private String currencyCode;
    private String internetTLD;


}
