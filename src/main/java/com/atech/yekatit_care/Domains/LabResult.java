package com.atech.yekatit_care.Domains;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "lab_result")
public class LabResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "result_id")
    private int result_id;

    @Column(name = "testRequest_id")
    private String testRequest_id;

    @Column(name = "labTechnician_name")
    private String labTechnician_name;

    @Column(name = "lab_result")
    @NotEmpty(message = "*Please provide your lab results")
    private String lab_result;
}
