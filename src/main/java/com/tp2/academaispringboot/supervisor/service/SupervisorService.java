package com.tp2.academaispringboot.supervisor.service;

import com.tp2.academaispringboot.supervisor.model.SupervisorEntity;

import java.util.List;

public interface SupervisorService {
    SupervisorEntity createSupervisor(SupervisorEntity supervisorEntity);
    SupervisorEntity updateSupervisor(Long id, SupervisorEntity supervisorEntity);
    SupervisorEntity getSupervisorById(Long id);
    List<SupervisorEntity> getAllSupervisors();
    SupervisorEntity loginSupervisor(String email, String password);

    void deleteSupervisor(Long id);
}
