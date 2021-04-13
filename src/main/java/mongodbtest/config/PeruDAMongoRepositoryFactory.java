//package mongodbtest.config;
//
//import org.springframework.aop.framework.ProxyFactory;
//import org.springframework.aop.interceptor.ExposeInvocationInterceptor;
//import org.springframework.core.log.LogMessage;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
//import org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor;
//import org.springframework.data.projection.ProjectionFactory;
//import org.springframework.data.repository.Repository;
//import org.springframework.data.repository.core.RepositoryInformation;
//import org.springframework.data.repository.core.RepositoryMetadata;
//import org.springframework.data.repository.core.support.MethodInvocationValidator;
//import org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor;
//import org.springframework.data.repository.core.support.RepositoryComposition;
//import org.springframework.data.repository.core.support.RepositoryFragment;
//import org.springframework.data.repository.query.QueryLookupStrategy;
//import org.springframework.transaction.interceptor.TransactionalProxy;
//import org.springframework.util.Assert;
//
//import java.util.Optional;
//
//public class PeruDAMongoRepositoryFactory extends MongoRepositoryFactory
//{
//    public PeruDAMongoRepositoryFactory(MongoOperations mongoOperations)
//    {
//        super(mongoOperations);
//    }
//
//    private RepositoryComposition getRepositoryComposition(RepositoryMetadata metadata) {
//        return RepositoryComposition.fromMetadata(metadata);
//    }
//
//    private RepositoryComposition getRepositoryComposition(RepositoryMetadata metadata, RepositoryComposition.RepositoryFragments fragments) {
//
//        Assert.notNull(metadata, "RepositoryMetadata must not be null!");
//        Assert.notNull(fragments, "RepositoryFragments must not be null!");
//
//        RepositoryComposition composition = getRepositoryComposition(metadata);
//        RepositoryComposition.RepositoryFragments repositoryAspects = getRepositoryFragments(metadata);
//
//        return composition.append(fragments).append(repositoryAspects);
//    }
//
//    public <T> T getRepository(Class<T> repositoryInterface, RepositoryComposition.RepositoryFragments fragments) {
//
//        Assert.notNull(repositoryInterface, "Repository interface must not be null!");
//        Assert.notNull(fragments, "RepositoryFragments must not be null!");
//
//        RepositoryMetadata metadata = getRepositoryMetadata(repositoryInterface);
//        RepositoryComposition composition = getRepositoryComposition(metadata, fragments);
//        RepositoryInformation information = getRepositoryInformation(metadata, composition);
//
//        validate(information, composition);
//
//        Object target = getTargetRepository(information);
//
//        // Create proxy
//        ProxyFactory result = new ProxyFactory();
//        result.setTarget(target);
//        result.setInterfaces(repositoryInterface, Repository.class, TransactionalProxy.class);
//
//        if (MethodInvocationValidator.supports(repositoryInterface)) {
//            result.addAdvice(new MethodInvocationValidator());
//        }
//
//        result.addAdvisor(ExposeInvocationInterceptor.ADVISOR);
//
//        postProcessors.forEach(processor -> processor.postProcess(result, information));
//
//        if (DefaultMethodInvokingMethodInterceptor.hasDefaultMethods(repositoryInterface)) {
//            result.addAdvice(new DefaultMethodInvokingMethodInterceptor());
//        }
//
//        ProjectionFactory projectionFactory = getProjectionFactory(classLoader, beanFactory);
//        Optional<QueryLookupStrategy> queryLookupStrategy = getQueryLookupStrategy(queryLookupStrategyKey,
//                evaluationContextProvider);
//        result.addAdvice(new QueryExecutorMethodInterceptor(information, projectionFactory, queryLookupStrategy,
//                namedQueries, queryPostProcessors, methodInvocationListeners));
//
//        composition = composition.append(RepositoryFragment.implemented(target));
//        result.addAdvice(new ImplementationMethodExecutionInterceptor(information, composition, methodInvocationListeners));
//
//        T repository = (T) result.getProxy(classLoader);
//
//        if (logger.isDebugEnabled()) {
//            logger
//                    .debug(LogMessage.format("Finished creation of repository instance for {}.", repositoryInterface.getName()));
//        }
//
//        return repository;
//    }
//
//}
