package com.example.cafe_online.Controller;


import com.example.cafe_online.entity.KitchenTeam;
import com.example.cafe_online.service.kTeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class kTeamControllerImpl implements kTeamController{
    private final kTeamService kTeamService;


    @Override
    public ResponseEntity<?> saveKTeamDetails(KitchenTeam request) {
        try {
            return kTeamService.saveKTeamDetails(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> fetchKTeamDetails() {
        try {
            return kTeamService.fetchKTeamDetails();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
