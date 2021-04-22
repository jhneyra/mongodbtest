//package org.springframework.data.mongodb.repository.support;
//
//import org.springframework.data.mapping.context.MappingContext;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
//import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
//import org.springframework.data.mongodb.repository.query.MongoQueryMethod;
//import org.springframework.data.mongodb.repository.query.PartTreeMongoQuery;
//import org.springframework.data.mongodb.repository.query.StringBasedAggregation;
//import org.springframework.data.mongodb.repository.query.StringBasedMongoQuery;
//import org.springframework.data.projection.ProjectionFactory;
//import org.springframework.data.repository.core.NamedQueries;
//import org.springframework.data.repository.core.RepositoryMetadata;
//import org.springframework.data.repository.query.QueryLookupStrategy;
//import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
//import org.springframework.data.repository.query.RepositoryQuery;
//import org.springframework.expression.ExpressionParser;
//import org.springframework.expression.spel.standard.SpelExpressionParser;
//import org.springframework.lang.Nullable;
//
//import java.lang.reflect.Method;
//import java.util.Optional;
//
//public class PeruDAMongoRepositoryFactory extends MongoRepositoryFactory
//{
//    private static final SpelExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();
//
//    private final MongoOperations operations;
//
//    private final MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext;
//
//    public PeruDAMongoRepositoryFactory(MongoOperations mongoOperations)
//    {
//        super(mongoOperations);
//        this.operations = mongoOperations;
//        this.mappingContext = mongoOperations.getConverter().getMappingContext();
//    }
//
//    @Override
//    protected Optional<QueryLookupStrategy> getQueryLookupStrategy(@Nullable QueryLookupStrategy.Key key,
//                                                                   QueryMethodEvaluationContextProvider evaluationContextProvider)
//    {
//        return Optional.of(new MongoQueryLookupStrategy(operations, evaluationContextProvider, mappingContext));
//    }
//
//    private static class MongoQueryLookupStrategy implements QueryLookupStrategy {
//
//        private final MongoOperations operations;
//        private final QueryMethodEvaluationContextProvider evaluationContextProvider;
//        private final MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext;
//        private final ExpressionParser expressionParser = new CachingExpressionParser(EXPRESSION_PARSER);
//
//        public MongoQueryLookupStrategy(MongoOperations operations,
//                                        QueryMethodEvaluationContextProvider evaluationContextProvider,
//                                        MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext) {
//
//            this.operations = operations;
//            this.evaluationContextProvider = evaluationContextProvider;
//            this.mappingContext = mappingContext;
//        }
//
//        /*
//         * (non-Javadoc)
//         * @see org.springframework.data.repository.query.QueryLookupStrategy#resolveQuery(java.lang.reflect.Method, org.springframework.data.repository.core.RepositoryMetadata, org.springframework.data.projection.ProjectionFactory, org.springframework.data.repository.core.NamedQueries)
//         */
//        @Override
//        public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory,
//                                            NamedQueries namedQueries) {
//
//            MongoQueryMethod queryMethod = new MongoQueryMethod(method, metadata, factory, mappingContext);
//            String namedQueryName = queryMethod.getNamedQueryName();
//
//            if (namedQueries.hasQuery(namedQueryName)) {
//                String namedQuery = namedQueries.getQuery(namedQueryName);
//                return new StringBasedMongoQuery(namedQuery, queryMethod, operations, expressionParser,
//                        evaluationContextProvider);
//            } else if (queryMethod.hasAnnotatedAggregation()) {
//                return new StringBasedAggregation(queryMethod, operations, expressionParser, evaluationContextProvider);
//            } else if (queryMethod.hasAnnotatedQuery()) {
//                return new StringBasedMongoQuery(queryMethod, operations, expressionParser, evaluationContextProvider);
//            } else {
//                return new PartTreeMongoQuery(queryMethod, operations, expressionParser, evaluationContextProvider);
//            }
//        }
//    }
//}
