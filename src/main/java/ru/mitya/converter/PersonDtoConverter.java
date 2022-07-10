package ru.mitya.converter;

import ru.mitya.dto.PersonDto;
import ru.mitya.model.Person;

public interface PersonDtoConverter {
    Person fromDto(PersonDto personDto);
    PersonDto toDto(Person person);
}
