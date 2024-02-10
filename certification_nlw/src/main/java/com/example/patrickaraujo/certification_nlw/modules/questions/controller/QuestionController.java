package com.example.patrickaraujo.certification_nlw.modules.questions.controller;

import com.example.patrickaraujo.certification_nlw.modules.questions.DTO.AlternativeResultDTO;
import com.example.patrickaraujo.certification_nlw.modules.questions.DTO.QuestionAlternativeResult;
import com.example.patrickaraujo.certification_nlw.modules.questions.entities.AlternativeEntity;
import com.example.patrickaraujo.certification_nlw.modules.questions.entities.QuestionEntity;
import com.example.patrickaraujo.certification_nlw.modules.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionRepository repository;

    @Autowired
    public QuestionController(QuestionRepository questionRepository){
        this.repository = questionRepository;
    }


    @GetMapping("/technology/{technology}")
    public List<QuestionAlternativeResult> findByQuestion(@PathVariable String technology) {
        var result =  this.repository.findByTechonology(technology);

        return result.stream().map(QuestionController::mapQuestionToDTO).collect(Collectors.toList());
    }

    static QuestionAlternativeResult mapQuestionToDTO(QuestionEntity questions){
        var questionResultDTO = QuestionAlternativeResult.builder().id(questions.getId()).technology(questions.getTechonology()).description(questions.getDescription()).build();

        List<AlternativeResultDTO> alternativeResultDTOS = questions.getAlternatives().stream().map(QuestionController::mapAlternativeDTO).collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativeResultDTOS);
        return questionResultDTO;
    }

    static AlternativeResultDTO mapAlternativeDTO(AlternativeEntity alternativeEntity){
        return AlternativeResultDTO.builder().id(alternativeEntity.getId()).description(alternativeEntity.getDescription()).build();
    }
}
