package com.startuptank.wbteam.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "role")
    private Boolean role;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "investor")
    private List<InvestedProject> investedProjects;

}
