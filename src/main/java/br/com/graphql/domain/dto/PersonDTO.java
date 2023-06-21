package br.com.graphql.domain.dto;

import br.com.graphql.domain.entity.person.Gender;

public record PersonDTO(String name, Integer age, Gender gender) {
}
