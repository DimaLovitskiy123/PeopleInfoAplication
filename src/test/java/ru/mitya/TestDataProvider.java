package ru.mitya;

import ru.mitya.model.Person;

import java.time.LocalDate;

public class TestDataProvider {

    public static Person createPerson(){
        return new Person("Dima", "Lovitskiy", LocalDate.of(2009, 9, 20));
    }
}
