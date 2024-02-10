package com.example.patrickaraujo.certification_nlw.modules.studants.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyHasCertificationDTO {
    private String email;
    private String technology;
}
