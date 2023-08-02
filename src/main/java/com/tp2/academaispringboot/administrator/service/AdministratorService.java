package com.tp2.academaispringboot.administrator.service;

import com.tp2.academaispringboot.administrator.dto.AdministratorResource;
import com.tp2.academaispringboot.administrator.dto.CreateAdministratorResource;
import com.tp2.academaispringboot.administrator.dto.UpdateAdministratorResource;
import com.tp2.academaispringboot.administrator.model.AdministratorEntity;

import java.util.List;

public interface AdministratorService {

    List<AdministratorEntity> getAllAdministrators();
    AdministratorEntity createAdministrator(AdministratorEntity administratorEntity);
    AdministratorEntity updateAdministrator(Long id, AdministratorEntity administratorEntity);
    AdministratorEntity loginAdministrator(String email, String password);

}
