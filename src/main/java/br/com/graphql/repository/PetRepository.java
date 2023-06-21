package br.com.graphql.repository;

import br.com.graphql.domain.entity.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID> {
}
