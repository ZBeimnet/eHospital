package com.atech.yekatit_care.Repositories;

import com.atech.yekatit_care.Domains.LabResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabResultRepository extends CrudRepository<LabResult, Integer> {
}
