package com.atech.yekatit_care.Repositories;

import com.atech.yekatit_care.Domains.LabTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabTestRepository extends CrudRepository<LabTest, Integer > {
//    List<LabTest> findAllByDoctor_email(String doctor_email);
}
