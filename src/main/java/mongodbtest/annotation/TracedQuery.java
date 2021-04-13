package mongodbtest.annotation;

import org.springframework.data.mongodb.repository.Query;

@Query
public @interface TracedQuery
{
    String value() default "";
}
