package com.tp2.academaispringboot.supervisor.repository;

import com.tp2.academaispringboot.supervisor.model.SupervisorEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SupervisorEntityRepository extends JpaRepository<SupervisorEntity, Long> {
    SupervisorEntity findByEmailAndPassword(String email, String password);
    SupervisorEntity findByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "UPDATE supervisor_entity SET password = ?1 WHERE email = ?2", nativeQuery = true)
    void updatePassword(String password, String email);
}