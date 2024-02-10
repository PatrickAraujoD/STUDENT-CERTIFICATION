package com.example.patrickaraujo.certification_nlw.modules.studants.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name="certifications")
public class CertificationStudantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name= "student_id")
    private UUID studentId;

    @Column(length = 100)
    private String technology;

    @Column(length = 10)
    private int grate;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudantEntity studant;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_certification_id", insertable = false, updatable = false)
    @JsonManagedReference
    private List<AnswarsCertification> answarsCertifications;

    @CreationTimestamp()
    private LocalDateTime createdAt;
}
