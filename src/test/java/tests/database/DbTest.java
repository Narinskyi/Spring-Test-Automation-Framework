package tests.database;

import com.onarinskyi._testdata_.database.DatabaseManager;
import com.onarinskyi._testdata_.database.entities.Person;
import com.onarinskyi._testdata_.database.repositories.PersonRepository;
import com.onarinskyi._testdata_.reporting.Feature;
import com.onarinskyi.context.AbstractTestNGTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Features(Feature.DATABASE)
@Stories("In-memory database test")
@Transactional
public class DbTest extends AbstractTestNGTest {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private DatabaseManager databaseManager;

    @BeforeClass
    public void init() {
        databaseManager.createDatabase();
    }

    @AfterClass
    public void teardown() {
        databaseManager.closeDatabase();
    }

    @Test
    public void verifySelectingFromDb() {
        Person person = repository.getPerson(1);

        assertThat(person.getName(), equalTo("John"));
    }

    @Test
    public void verifyUpdatingDb() {
        repository.insert(new Person(4, "Test"));

        assertThat(repository.getPerson(4).getName(), equalTo("Test"));
    }
}
