package ru.mitya.dao;

import ru.mitya.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements   PersonDao{

    private List<Person> list = new ArrayList();

    @Override
    public void save(Person person) {
        list.add(person);
    }

    @Override
    public List<Person> findAll() {
        return list;
    }
}
