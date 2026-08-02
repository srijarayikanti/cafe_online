package com.example.cafe_online.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "login")
@Data
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LoginId")
    private int loginId;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "LoginTime")
    private LocalDateTime loginTime;

    @Column(name = "LogoutTime")
    private LocalDateTime logoutTime;
}
