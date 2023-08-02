package com.tp2.academaispringboot.supervisor.mapping;

import com.tp2.academaispringboot.supervisor.dto.CreateSupervisorResource;
import com.tp2.academaispringboot.supervisor.dto.SupervisorResource;
import com.tp2.academaispringboot.supervisor.dto.UpdateSupervisorResource;
import com.tp2.academaispringboot.supervisor.model.SupervisorEntity;
import com.tp2.academaispringboot.util.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class SupervisorMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper enhancedModelMapper;

    public SupervisorResource toResource(SupervisorEntity supervisorEntity) {
        return enhancedModelMapper.map(supervisorEntity, SupervisorResource.class);
    }

    public List<SupervisorResource> toResource(List<SupervisorEntity> supervisorEntities) {
        return enhancedModelMapper.mapList(supervisorEntities, SupervisorResource.class);
    }

    public SupervisorEntity toEntity(CreateSupervisorResource createSupervisorResource) {
        return enhancedModelMapper.map(createSupervisorResource, SupervisorEntity.class);
    }

    public SupervisorEntity toEntity(UpdateSupervisorResource updateSupervisorResource) {
        return enhancedModelMapper.map(updateSupervisorResource, SupervisorEntity.class);
    }

}
