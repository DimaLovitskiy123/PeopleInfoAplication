package ru.mitya.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mitya.TestDataProvider;
import ru.mitya.model.Person;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class PersonDaoTest {

    @Autowired
    private PersonDao personDao;

    @AfterEach
    void deleteAll(){
        personDao.deleteAll();
    }

    @Test
    void saveAndFindTest(){
        Person personToSave = TestDataProvider.createPerson();
        int id = personDao.save(personToSave).getId();
        Person foundPerson = personDao.findById(id);
        Assertions.assertEquals(personToSave, foundPerson);
        Assertions.assertEquals(1, personDao.findAll().size());
    }

    @Test
    void test(){

    }

    @Test
    void updateTest(){
        int id = personDao.save(TestDataProvider.createPerson()).getId();
        Person foundPerson = personDao.findById(id);
        String newName = "Вася";
        foundPerson.setFirstName(newName);
        Person updatedPerson = personDao.update(id, foundPerson);
        Assertions.assertEquals(newName, updatedPerson.getFirstName());
    }

    @Test
    void findByIdTest(){
        Person personToSave = TestDataProvider.createPerson();
        Person savedPerson = personDao.save(personToSave);
        Assertions.assertEquals(personToSave, personDao.findById(savedPerson.getId()));
    }

    @Test
    void deleteByIdTest(){
        personDao.save(TestDataProvider.createPerson());
        personDao.save(TestDataProvider.createPerson());
        personDao.deleteById(1);
        Assertions.assertEquals(1, personDao.findAll().size());
    }
}