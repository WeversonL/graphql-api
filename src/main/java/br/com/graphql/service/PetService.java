package br.com.graphql.service;

import br.com.graphql.domain.dto.PetDTO;
import br.com.graphql.domain.entity.pet.Pet;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface PetService {

    Collection<Pet> findAllPets();

    Optional<Pet> findById(UUID id);

    Pet savePet(PetDTO pet);

    Pet updatePet(UUID id, PetDTO pet);

}
