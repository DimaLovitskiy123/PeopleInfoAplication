package ru.mitya.dao;

import ru.mitya.model.Person;

import java.util.Collection;

public interface PersonDao {
    Person save(Person person);
    Collection<Person> findAll();
    Person findById(int id);
    void deleteById(int id);
    Person update(int id, Person person);
    void deleteAll();
}
