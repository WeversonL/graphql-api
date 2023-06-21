package br.com.graphql.resolver;

import br.com.graphql.domain.dto.PetDTO;
import br.com.graphql.domain.entity.person.Person;
import br.com.graphql.domain.entity.pet.Pet;
import br.com.graphql.service.PetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PetResolverTest {

    @Mock
    private PetService petService;

    private PetResolver petResolver;

    @Before
    public void setUp() {
        petResolver = new PetResolver(petService);
    }

    @Test
    public void testFindAllPets() {

        Collection<Pet> pets = List.of(new Pet(UUID.randomUUID(), "Max", new Person()));
        when(petService.findAllPets()).thenReturn(pets);

        Collection<Pet> result = petResolver.findAllPets();

        assertEquals(pets, result);
        verify(petService).findAllPets();
    }

    @Test
    public void testFindPetById() {

        UUID id = UUID.randomUUID();
        Pet pet = new Pet(UUID.randomUUID(), "Max", new Person());
        when(petService.findById(any())).thenReturn(Optional.of(pet));

        Optional<Pet> result = petResolver.findPetById(id);

        assertEquals(Optional.of(pet), result);
        verify(petService).findById(id);
    }

    @Test
    public void testSavePet() {

        PetDTO petDTO = new PetDTO("Max", UUID.randomUUID());
        Pet savedPet = new Pet(UUID.randomUUID(), "Max", new Person());
        when(petService.savePet(any())).thenReturn(savedPet);

        Pet result = petResolver.savePet(petDTO);

        assertEquals(savedPet, result);
        verify(petService).savePet(petDTO);
    }

    @Test
    public void testUpdatePet() {

        UUID id = UUID.randomUUID();
        PetDTO petDTO = new PetDTO("Max", UUID.randomUUID());
        Pet updatedPet = new Pet(UUID.randomUUID(), "Max", new Person());
        when(petService.updatePet(any(), any())).thenReturn(updatedPet);

        Pet result = petResolver.updatePet(id, petDTO);

        assertEquals(updatedPet, result);
        verify(petService).updatePet(id, petDTO);
    }

}
