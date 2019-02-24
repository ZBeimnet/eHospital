package com.atech.yekatit_care.Domains;

import lombok.Data;
import javax.persistence.*;
@Data
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id")
    private int admin_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_user_id")
    private User userAdmin;
}
