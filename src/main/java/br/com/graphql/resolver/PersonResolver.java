package br.com.graphql.resolver;

import br.com.graphql.domain.dto.PersonDTO;
import br.com.graphql.domain.entity.person.Person;
import br.com.graphql.service.PersonService;
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
public class PersonResolver {

    @Autowired
    private final PersonService personService;

    @QueryMapping()
    public Collection<Person> findAllPeople() {
        return this.personService.findAllPeople();
    }

    @QueryMapping()
    public Optional<Person> findPeopleById(@Argument UUID id) {
        return this.personService.findById(id);
    }

    @MutationMapping()
    public Person savePerson(@Argument PersonDTO person) {
        return this.personService.savePerson(person);
    }

    @MutationMapping()
    public Person updatePerson(@Argument UUID id, @Argument PersonDTO person) {
        return this.personService.updatePerson(id, person);
    }

}
