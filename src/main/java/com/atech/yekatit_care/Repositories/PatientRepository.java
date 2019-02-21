package com.atech.yekatit_care.Repositories;

import com.atech.yekatit_care.Domains.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {
    Patient findById(int id);
}
