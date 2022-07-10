package ru.mitya.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mitya.TestDataProvider;
import ru.mitya.model.Person;

import java.time.LocalDate;
import java.time.Period;

@SpringBootTest
class PeopleServiceTest {

    @Autowired
    private PeopleService peopleService;

    @Test
    void saveAndFindAllTest(){
        Person createdPerson = TestDataProvider.createPerson();
        peopleService.save(createdPerson);
        Assertions.assertEquals(1, peopleService.findAll().size());
        Assertions.assertEquals(12, createdPerson.getAge());
    }

    @Test
    void test(){

    }

    @Test
    void updateTest(){
        int id = peopleService.save(TestDataProvider.createPerson()).getId();
        Person foundPerson = peopleService.findById(id);
        LocalDate newDateOfBirth = LocalDate.of(2000, 9, 20);
        foundPerson.setDateOfBirth(newDateOfBirth);
        Person updatedPerson = peopleService.update(id, foundPerson);
        Assertions.assertEquals(Period.between(newDateOfBirth, LocalDate.now()).getYears(), updatedPerson.getAge());
    }

    @Test
    void findByIdTest(){
        Person personToSave = TestDataProvider.createPerson();
        Person savedPerson = peopleService.save(personToSave);
        Assertions.assertEquals(personToSave, peopleService.findById(savedPerson.getId()));
    }

    @Test
    void deleteByIdTest(){
        peopleService.save(TestDataProvider.createPerson());
        peopleService.save(TestDataProvider.createPerson());
        peopleService.deleteById(1);
        Assertions.assertEquals(1, peopleService.findAll().size());
    }
}