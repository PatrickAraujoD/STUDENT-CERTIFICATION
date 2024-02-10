package com.example.patrickaraujo.certification_nlw.modules.studants.repositories;

import com.example.patrickaraujo.certification_nlw.modules.studants.entities.CertificationStudantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CertificationStudentRepository extends JpaRepository<CertificationStudantEntity, UUID> {
    @Query("SELECT c FROM certifications c INNER JOIN c.studant WHERE studant.email = :email AND  c.technology = :technology")
    List<CertificationStudantEntity> FindByStudentEmailAndTechonology(String email, String technology);

    @Query("SELECT c FROM certifications c ORDER BY c.grate DESC LIMIT 10")
    List<CertificationStudantEntity> findTop10ByOrderByGrateDesc();
}
