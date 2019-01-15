package com.atech.yekatit_care.Domains;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "patient_history")
public class PatientHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id")
    private int history_id;

    @Column(name = "doctor_name")
    private String doctor_name;

    @Column(name = "patient_history")
    @NotEmpty(message = "*Please provide patient history")
    private String name;


    @OneToOne(mappedBy = "patientHistory")
    private Patient patient;
}
