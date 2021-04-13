package mongodbtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.PeruDAMongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"mongodbtest"}, repositoryFactoryBeanClass = PeruDAMongoRepositoryFactoryBean.class)
public class MongoConfig
{
    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory databaseFactory, MappingMongoConverter converter)
    {
        System.out.println("Creating Mongo Template");
        return new PeruDAMongoTemplate(databaseFactory, converter);
    }

//    public MongoRepositoryFactoryBean mongoRepositoryFactoryBean()
//    {
//        return new MongoRepositoryFactoryBean()
//        {
//
//        };
//    }

}
