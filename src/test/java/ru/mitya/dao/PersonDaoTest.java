package ru.mitya.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mitya.TestDataProvider;
import ru.mitya.dbutil.DatabaseConnector;
import ru.mitya.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootTest
@ActiveProfiles("test")
class PersonDaoTest {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private DatabaseConnector connectorH2;

    @AfterEach
    void deleteAll(){
        personDao.deleteAll();
    }

    @BeforeEach
    public void createTable(){
        try {
            Connection connection = connectorH2.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("create table person(\n" +
                    "id serial primary key,\n" +
                    "first_name text,\n" +
                    "last_name text,\n" +
                    "age integer,\n" +
                    "date_of_birth date\n" +
                    ");");
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        Person person1 = personDao.save(TestDataProvider.createPerson());
        Person person2 = personDao.save(TestDataProvider.createPerson());
        personDao.deleteById(person2.getId());
        Assertions.assertEquals(1, personDao.findAll().size());
    }
}