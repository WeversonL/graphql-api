package br.com.graphql.mapper;

import br.com.graphql.domain.dto.PetDTO;
import br.com.graphql.domain.entity.person.Person;
import br.com.graphql.domain.entity.pet.Pet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PetMapperTest {

    @Test
    public void testToPet() {

        var uuid = UUID.randomUUID();

        PetDTO petDTO = new PetDTO("Max", uuid);
        Person person = Mockito.mock(Person.class);

        Pet pet = PetMapper.toPet(petDTO, person);

        assertEquals("Max", pet.getName());
        assertEquals(person, pet.getOwner());
    }

    @Test
    public void testParseData() {

        var uuid = UUID.randomUUID();

        PetDTO petDTO = new PetDTO("Max", uuid);
        Pet pet = Mockito.mock(Pet.class);
        Person owner = Mockito.mock(Person.class);

        Pet result = PetMapper.parseData(petDTO, pet, owner);

        assertEquals(pet, result);
        verify(pet).setName("Max");
        verify(pet).setOwner(owner);
    }

}
