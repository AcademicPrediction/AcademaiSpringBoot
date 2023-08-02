package com.tp2.academaispringboot.administrator.service.impl;

import com.tp2.academaispringboot.administrator.dto.AdministratorResource;
import com.tp2.academaispringboot.administrator.dto.CreateAdministratorResource;
import com.tp2.academaispringboot.administrator.dto.UpdateAdministratorResource;
import com.tp2.academaispringboot.administrator.model.AdministratorEntity;
import com.tp2.academaispringboot.administrator.repository.AdministratorEntityRepository;
import com.tp2.academaispringboot.administrator.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorEntityRepository administratorEntityRepository;

    @Override
    public List<AdministratorEntity> getAllAdministrators() {
        return administratorEntityRepository.findAll();
    }

    @Override
    public AdministratorEntity createAdministrator(AdministratorEntity administratorEntity) {
        return administratorEntityRepository.save(administratorEntity);
    }

    @Override
    public AdministratorEntity updateAdministrator(Long id, AdministratorEntity administratorEntity) {
        AdministratorEntity administrator = administratorEntityRepository.findById(id).orElseThrow();
        administrator.setName(administratorEntity.getName());
        administrator.setLastName(administratorEntity.getLastName());
        administrator.setEmail(administratorEntity.getEmail());
        return administratorEntityRepository.save(administrator);
    }

    @Override
    public AdministratorEntity loginAdministrator(String email, String password) {
        return administratorEntityRepository.findByEmailAndPassword(email, password);
    }
}
