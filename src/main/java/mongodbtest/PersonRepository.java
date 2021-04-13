package mongodbtest;

import mongodbtest.annotation.TracedQuery;
import mongodbtest.entity.Person;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String>
{
//    @Query("{name: \"Person.findByName\", \"param1\": \"\"}")
    @TracedQuery("{\"param1\": \"\"}")
    Person findByName(String name);
}
