package br.com.graphql.resolver;

import br.com.graphql.domain.dto.PetDTO;
import br.com.graphql.domain.entity.pet.Pet;
import br.com.graphql.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PetResolver {

    @Autowired
    private final PetService petService;

    @QueryMapping()
    public Collection<Pet> findAllPets() {
        return this.petService.findAllPets();
    }

    @QueryMapping()
    public Optional<Pet> findPetById(@Argument UUID id) {
        return this.petService.findById(id);
    }

    @MutationMapping()
    public Pet savePet(@Argument PetDTO pet) {
        return this.petService.savePet(pet);
    }

    @MutationMapping()
    public Pet updatePet(@Argument UUID id, @Argument PetDTO pet) {
        return this.petService.updatePet(id, pet);
    }

}
