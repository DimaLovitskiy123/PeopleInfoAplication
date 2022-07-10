package ru.mitya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mitya.dao.PersonDao;
import ru.mitya.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;

//@Service = @Component
@Service
public class PeopleServiceImpl implements PeopleService{

    @Autowired
    private PersonDao dao;

    @Override
    public Person save(Person person) {
        person.setAge(calculateAge(person.getDateOfBirth()));
        return dao.save(person);
    }

    @Override
    public Person update(int id, Person person) {
        person.setAge(calculateAge(person.getDateOfBirth()));
        return dao.update(id, person);
    }

    @Override
    public Collection<Person> findAll() {
        return dao.findAll();
    }

    @Override
    public Person findById(int id) {
        return dao.findById(id);
    }

    @Override
    public Person deleteById(int id) {
        return dao.deleteById(id);
    }

    private int calculateAge(LocalDate dateOfBirth){
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}

//TODO: 1)доделать метод ToDto в Converter, онвертировать все 5 полей
//TODO: 2)написать тест для toDto