package com.example.demo.service.answer_type;

import com.example.demo.model.entity.AnswersType;
import java.util.List;

public interface AnswerTypeService {

    List<AnswersType> getAnswerTypeList(int pollId, int questionId);

    AnswersType getAnswerType(int pollId, int questionId, int id);

    void createAnswerType(int pollId, int questionId, AnswersType answerType);

    void updateAnswerType(int pollId, int questionId, AnswersType answerType);

    void deleteAnswerType(int pollId, int questionId, int id);
}
