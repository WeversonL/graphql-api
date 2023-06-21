package br.com.graphql.service;

import br.com.graphql.domain.dto.PetDTO;
import br.com.graphql.domain.entity.person.Gender;
import br.com.graphql.domain.entity.person.Person;
import br.com.graphql.domain.entity.pet.Pet;
import br.com.graphql.repository.PetRepository;
import br.com.graphql.service.impl.PetServiceImpl;
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
public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private PersonService personService;

    private PetServiceImpl petService;

    @Before
    public void setUp() {
        petService = new PetServiceImpl(petRepository, personService);
    }

    @Test
    public void testFindAllPets() {

        Collection<Pet> pets = List.of(new Pet(UUID.randomUUID(), "Max", new Person()));
        when(petRepository.findAll()).thenReturn(pets.stream().toList());

        Collection<Pet> result = petService.findAllPets();

        assertEquals(pets, result);
        verify(petRepository).findAll();
    }

    @Test
    public void testFindPetById() {

        UUID id = UUID.randomUUID();
        Pet pet = new Pet(UUID.randomUUID(), "Max", new Person());
        when(petRepository.findById(id)).thenReturn(Optional.of(pet));

        Optional<Pet> result = petService.findById(id);

        assertEquals(Optional.of(pet), result);
        verify(petRepository).findById(id);
    }

    @Test
    public void testSavePet() {

        PetDTO petDTO = new PetDTO("Max", UUID.randomUUID());
        Person owner = new Person(UUID.randomUUID(), "John", 30, Gender.MALE, new ArrayList<>());
        Pet savedPet = new Pet(UUID.randomUUID(), "Max", owner);

        when(personService.findById(any())).thenReturn(Optional.of(owner));
        when(petRepository.save(any())).thenReturn(savedPet);

        Pet result = petService.savePet(petDTO);

        assertEquals(savedPet, result);
        verify(petRepository).save(any());
    }

    @Test
    public void testUpdatePet() {

        UUID id = UUID.randomUUID();
        PetDTO petDTO = new PetDTO("Max", UUID.randomUUID());
        Person owner = new Person(UUID.randomUUID(), "John", 30, Gender.MALE, new ArrayList<>());
        Pet existingPet = new Pet(UUID.randomUUID(), "Max", owner);
        Pet updatedPet = new Pet(UUID.randomUUID(), "Ralph", owner);

        when(personService.findById(any())).thenReturn(Optional.of(owner));
        when(petRepository.findById(id)).thenReturn(Optional.of(existingPet));
        when(petRepository.save(any())).thenReturn(updatedPet);

        Pet result = petService.updatePet(id, petDTO);

        assertEquals(updatedPet, result);
        verify(petRepository).findById(id);
        verify(petRepository).save(any());
    }

}
