package com.example.patrickaraujo.certification_nlw.modules.studants.dtos;

import com.example.patrickaraujo.certification_nlw.modules.questions.DTO.QuestionAnswersDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCertificationAwersDTO {
    private String email;
    private String technology;
    private List<QuestionAnswersDTO> questions;
}
