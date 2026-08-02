package com.example.cafe_online.repository;

import com.example.cafe_online.entity.KitchenTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface kTeamRepository extends JpaRepository<KitchenTeam,Integer> {
}
