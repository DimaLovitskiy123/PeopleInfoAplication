package ru.mitya.converter;

import org.springframework.stereotype.Component;
import ru.mitya.dto.PersonDto;
import ru.mitya.model.Person;

import java.time.LocalDate;

@Component
public class PersonDtoConverterImpl implements PersonDtoConverter{

    @Override
    public Person fromDto(PersonDto personDto) {
        Person person = new Person();
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        String dateOfBirthAsString = personDto.getDateOfBirth();
        person.setDateOfBirth(convertStringToLocalDate(dateOfBirthAsString));
        return person;
    }

    @Override
    public PersonDto toDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setFirstName(person.getFirstName());
        personDto.setLastName(person.getLastName());
        personDto.setDateOfBirth(convertLocalDateToString(person.getDateOfBirth()));
        personDto.setAge(person.getAge());
        personDto.setId(person.getId());
        return personDto;
    }

    private LocalDate convertStringToLocalDate(String dateAsString){
        String[] dateArray = dateAsString.split("\\.");
        return LocalDate.of(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[0]));
    }

    private String convertLocalDateToString(LocalDate date){
        String pattern = "%d.%d.%d";
        return String.format(pattern, date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }
}
