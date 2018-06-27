package com.onarinskyi._testdata_.database.jdbc.services;

import com.onarinskyi._testdata_.database.jdbc.repositories.PersonRepository;
import com.onarinskyi._testdata_.database.jdbc.entities.Person;
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
