package com.example.patrickaraujo.certification_nlw.modules.studants.repositories;

import com.example.patrickaraujo.certification_nlw.modules.studants.entities.StudantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudantEntity, UUID> {
    Optional<StudantEntity> findByEmail(String email);
}
