package ru.mitya.dao;

import org.springframework.stereotype.Repository;
import ru.mitya.model.Person;

import java.util.*;

//@Repository = @Component
@Repository
public class PersonDaoImpl implements PersonDao {

    private Map<Integer, Person> map = new HashMap();
    private int idCounter = 1;

    @Override
    public Person save(Person person) {
        person.setId(idCounter);
        map.put(idCounter, person);
        return map.get(idCounter++);
    }

    @Override
    public Person update(int id, Person person) {
        person.setAge(person.getAge());
        map.put(person.getAge(), person);
        person.setId(id);
        map.put(id, person);
        return map.get(id);
    }

    @Override
    public Collection<Person> findAll() {
        return map.values();
    }

    @Override
    public Person findById(int id) {
        return map.get(id);
    }

    @Override
    public Person deleteById(int id) {
        return map.remove(id);
    }

    @Override
    public void deleteAll() {
        map.clear();
    }
}
