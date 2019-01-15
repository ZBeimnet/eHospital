package com.atech.yekatit_care.Domains;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lab_technician")
public class LabTechnician {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "labTechnician_id")
    private int labTechnician_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "labTechnician_user_id")
    private User userLabTechnician;

}
