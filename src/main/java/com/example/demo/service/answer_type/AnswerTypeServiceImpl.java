package com.example.demo.service.answer_type;

import com.example.demo.model.entity.AnswersType;
import com.example.demo.model.entity.Poll;
import com.example.demo.model.entity.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class AnswerTypeServiceImpl extends AnswersType {

    private static final String NO_SUCH_POLL = "No such poll with ID=%s in database";
    private static final String NO_SUCH_QUESTION = "No such question with ID=%s in database";
    private static final String NO_SUCH_VARIANT = "No such answer variant with ID=%s in database";

    private final PollRepository pollRepository;
    private final QuestionsRepository questionsRepository;
    private final AnswerTypeRepository answerTypeRepository;

    @Override
    @Transactional
    public List<AnswersType> getAnswerVariantList(int pollId, int questionId) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, questionId);
        return answerTypeRepository.getAnswerTypeList(pollId, questionId);
    }

    @Override
    @Transactional
    public AnswersType getAnswerType(int pollId, int questionId, int id) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, questionId);
        throwExceptionIfAnswerVariantEmpty(pollId, questionId, id);
        return answerTypeRepository
                .getAnswerType(pollId, questionId, id);
    }

    @Override
    @Transactional
    public void createAnswerVariant(int pollId, int questionId, AnswerVariant answerVariant) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, questionId);
        answerVariant.setQuestion(questionsRepository.getQuestion(pollId, questionId));
        answerVariantRepository.createOrUpdateAnswerVariant(answerVariant);
    }

    @Override
    @Transactional
    public void updateAnswerVariant(int pollId, int questionId, AnswerVariant answerVariant) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, questionId);
        throwExceptionIfAnswerVariantEmpty(pollId, questionId, answerVariant.getId());
        answerVariant.setQuestion(questionsRepository.getQuestion(pollId, questionId));
        answerVariantRepository.createOrUpdateAnswerVariant(answerVariant);
    }

    @Override
    @Transactional
    public void deleteAnswerVariant(int pollId, int questionId, int id) {
        throwExceptionIfPollEmpty(pollId);
        throwExceptionIfQuestionEmpty(pollId, questionId);
        throwExceptionIfAnswerVariantEmpty(pollId, questionId, id);
        answerVariantRepository.deleteAnswerVariant(questionId, id);
    }

    private void throwExceptionIfPollEmpty(int pollId) {
        Poll poll = pollRepository.getPoll(pollId);
        if (poll == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_POLL, pollId));
        }
    }

    private void throwExceptionIfQuestionEmpty(int pollId, int questionId) {
        Question question = questionsRepository.getQuestion(pollId, questionId);
        if (question == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_QUESTION, questionId));
        }
    }

    private void throwExceptionIfAnswerVariantEmpty(int pollId, int questionId, int id) {
        AnswersType answerVariant = answerVariantRepository
                .getAnswerVariant(pollId, questionId, id);
        if (answerVariant == null) {
            throw new NoSuchEntityException(
                    String.format(NO_SUCH_VARIANT, id));
        }
    }
}
