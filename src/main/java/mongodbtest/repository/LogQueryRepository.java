package mongodbtest.repository;

import mongodbtest.entity.LogQuery;
import org.springframework.data.repository.CrudRepository;

public interface LogQueryRepository extends CrudRepository<LogQuery, String>
{
}

