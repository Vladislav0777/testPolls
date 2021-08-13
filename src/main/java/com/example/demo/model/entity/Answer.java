package com.example.demo.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "answer")
@RequiredArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false)
    User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_poll", nullable = false)
    Poll poll;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_question", nullable = false)
    Question question;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "answers_answer_variants",
            joinColumns = @JoinColumn(name = "answer_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "answer_variant_id"))
    List<AnswersType> answersTypeList;
}
