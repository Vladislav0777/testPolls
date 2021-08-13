package com.example.demo.repository.answer;

import com.example.demo.model.entity.Answer;
import java.util.List;

public interface AnswerRepository {

    List<Answer> getAllAnswers();

    void createOrUpdateAnswer(Answer answer);

    Answer getAnswer(int id);

    void deleteAnswer(int id);
}
