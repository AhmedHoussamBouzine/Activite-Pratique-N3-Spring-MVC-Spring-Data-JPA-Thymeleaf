package com.ahmedhoussambouzine.ap3mvc.repository;

import com.ahmedhoussambouzine.ap3mvc.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContains(String nom, Pageable pageable);
    Patient findByNom(String nom);
    @Query("select p from Patient p where p.id = :id")
    Patient search(@Param("id")long id);
}
