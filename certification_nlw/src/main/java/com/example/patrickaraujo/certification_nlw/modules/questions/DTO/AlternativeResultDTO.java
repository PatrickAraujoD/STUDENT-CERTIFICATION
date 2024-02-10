package com.example.patrickaraujo.certification_nlw.modules.questions.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlternativeResultDTO{
    private UUID id;
    private String description;
}
