package com.atech.yekatit_care.Domains;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "receptionist")
public class Receptionist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "receptionist_id")
    private int receptionist_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receptionist_user_id")
    private User userReceptionist;
}
