package br.com.graphql.domain.dto;

import java.util.UUID;

public record PetDTO(String name, UUID ownerId) {
}
