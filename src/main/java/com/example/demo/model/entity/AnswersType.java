package com.example.demo.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "answer_type")
@RequiredArgsConstructor
public class AnswersType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "text", nullable = false)
    @NotBlank
    String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    Question question;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "answers_answer_variants",
            joinColumns = @JoinColumn(name = "answer_variant_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    List<Answer> answerList;
}
