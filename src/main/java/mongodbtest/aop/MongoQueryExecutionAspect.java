package mongodbtest.aop;

import mongodbtest.AnalyticsMap;
import mongodbtest.entity.LogQuery;
import mongodbtest.repository.LogQueryRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Aspect
@Component
public class MongoQueryExecutionAspect
{
    @Autowired
    AnalyticsMap hash;

    @Autowired
    LogQueryRepository logQueryRepository;

//    @Around("@annotation(org.springframework.data.mongodb.repository.Query)")
    @Around("execution(* *.PDARepository.*(..))")
    public Object mongoQueryExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();

        Long threadId = Thread.currentThread().getId();
        System.out.println("====>> logExecutionTime in thread: " + threadId);
//        String name = joinPoint.getSignature().toLongString();
        String name = joinPoint.getSignature().toShortString();
        LogQuery params = new LogQuery();
        params.setStartTime(System.currentTimeMillis());
        HashMap<String, LogQuery> map = new HashMap<>();
        params.setMethodName(name);
        map.put(name, params);
        hash.put(threadId, map);
        Object response = joinPoint.proceed();
        params.setEndTime(System.currentTimeMillis());
        hash.remove(threadId);

        logQueryRepository.save(params);

        final long executionTime = System.currentTimeMillis() - start;

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return response;
    }

}
