package br.com.graphql.service.impl;

import br.com.graphql.domain.dto.PetDTO;
import br.com.graphql.domain.entity.person.Person;
import br.com.graphql.domain.entity.pet.Pet;
import br.com.graphql.repository.PetRepository;
import br.com.graphql.service.PersonService;
import br.com.graphql.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static br.com.graphql.mapper.PetMapper.parseData;
import static br.com.graphql.mapper.PetMapper.toPet;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    @Autowired
    private final PetRepository petRepository;

    @Autowired
    private final PersonService personService;

    @Override
    public Collection<Pet> findAllPets() {
        return this.petRepository.findAll();
    }

    @Override
    public Optional<Pet> findById(UUID id) {
        return this.petRepository.findById(id);
    }

    @Override
    public Pet savePet(PetDTO pet) {
        var owner = personService.findById(pet.ownerId());
        return owner.map(person -> this.petRepository.save(toPet(pet, person))).orElse(null);
    }

    @Override
    public Pet updatePet(UUID id, PetDTO pet) {
        return petRepository.findById(id).flatMap(existingPet -> {
            UUID newOwnerId = pet.ownerId();
            if (!existingPet.getOwner().getId().equals(newOwnerId)) {
                return personService.findById(newOwnerId).map(newOwner -> saveUpdatedPet(pet, existingPet, newOwner));
            } else {
                return Optional.of(saveUpdatedPet(pet, existingPet, existingPet.getOwner()));
            }
        }).orElse(null);
    }

    private Pet saveUpdatedPet(PetDTO pet, Pet existingPet, Person newOwner) {
        Pet updatedPet = parseData(pet, existingPet, newOwner);
        return petRepository.save(updatedPet);
    }

}
