package com.example.patrickaraujo.certification_nlw.modules.questions.DTO;

import com.example.patrickaraujo.certification_nlw.modules.questions.entities.AlternativeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionAlternativeResult {
    private UUID id;
    private String technology;
    private String description;
    private List<AlternativeResultDTO> alternatives;
}
