package com.onarinskyi.database.jdbc.services;

import com.onarinskyi.database.jdbc.repositories.PersonRepository;
import com.onarinskyi.database.jdbc.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person getPerson(long id) {
        //and do something else
        return repository.getPerson(id);
    }
}
