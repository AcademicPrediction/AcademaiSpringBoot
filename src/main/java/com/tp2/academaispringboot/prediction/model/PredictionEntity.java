package com.tp2.academaispringboot.prediction.model;

import com.tp2.academaispringboot.supervisor.model.SupervisorEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "prediction_entity")
public class PredictionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //filename
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    //fecha
    @Column(name = "date", length = 50, nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "supervisor_entity_id")
    private SupervisorEntity supervisorEntity;

}