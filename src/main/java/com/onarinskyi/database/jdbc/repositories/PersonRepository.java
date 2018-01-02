package com.onarinskyi.database.jdbc.repositories;

import com.onarinskyi.database.jdbc.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

    private final JdbcTemplate template;

    @Autowired
    public PersonRepository(JdbcTemplate template) {
        this.template = template;
    }

    private RowMapper<Person> rowMapper = (resultSet, rowNum) ->
            new Person(resultSet.getLong("ID"),
                    resultSet.getString("NAME"));

    public Person getPerson(long id) {
        String query = "SELECT * FROM PERSON WHERE ID=?";
        return template.queryForObject(query, rowMapper, id);
    }
}
