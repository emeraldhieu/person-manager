package com.emeraldhieu.personmanager.app.person;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person-manager/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> listPersons() {
        return personService.getPersons();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO createPerson(@RequestBody PersonDTO body) {
        return personService.createPerson(body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable String id) {
        personService.deletePerson(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO getPerson(@PathVariable String id) {
        return personService.getPerson(id);
    }
}
