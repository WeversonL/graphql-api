package br.com.graphql.service;

import br.com.graphql.domain.dto.PersonDTO;
import br.com.graphql.domain.entity.person.Person;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {

    Collection<Person> findAllPeople();

    Optional<Person> findById(UUID id);

    Person savePerson(PersonDTO person);

    Person updatePerson(UUID id, PersonDTO person);

}
