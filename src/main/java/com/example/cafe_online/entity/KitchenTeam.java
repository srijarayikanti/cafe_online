package com.example.cafe_online.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "kitchenTeam")
@Data
public class KitchenTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TeamId")
    private int teamId;

    @Column(name = "TeamName")
    private String teamName;

    @Column(name = "TeamLead")
    private String teamLead;

    @Column(name = "Members")
    private String members;

    @Column(name = "ShiftTiming")
    private LocalDateTime shiftTiming;

    @Column(name = "Specialization")
    private String specialization;

    @Column(name = "ContactInfo")
    private String contactInfo;

    @Column(name = "Status")
    private String status;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

}
