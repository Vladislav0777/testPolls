package com.example.demo.controller;

import com.example.demo.model.entity.Answer;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/main")
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/answers")
    public List<AnswerDTO> getAnswerList() {
        return answerService.getAllAnswers().stream()
                .map(AnswerMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/answers/{id}")
    public AnswerDTO getAnswer(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        Answer answer = answerService.getAnswer(id);
        return AnswerMapper.INSTANCE.toDTO(answer);
    }

    @PostMapping("/answers")
    public AnswerDTO createAnswer(
            @Valid @RequestBody Answer answer, BindingResult br) {
        br.getAllErrors().forEach(System.out::println);
        answerService.createOrUpdateAnswer(answer);
        return AnswerMapper.INSTANCE.toDTO(answer);
    }

    @PutMapping("/answers")
    public AnswerDTO updateAnswer(
            @Valid @RequestBody Answer answer, BindingResult br) {
        throwExceptionIfEmpty(answer.getId());
        answerService.createOrUpdateAnswer(answer);
        return AnswerMapper.INSTANCE.toDTO(answer);
    }

    @DeleteMapping("/answers/{id}")
    public String deleteAnswer(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        answerService.deleteAnswer(id);
        return String.format("deleted", id);
    }

    private void throwExceptionIfEmpty(@PathVariable int id) {
        Answer answer = answerService.getAnswer(id);
        if (answer == null) {
            throw new NoSuchEntityException(
                    String.format("no_types", id));
        }
    }
}
