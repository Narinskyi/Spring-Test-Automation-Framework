package com.onarinskyi._testdata_.database.repositories;

import com.onarinskyi._testdata_.database.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

    @Autowired
    private JdbcTemplate template;

    private RowMapper<Person> rowMapper = (resultSet, rowNum) ->
            new Person(resultSet.getLong("ID"),
                    resultSet.getString("NAME"));

    public Person getPerson(long id) {
        String query = "SELECT * FROM PERSON WHERE ID=?";
        return template.queryForObject(query, rowMapper, id);
    }

    public void insert(Person person) {
        String query = "INSERT INTO PERSON VALUES(?,?)";
        template.update(query, person.getId(), person.getName());
    }
}
