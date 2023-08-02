package com.tp2.academaispringboot.administrator.mapping;

import com.tp2.academaispringboot.administrator.dto.AdministratorResource;
import com.tp2.academaispringboot.administrator.dto.CreateAdministratorResource;
import com.tp2.academaispringboot.administrator.dto.UpdateAdministratorResource;
import com.tp2.academaispringboot.administrator.model.AdministratorEntity;
import com.tp2.academaispringboot.util.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class AdministratorMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper modelMapper;

    public AdministratorResource toResource(AdministratorEntity administrator) {
        return modelMapper.map(administrator, AdministratorResource.class);
    }

    public List<AdministratorResource> toResource(List<AdministratorEntity> administrators) {
        return modelMapper.mapList(administrators, AdministratorResource.class);
    }

    public AdministratorEntity toEntity(CreateAdministratorResource administrator) {
        return modelMapper.map(administrator, AdministratorEntity.class);
    }

    public AdministratorEntity toEntity(UpdateAdministratorResource administrator) {
        return modelMapper.map(administrator, AdministratorEntity.class);
    }
}
