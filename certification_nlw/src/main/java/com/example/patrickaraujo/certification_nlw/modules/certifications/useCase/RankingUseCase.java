package com.example.patrickaraujo.certification_nlw.modules.certifications.useCase;

import com.example.patrickaraujo.certification_nlw.modules.studants.entities.CertificationStudantEntity;
import com.example.patrickaraujo.certification_nlw.modules.studants.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingUseCase {

    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    public RankingUseCase(CertificationStudentRepository certificationStudentRepository) {
        this.certificationStudentRepository = certificationStudentRepository;
    }

    public List<CertificationStudantEntity> execute(){
        return this.certificationStudentRepository.findTop10ByOrderByGrateDesc();
    }
}
