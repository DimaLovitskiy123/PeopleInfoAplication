package ru.mitya.service;

import org.springframework.stereotype.Component;
import ru.mitya.dao.PersonDao;
import ru.mitya.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

@Component
public class PeopleServiceImpl implements PeopleService{

    private PersonDao dao;
    private Person person;
    Date date = new Date();

    @Override
    public void save(Person person, LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            person.getAge() = Period.between(birthDate, currentDate).getYears();
        } else {

        }
        dao.save(person);
    }

    @Override
    public List<Person> findAll() {
        return dao.findAll();
    }
}

//TODO: код для вычисления возраста
//TODO: сделать контроллер и поставить аннотации для работы спринга
//TODO: к пятнице