package ru.mitya.service;

import ru.mitya.model.Person;

import java.util.List;

public interface PeopleService {
    void save(Person person);
    List<Person> findAll();
}
