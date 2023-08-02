package com.tp2.academaispringboot.supervisor.repository;

import com.tp2.academaispringboot.supervisor.model.SupervisorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorEntityRepository extends JpaRepository<SupervisorEntity, Long> {
    SupervisorEntity findByEmailAndPassword(String email, String password);
}