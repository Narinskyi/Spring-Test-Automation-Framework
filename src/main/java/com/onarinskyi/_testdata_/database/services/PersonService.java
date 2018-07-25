package com.onarinskyi._testdata_.database.services;

import com.onarinskyi._testdata_.database.entities.Person;
import com.onarinskyi._testdata_.database.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Person getPerson(long id) {
        //and do something else
        return repository.getPerson(id);
    }
}
