package br.com.graphql.domain.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PetDTOTest {

    @Test
    public void testPetDTO() {

        String name = "Max";
        UUID ownerId = UUID.randomUUID();

        PetDTO petDTO = new PetDTO(name, ownerId);

        assertEquals(name, petDTO.name());
        assertEquals(ownerId, petDTO.ownerId());

    }

}
