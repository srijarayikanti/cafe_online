package com.example.cafe_online.service;


import com.example.cafe_online.entity.KitchenTeam;
import com.example.cafe_online.repository.kTeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class kTeamServiceImpl implements kTeamService {
    private final kTeamRepository kTeamRepository;

    @Override
    public ResponseEntity<?> saveKTeamDetails(KitchenTeam request) {
        KitchenTeam kitchenTeam = new KitchenTeam();
        BeanUtils.copyProperties(request, kitchenTeam);
        kTeamRepository.save(kitchenTeam);
        return ResponseEntity.ok(kitchenTeam);
    }

    @Override
    public ResponseEntity<?> fetchKTeamDetails() {
        return ResponseEntity.ok(kTeamRepository.findAll());
    }
}

