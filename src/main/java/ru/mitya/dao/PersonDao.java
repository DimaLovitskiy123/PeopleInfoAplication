package ru.mitya.dao;

import ru.mitya.model.Person;

import java.util.List;

public interface PersonDao {
    void save(Person person);
    List<Person> findAll();
}
