package com.example.patrickaraujo.certification_nlw.modules.certifications.controllers;

import com.example.patrickaraujo.certification_nlw.modules.certifications.useCase.RankingUseCase;
import com.example.patrickaraujo.certification_nlw.modules.studants.entities.CertificationStudantEntity;
import com.example.patrickaraujo.certification_nlw.modules.studants.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {
    private final RankingUseCase rankingUseCase;

    @Autowired
    public RankingController(RankingUseCase rankingUseCase, CertificationStudentRepository certificationStudentRepository){
        this.rankingUseCase = rankingUseCase;
    }

    @GetMapping("/top10")
    public List<CertificationStudantEntity> top10(){
        return this.rankingUseCase.execute();
    }
}
