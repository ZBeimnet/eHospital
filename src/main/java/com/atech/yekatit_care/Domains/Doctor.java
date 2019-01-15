package com.atech.yekatit_care.Domains;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doctor_id")
    private int doctor_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_user_id")
    private User userDoctor;

}
