package com.tp2.academaispringboot.administrator.repository;

import com.tp2.academaispringboot.administrator.model.AdministratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorEntityRepository extends JpaRepository<AdministratorEntity, Long> {
    AdministratorEntity findByEmail(String email);
    AdministratorEntity findByEmailAndPassword(String email, String password);
}