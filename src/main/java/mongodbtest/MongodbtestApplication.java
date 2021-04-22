package mongodbtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MongodbtestApplication
{
    @Bean
    AnalyticsMap hash() {
        return new AnalyticsMap();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(MongodbtestApplication.class, args);
    }

}
