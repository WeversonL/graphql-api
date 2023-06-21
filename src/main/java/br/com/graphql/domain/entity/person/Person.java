package br.com.graphql.domain.entity.person;

import br.com.graphql.domain.entity.pet.Pet;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "peoples")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private Integer age;

    private Gender gender;

    @OneToMany(mappedBy = "owner")
    private Collection<Pet> pets;

}
