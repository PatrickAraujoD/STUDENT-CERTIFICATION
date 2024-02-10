package com.example.patrickaraujo.certification_nlw.modules.studants.useCase;

import com.example.patrickaraujo.certification_nlw.CertificationNlwApplication;
import com.example.patrickaraujo.certification_nlw.modules.studants.dtos.VerifyHasCertificationDTO;
import com.example.patrickaraujo.certification_nlw.modules.studants.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasCertification {

    @Autowired
    private CertificationStudentRepository repository;
    public boolean execute(VerifyHasCertificationDTO verifyHasCertificationDTO){
       var result = this.repository.FindByStudentEmailAndTechonology(verifyHasCertificationDTO.getEmail(), verifyHasCertificationDTO.getTechnology());
        System.out.println(result);
       if (result.isEmpty()){
           return true;
       }

       return false;
    }
}
