package br.com.graphql.mapper;

import br.com.graphql.domain.dto.PetDTO;
import br.com.graphql.domain.entity.person.Person;
import br.com.graphql.domain.entity.pet.Pet;

public class PetMapper {

    private PetMapper() {
    }

    public static Pet toPet(PetDTO petDTO, Person person) {
        return Pet.builder()
                .name(petDTO.name())
                .owner(person)
                .build();
    }

    public static Pet parseData(PetDTO petDTO, Pet pet, Person owner) {
        pet.setName(petDTO.name());
        pet.setOwner(owner);
        return pet;
    }

}
