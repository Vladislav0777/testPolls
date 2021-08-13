package com.example.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class QuestionController {

    private static final String DELETED = "Question with ID=%s is deleted";

    private final QuestionsService questionsService;

    @GetMapping("/polls/{pollId}/questions")
    public List<QuestionDTO> getQuestionList(@PathVariable int pollId) {
        return questionsService.getQuestionList(pollId).stream()
                .map(QuestionMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/polls/{pollId}/questions/{questionId}")
    public QuestionDTO getQuestion(
            @PathVariable int pollId, @PathVariable int questionId) {
        Question question = questionsService.getQuestion(pollId, questionId);
        return QuestionMapper.INSTANCE.toDTO(question);
    }

    @PostMapping("/polls/{pollId}/questions")
    public QuestionDTO createQuestion(
            @PathVariable int pollId, @RequestBody Question question) {
        questionsService.createQuestion(pollId, question);
        return QuestionMapper.INSTANCE.toDTO(question);
    }

    @PutMapping("/polls/{pollId}/questions")
    public QuestionDTO updateQuestion(
            @PathVariable int pollId, @RequestBody Question question) {
        questionsService.updateQuestion(pollId, question);
        return QuestionMapper.INSTANCE.toDTO(question);
    }

    @DeleteMapping("/polls/{pollId}/questions/{questionId}")
    public String deleteQuestion(
            @PathVariable int pollId, @PathVariable int questionId) {
        questionsService.deleteQuestion(pollId, questionId);
        return String.format(DELETED, questionId);
    }
}
© 20
