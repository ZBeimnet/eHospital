package com.atech.yekatit_care.Repositories;

import com.atech.yekatit_care.Domains.LabTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabTestRepository extends CrudRepository<LabTest, Integer > {
}
