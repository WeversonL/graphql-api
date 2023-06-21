package br.com.graphql.domain.dto;

import br.com.graphql.domain.entity.person.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PersonDTOTest {

    @Test
    public void testPersonDTO() {

        String name = "John";
        Integer age = 30;
        Gender gender = Gender.MALE;

        PersonDTO personDTO = new PersonDTO(name, age, gender);

        assertEquals(name, personDTO.name());
        assertEquals(age, personDTO.age());
        assertEquals(gender, personDTO.gender());
    }

}
