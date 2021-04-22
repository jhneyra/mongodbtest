package mongodbtest.repository;

import mongodbtest.entity.Person;
import org.springframework.data.mongodb.repository.Query;

public interface PersonRepository extends PDARepository<Person, String>
{
    @Query("{ 'name' : ?0 }")
    Person findByName(String name);
}

