package br.com.graphql.mapper;

import br.com.graphql.domain.dto.PersonDTO;
import br.com.graphql.domain.entity.person.Gender;
import br.com.graphql.domain.entity.person.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PersonMapperTest {

    @Test
    public void testToPerson() {

        PersonDTO personDTO = new PersonDTO("John", 30, Gender.MALE);

        Person person = PersonMapper.toPerson(personDTO);

        assertEquals("John", person.getName());
        assertEquals(30, person.getAge());
        assertEquals(Gender.MALE, person.getGender());
    }

    @Test
    public void testParseData() {

        PersonDTO personDTO = new PersonDTO("John", 30, Gender.MALE);
        Person person = Mockito.mock(Person.class);

        Person result = PersonMapper.parseData(personDTO, person);

        assertEquals(person, result);
        verify(person).setName("John");
        verify(person).setAge(30);
        verify(person).setGender(Gender.MALE);

    }

}
