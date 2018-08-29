package com.onarinskyi._testdata_.database;

import com.onarinskyi.config.DbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
@PropertySource("classpath:database.properties")
public class DatabaseManager {

    @Autowired
    private DbConfig dbConfig;

    public void createDatabase() {
        try  {
            Connection connection = getConnection();

            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE PERSON(id int primary key, name varchar(255))");
            statement.execute("INSERT INTO PERSON(id, name) VALUES(1, 'John')");
            statement.execute("INSERT INTO PERSON(id, name) VALUES(2, 'Mark')");
            statement.execute("INSERT INTO PERSON(id, name) VALUES(3, 'Peter')");

            statement.executeQuery("select * from PERSON");
            statement.close();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(dbConfig.getDriver());
            dbConnection = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

    public void closeDatabase() {
        try {
            getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}