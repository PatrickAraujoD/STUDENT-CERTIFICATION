package com.example.patrickaraujo.certification_nlw.modules.studants.useCase;

import com.example.patrickaraujo.certification_nlw.modules.questions.entities.AlternativeEntity;
import com.example.patrickaraujo.certification_nlw.modules.questions.entities.QuestionEntity;
import com.example.patrickaraujo.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.example.patrickaraujo.certification_nlw.modules.studants.dtos.StudentCertificationAwersDTO;
import com.example.patrickaraujo.certification_nlw.modules.studants.dtos.VerifyHasCertificationDTO;
import com.example.patrickaraujo.certification_nlw.modules.studants.entities.AnswarsCertification;
import com.example.patrickaraujo.certification_nlw.modules.studants.entities.CertificationStudantEntity;
import com.example.patrickaraujo.certification_nlw.modules.studants.entities.StudantEntity;
import com.example.patrickaraujo.certification_nlw.modules.studants.repositories.CertificationStudentRepository;
import com.example.patrickaraujo.certification_nlw.modules.studants.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationAnswersUseCase {

    private final QuestionRepository questionRepository;
    private final StudentRepository studentRepository;
    private final CertificationStudentRepository certificationStudentRepository;

    @Autowired
    public StudentCertificationAnswersUseCase(QuestionRepository questionRepository, StudentRepository studentRepository,
                                                CertificationStudentRepository certificationStudentRepository){
        this.questionRepository = questionRepository;
        this.studentRepository = studentRepository;
        this.certificationStudentRepository = certificationStudentRepository;
    }
    public CertificationStudantEntity execute(StudentCertificationAwersDTO studentCertificationAwersDTO){
        List<AnswarsCertification> answarsCertifications = new ArrayList<>();
        List<QuestionEntity> questions = this.questionRepository.findByTechonology(studentCertificationAwersDTO.getTechnology());
        AtomicInteger correctAnsewr = new AtomicInteger(0);
        studentCertificationAwersDTO.getQuestions()
                .stream()
                .forEach(questionAnsewr -> {
                    var question = questions.stream().filter(questionFilter -> questionFilter.getId().equals(questionAnsewr.getQuestionID())).findFirst().get();

                    var findAlternativeIsCorrect = question.getAlternatives().stream().filter(AlternativeEntity::isCorrect).findFirst().get();

                    if(findAlternativeIsCorrect.getId().equals(questionAnsewr.getAlternativeID())){
                        questionAnsewr.setCorrect(true);
                        correctAnsewr.incrementAndGet();
                    } else {
                        questionAnsewr.setCorrect(false);
                    }

                    var answars = AnswarsCertification.builder()
                            .answerId(questionAnsewr.getAlternativeID())
                            .questionId(questionAnsewr.getQuestionID())
                            .isCorrect(questionAnsewr.isCorrect())
                            .build();

                    answarsCertifications.add(answars);
                });

        var student = studentRepository.findByEmail(studentCertificationAwersDTO.getEmail());
        UUID studentID;
        if(student.isEmpty()){
            var studentCreate = StudantEntity.builder().email(studentCertificationAwersDTO.getEmail()).build();

            studentCreate = studentRepository.save(studentCreate);
            studentID = studentCreate.getId();
        } else {
            studentID = student.get().getId();
        }



        CertificationStudantEntity certificationStudantEntity = CertificationStudantEntity.builder()
                .technology(studentCertificationAwersDTO.getTechnology())
                .studentId(studentID)
                .grate(correctAnsewr.get())
                .build();

        certificationStudentRepository.save(certificationStudantEntity);

        answarsCertifications.stream().forEach(answarsCertification -> {
            answarsCertification.setCertificationId(certificationStudantEntity.getId());
            answarsCertification.setCertificationStudantEntity(certificationStudantEntity);
        });

        certificationStudantEntity.setAnswarsCertifications(answarsCertifications);

        return certificationStudentRepository.save(certificationStudantEntity);
    }
}
