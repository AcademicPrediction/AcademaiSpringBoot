package com.tp2.academaispringboot.supervisor.model;

import com.tp2.academaispringboot.administrator.model.AdministratorEntity;
import com.tp2.academaispringboot.prediction.model.PredictionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "supervisor_entity")
public class SupervisorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "dni", length = 8, nullable = false)
    private String dni;

    @Column(name = "phone_number", length = 9, nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "supervisorEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PredictionEntity> predictions = new ArrayList<>();

}