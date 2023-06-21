package br.com.graphql.repository;

import br.com.graphql.domain.entity.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
