package com.tapiwa.demo.logging.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "_pets")
public class Pet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

}
