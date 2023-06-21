package br.com.graphql.domain.entity.person;

import br.com.graphql.domain.entity.pet.Pet;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {

    @Test
    public void testPerson() {

        UUID id = UUID.randomUUID();
        String name = "John";
        Integer age = 30;
        Gender gender = Gender.FEMALE;
        Collection<Pet> pets = new ArrayList<>();

        Person person = Person.builder()
                .id(id)
                .name(name)
                .age(age)
                .gender(gender)
                .pets(pets)
                .build();

        assertEquals(id, person.getId());
        assertEquals(name, person.getName());
        assertEquals(age, person.getAge());
        assertEquals(gender, person.getGender());
        assertEquals(pets, person.getPets());
    }

    @Test
    public void testPersonPetsOneToMany() throws NoSuchFieldException {
        OneToMany oneToMany = Person.class.getDeclaredField("pets").getAnnotation(OneToMany.class);
        assertEquals("owner", oneToMany.mappedBy());
    }

    @Test
    public void testPersonTable() {
        Table table = Person.class.getAnnotation(Table.class);
        assertEquals("peoples", table.name());
    }

}
