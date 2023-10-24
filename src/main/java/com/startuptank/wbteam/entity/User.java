package com.startuptank.wbteam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "active")
    private boolean active = false;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "role")
    private boolean role = false;
    @Column(name = "address")
    private String address;
}
