package com.example.patrickaraujo.certification_nlw.modules.questions.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column()
    private String description;

    @Column()
    private String techonology;

    @OneToMany
    @JoinColumn(name = "questions_id")
    private List<AlternativeEntity> alternatives;

    @CreationTimestamp()
    private LocalDateTime createdAt;
}
