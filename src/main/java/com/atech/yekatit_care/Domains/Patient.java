package com.atech.yekatit_care.Domains;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide your last name")
    private String name;

    @Column(name = "age")
    @NotNull(message = "*Please provide age")
    private int age;

    @Column(name = "gender")
    @NotNull(message = "*Please select gender")
    private String gender;

    @Column(name = "address")
    @NotNull(message = "*Please provide your address")
    private String address;

    @Column(name = "phone_no")
    @NotNull(message = "*Please provide your phone_no")
    private String phone_no;

    @Column(name = "date")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_history_id")
    private PatientHistory patientHistory;

    @Column(name="assignedDoctor")
    private String assignedDoctor;
}
