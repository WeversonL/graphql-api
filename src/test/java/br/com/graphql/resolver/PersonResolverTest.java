package br.com.graphql.resolver;

import br.com.graphql.domain.dto.PersonDTO;
import br.com.graphql.domain.entity.person.Gender;
import br.com.graphql.domain.entity.person.Person;
import br.com.graphql.service.PersonService;
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
public class PersonResolverTest {

    @Mock
    private PersonService personService;

    private PersonResolver personResolver;

    @Before
    public void setUp() {
        personResolver = new PersonResolver(personService);
    }

    @Test
    public void testFindAllPeople() {

        Collection<Person> people = List.of(new Person(UUID.randomUUID(), "John", 30, Gender.MALE, new ArrayList<>()));

        when(personService.findAllPeople()).thenReturn(people);

        Collection<Person> result = personResolver.findAllPeople();

        assertEquals(people, result);
        verify(personService).findAllPeople();

    }

    @Test
    public void testFindPeopleById() {

        UUID id = UUID.randomUUID();
        Person person = new Person(UUID.randomUUID(), "John", 30, Gender.MALE, new ArrayList<>());
        when(personService.findById(any())).thenReturn(Optional.of(person));

        Optional<Person> result = personResolver.findPeopleById(id);

        assertEquals(Optional.of(person), result);
        verify(personService).findById(id);
    }

    @Test
    public void testSavePerson() {

        PersonDTO personDTO = new PersonDTO("John", 30, Gender.MALE);
        Person savedPerson = new Person(UUID.randomUUID(), "John", 30, Gender.MALE, new ArrayList<>());
        when(personService.savePerson(any())).thenReturn(savedPerson);

        Person result = personResolver.savePerson(personDTO);

        assertEquals(savedPerson, result);
        verify(personService).savePerson(personDTO);
    }

    @Test
    public void testUpdatePerson() {

        UUID id = UUID.randomUUID();
        PersonDTO personDTO = new PersonDTO("John", 30, Gender.MALE);
        Person updatedPerson = new Person(UUID.randomUUID(), "John", 30, Gender.MALE, new ArrayList<>());
        when(personService.updatePerson(any(), any())).thenReturn(updatedPerson);

        Person result = personResolver.updatePerson(id, personDTO);

        assertEquals(updatedPerson, result);
        verify(personService).updatePerson(id, personDTO);
    }

}
