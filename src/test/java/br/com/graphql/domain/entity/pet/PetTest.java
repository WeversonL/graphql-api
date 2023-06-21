package br.com.graphql.domain.entity.pet;


import br.com.graphql.domain.entity.person.Person;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class PetTest {

    @Test
    public void testPet() {

        UUID id = UUID.randomUUID();
        String name = "Max";
        Person owner = Mockito.mock(Person.class);

        Pet pet = Pet.builder()
                .id(id)
                .name(name)
                .owner(owner)
                .build();

        assertEquals(id, pet.getId());
        assertEquals(name, pet.getName());
        assertEquals(owner, pet.getOwner());
    }

    @Test
    public void testPetOwnerJoinColumn() throws NoSuchFieldException {
        JoinColumn joinColumn = Pet.class.getDeclaredField("owner").getAnnotation(JoinColumn.class);
        assertEquals("person_id", joinColumn.name());
        assertFalse(joinColumn.nullable());
    }

    @Test
    public void testPetTable() {
        Table table = Pet.class.getAnnotation(Table.class);
        assertEquals("pets", table.name());
    }

}
