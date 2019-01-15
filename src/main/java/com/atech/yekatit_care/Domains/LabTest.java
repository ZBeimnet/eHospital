package com.atech.yekatit_care.Domains;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "lab_test")
public class LabTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "test_id")
    private int test_id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "lab_request")
    @NotEmpty(message = "*Please provide your lab requests")
    private String lab_request;

}
