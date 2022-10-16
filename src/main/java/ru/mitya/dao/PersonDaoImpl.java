package ru.mitya.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mitya.model.Person;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

@Component("postgres")
public class PersonDaoImpl implements PersonDao {
    private static final String FIND_BY_ID = "select * from person where id = ?";
    private static final String SAVE = "INSERT INTO person(first_name, last_name, date_of_birth, age)" +
            " values(?, ?, ?, ?);";
    private static final String DELETE_BY_ID = "delete from person where id = ?;";
    private static final String UPDATE_BY_ID = "update person set first_name = ?," +
            " last_name = ?, date_of_birth = ?, age = ? where id = ?";

    @Autowired
    private DataSource dataSource;

    @Override
    public Person save(Person person) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(person.getDateOfBirth()));
            preparedStatement.setInt(4, person.getAge());
            preparedStatement.executeUpdate();
            ResultSet keySet = preparedStatement.getGeneratedKeys();
            if (keySet.next()) {
                person.setId(keySet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    @Override
    public Collection<Person> findAll() {
        Collection<Person> persons = new LinkedList();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from person;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                persons.add(createPerson(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    @Override
    public Person findById(int id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createPerson(resultSet);
            } else {
                throw new RuntimeException("Пользователь не найден");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person update(int id, Person person) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setDate(3, java.sql.Date.valueOf(person.getDateOfBirth()));
            preparedStatement.setInt(4, person.getAge());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    @Override
    public void deleteAll() {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from person;");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Person createPerson(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setFirstName(resultSet.getString("first_name"));
        person.setLastName(resultSet.getString("last_name"));
        person.setAge(resultSet.getInt("age"));
        person.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
        return person;
    }
}
