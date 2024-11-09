package com.tapiwa.demo.logging.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
}
