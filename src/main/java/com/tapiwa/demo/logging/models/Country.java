package com.tapiwa.demo.logging.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "_countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country_name")
    private String countryName;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "dialing_code")
    private String dialingCode;
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "internet_tld")
    private String internetTLD;

}
