package com.example.demo.model.entity;

import com.example.demo.model.enums.QuestionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "questions")
@RequiredArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Enumerated(EnumType.ORDINAL)
    @NotBlank
    QuestionType questionType;

    @Column(name = "name", nullable = false)
    @Size(min = 4, max = 255, message = "5-255 symbols")
    @NotBlank
    String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    List<AnswersType> answersTypeList;
}
