package br.com.graphql.service;

import br.com.graphql.domain.dto.PersonDTO;
import br.com.graphql.domain.entity.person.Gender;
import br.com.graphql.domain.entity.person.Person;
import br.com.graphql.repository.PersonRepository;
import br.com.graphql.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    private PersonServiceImpl personService;

    @Before
    public void setUp() {
        personService = new PersonServiceImpl(personRepository);
    }

    @Test
    public void testFindAllPeople() {

        Collection<Person> people = List.of(new Person(UUID.randomUUID(), "John", 30, Gender.MALE, new ArrayList<>()));

        when(personRepository.findAll()).thenReturn(people.stream().toList());

        Collection<Person> result = personService.findAllPeople();

        assertEquals(people, result);
        verify(personRepository).findAll();
    }

    @Test
    public void testFindById() {

        UUID id = UUID.randomUUID();
        Person person = new Person(UUID.randomUUID(), "John", 30, Gender.MALE, new ArrayList<>());
        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        Optional<Person> result = personService.findById(id);

        assertEquals(Optional.of(person), result);
        verify(personRepository).findById(id);
    }

    @Test
    public void testSavePerson() {

        PersonDTO personDTO = new PersonDTO("John", 30, Gender.MALE);
        Person savedPerson = new Person(UUID.randomUUID(), "John", 30, Gender.MALE, new ArrayList<>());
        when(personRepository.save(any())).thenReturn(savedPerson);

        Person result = personService.savePerson(personDTO);

        assertEquals(savedPerson, result);
        verify(personRepository).save(any());
    }

    @Test
    public void testUpdatePerson() {

        UUID id = UUID.randomUUID();
        PersonDTO personDTO = new PersonDTO("John", 30, Gender.MALE);
        Person existingPerson = new Person(UUID.randomUUID(), "John", 30, Gender.MALE, new ArrayList<>());
        Person updatedPerson = new Person(UUID.randomUUID(), "John Senna", 30, Gender.MALE, new ArrayList<>());
        when(personRepository.findById(id)).thenReturn(Optional.of(existingPerson));
        when(personRepository.save(any())).thenReturn(updatedPerson);

        Person result = personService.updatePerson(id, personDTO);

        assertEquals(updatedPerson, result);
        verify(personRepository).findById(id);
        verify(personRepository).save(any());
    }

}
