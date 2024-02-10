package com.example.patrickaraujo.certification_nlw.modules.studants.controller;

import com.example.patrickaraujo.certification_nlw.modules.studants.dtos.StudentCertificationAwersDTO;
import com.example.patrickaraujo.certification_nlw.modules.studants.dtos.VerifyHasCertificationDTO;
import com.example.patrickaraujo.certification_nlw.modules.studants.entities.CertificationStudantEntity;
import com.example.patrickaraujo.certification_nlw.modules.studants.useCase.StudentCertificationAnswersUseCase;
import com.example.patrickaraujo.certification_nlw.modules.studants.useCase.VerifyIfHasCertification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final VerifyIfHasCertification verifyIfHasCertification;
    private final StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @Autowired
    public StudentController(VerifyIfHasCertification verifyIfHasCertification, StudentCertificationAnswersUseCase studentCertificationAnswersUseCase){
        this.verifyIfHasCertification = verifyIfHasCertification;
        this.studentCertificationAnswersUseCase = studentCertificationAnswersUseCase;
    }

    public boolean verifyHasCertification(VerifyHasCertificationDTO verifyHasCertificationDTO){
        return this.verifyIfHasCertification.execute(verifyHasCertificationDTO);
    }

    @PostMapping("certification/answer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAwersDTO studentCertificationAwersDTO,
                                              VerifyHasCertificationDTO verifyHasCertificationDTO){

        verifyHasCertificationDTO.setEmail(studentCertificationAwersDTO.getEmail());
        verifyHasCertificationDTO.setTechnology(studentCertificationAwersDTO.getTechnology());

        try {
            boolean hasCertification = this.verifyHasCertification(verifyHasCertificationDTO);

            if (!hasCertification) {
                throw new Exception("Você ja possui um certificado!");
            }

            var result = this.studentCertificationAnswersUseCase.execute(studentCertificationAwersDTO);

            return ResponseEntity.ok().body(result);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
