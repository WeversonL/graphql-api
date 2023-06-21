package br.com.graphql.service.impl;

import br.com.graphql.domain.dto.PersonDTO;
import br.com.graphql.domain.entity.person.Person;
import br.com.graphql.repository.PersonRepository;
import br.com.graphql.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static br.com.graphql.mapper.PersonMapper.parseData;
import static br.com.graphql.mapper.PersonMapper.toPerson;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    @Autowired
    private final PersonRepository personRepository;

    @Override
    public Collection<Person> findAllPeople() {
        return this.personRepository.findAll();
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return this.personRepository.findById(id);
    }

    @Override
    public Person savePerson(PersonDTO person) {
        return this.personRepository.save(toPerson(person));
    }

    @Override
    public Person updatePerson(UUID id, PersonDTO person) {
        return this.personRepository.findById(id)
                .map(peopleObject -> this.personRepository.save(parseData(person, peopleObject)))
                .orElse(null);
    }

}
