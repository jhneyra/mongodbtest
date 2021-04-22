package mongodbtest;

import mongodbtest.entity.LogQuery;
import mongodbtest.entity.Person;
import mongodbtest.repository.LogQueryRepository;
import mongodbtest.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MongodbtestApplicationTests
{

    @Autowired
    PersonRepository repository;

    @Autowired
    LogQueryRepository logQueryRepository;

    @Test
    void contextLoads()
    {
        long total = repository.count();
        Assertions.assertEquals(0, total);
        Person p1 = new Person();
        p1.setId("00001");
        p1.setName("Juan Perez");
        repository.save(p1);
        total = repository.count();
        Assertions.assertEquals(1, total);
        Person response = repository.findByName("Juan Perez");
        Assertions.assertEquals(p1.getId(), response.getId());

        Iterable<LogQuery> logs = logQueryRepository.findAll();
        LogQuery logQuery = logs.iterator().next();
        System.out.println(logQuery.getMethodName());
        Assertions.assertNotNull(logQuery);
    }

}
