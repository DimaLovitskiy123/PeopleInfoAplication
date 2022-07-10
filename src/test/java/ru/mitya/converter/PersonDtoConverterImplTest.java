package ru.mitya.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mitya.dto.PersonDto;
import ru.mitya.model.Person;

import java.time.LocalDate;

class PersonDtoConverterImplTest {

    @Test
    void fromDtoConvert() {
        PersonDtoConverter personDtoConverter = new PersonDtoConverterImpl();
        String dateString = "20.9.2009";
        PersonDto personDto = new PersonDto("a", "b", dateString);
        LocalDate date = LocalDate.of(2009, 9, 20);
        Assertions.assertEquals(date, personDtoConverter.fromDto(personDto).getDateOfBirth());
    }

    @Test
    void toDtoConvert() {
        PersonDtoConverter personDtoConverter = new PersonDtoConverterImpl();
        Person person = new Person("a", "b", LocalDate.of(2009, 9, 20));
        Assertions.assertEquals("20.9.2009", personDtoConverter.toDto(person).getDateOfBirth());
    }
}