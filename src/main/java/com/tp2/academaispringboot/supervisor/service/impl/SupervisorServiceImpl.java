package com.tp2.academaispringboot.supervisor.service.impl;

import com.tp2.academaispringboot.supervisor.model.SupervisorEntity;
import com.tp2.academaispringboot.supervisor.repository.SupervisorEntityRepository;
import com.tp2.academaispringboot.supervisor.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisorServiceImpl implements SupervisorService {

    @Autowired
    private SupervisorEntityRepository supervisorEntityRepository;
    @Override
    public SupervisorEntity createSupervisor(SupervisorEntity supervisorEntity) {
        return supervisorEntityRepository.save(supervisorEntity);
    }

    @Override
    public SupervisorEntity updateSupervisor(Long id, SupervisorEntity supervisorEntity) {
        SupervisorEntity supervisorEntityDB = supervisorEntityRepository.findById(id).orElseThrow();
        supervisorEntityDB.setName(supervisorEntity.getName());
        supervisorEntityDB.setLastName(supervisorEntity.getLastName());
        supervisorEntityDB.setEmail(supervisorEntity.getEmail());
        supervisorEntityDB.setDni(supervisorEntity.getDni());
        supervisorEntityDB.setPhoneNumber(supervisorEntity.getPhoneNumber());
        supervisorEntityDB.setEmail(supervisorEntity.getEmail());
        return supervisorEntityRepository.save(supervisorEntityDB);
    }

    @Override
    public SupervisorEntity getSupervisorById(Long id) {
        return supervisorEntityRepository.findById(id).orElseThrow();
    }

    @Override
    public List<SupervisorEntity> getAllSupervisors() {
        return supervisorEntityRepository.findAll();
    }

    @Override
    public SupervisorEntity loginSupervisor(String email, String password) {
        return supervisorEntityRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public void deleteSupervisor(Long id) {
        supervisorEntityRepository.deleteById(id);
    }


}
