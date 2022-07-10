package ru.mitya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mitya.converter.PersonDtoConverter;
import ru.mitya.dto.PersonDto;
import ru.mitya.model.Person;
import ru.mitya.service.PeopleService;

import java.util.Collection;
import java.util.LinkedList;

@RestController
public class PeopleController {
    private PeopleService peopleService;
    private PersonDtoConverter personDtoConverter;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonDtoConverter personDtoConverter) {
        this.peopleService = peopleService;
        this.personDtoConverter = personDtoConverter;
    }

    @GetMapping("/people/findAll")
    public Collection<PersonDto> findAll() {
        Collection<PersonDto> result = new LinkedList();
        for (Person person: peopleService.findAll()){
            result.add(personDtoConverter.toDto(person));
        }
        return result;
    }

    @PostMapping("/people/save")
    public PersonDto save(@RequestBody PersonDto personDto){
        Person person = personDtoConverter.fromDto(personDto);
        Person savedPerson = peopleService.save(person);
        PersonDto personDtoResult = personDtoConverter.toDto(savedPerson);
        return personDtoResult;
    }

    @GetMapping("/people/{id}")
    public PersonDto findById(@PathVariable(name = "id") int id){
        return personDtoConverter.toDto(peopleService.findById(id));
    }

    @DeleteMapping("/people/{id}")
    public PersonDto deleteById(@PathVariable(name = "id") int id){
        return personDtoConverter.toDto(peopleService.deleteById(id));
    }

    @PutMapping("/people/{id}")
    public PersonDto update(@PathVariable(name = "id") int id,
                         @RequestBody PersonDto personDto){
        return personDtoConverter.toDto(peopleService.update(id, personDtoConverter.fromDto(personDto)));
    }
}
