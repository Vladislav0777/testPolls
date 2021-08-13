package com.example.demo.controller;

import com.example.demo.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/main")
public class UserController {

    private final PersonService personService;

    @GetMapping("/users")
    public List<PersonDTO> getPersonList() {
        return personService.getAllPersons().stream()
                .map(PersonMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public PersonDTO getPerson(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        User user = personService.get(id);
        return PersonMapper.INSTANCE.toDTO(user);
    }

    @PostMapping("/users")
    public PersonDTO createPerson(@Valid @RequestBody User user, BindingResult br) {
        br.getAllErrors().forEach(System.out::println);
        personService.createOrUpdatePerson(user);
        return PersonMapper.INSTANCE.toDTO(user);
    }

    @PutMapping("/users")
    public PersonDTO updatePerson(@Valid @RequestBody User user, BindingResult br) {
        personService.createOrUpdatePerson(user);
        return PersonMapper.INSTANCE.toDTO(user);
    }

    @DeleteMapping("/users/{id}")
    public String deletePerson(@PathVariable int id) {
        throwExceptionIfEmpty(id);
        personService.deletePerson(id);
        return String.format("deleted", id);
    }

    private void throwExceptionIfEmpty(@PathVariable int id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NoSuchEntityException(
                    String.format("no_user", id));
        }
    }
}
