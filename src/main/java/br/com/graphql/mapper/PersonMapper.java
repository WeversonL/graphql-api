package br.com.graphql.mapper;

import br.com.graphql.domain.dto.PersonDTO;
import br.com.graphql.domain.entity.person.Person;

public class PersonMapper {

    private PersonMapper() {
    }

    public static Person toPerson(PersonDTO personDTO) {
        return Person.builder()
                .name(personDTO.name())
                .age(personDTO.age())
                .gender(personDTO.gender())
                .build();
    }

    public static Person parseData(PersonDTO personDTO, Person person) {
        person.setName(personDTO.name());
        person.setAge(personDTO.age());
        person.setGender(personDTO.gender());
        return person;
    }

}
