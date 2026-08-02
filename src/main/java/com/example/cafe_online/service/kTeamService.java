package com.example.cafe_online.service;


import com.example.cafe_online.entity.KitchenTeam;
import org.springframework.http.ResponseEntity;

public interface kTeamService {
    ResponseEntity<?> saveKTeamDetails(KitchenTeam request);

    ResponseEntity<?> fetchKTeamDetails();
}
