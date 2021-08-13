package com.example.demo.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email", referencedColumnName="username",
            foreignKey = @ForeignKey(name = "FK_persons_username"))
    User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Answer> answerList;
}
