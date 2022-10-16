package ru.mitya.service;

import ru.mitya.model.Person;

import java.util.Collection;

public interface PeopleService {
    Person save(Person person);
    Collection<Person> findAll();
    Person findById(int id);
    void deleteById(int id);
    Person update(int id, Person person);
}
