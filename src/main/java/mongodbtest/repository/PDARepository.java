package mongodbtest.repository;

import org.springframework.data.repository.CrudRepository;

public interface PDARepository<T, ID> extends CrudRepository<T, ID>
{
//    List< T, ID> ExeQuery(String query)
//    {
//        timestamp start = getTime();
//        List< T, ID> result = super.ExeQuery();
//        timestamp end = getTime();
//
//        saveQuery(module, "GetInbox", query, start, end)
//    }
}
