package org.springframework.data.mongodb.core;

import mongodbtest.AnalyticsMap;
import mongodbtest.entity.LogQuery;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.List;

public class PeruDAMongoTemplate extends MongoTemplate
{
    @Autowired
    AnalyticsMap hash;

    public PeruDAMongoTemplate(MongoDatabaseFactory databaseFactory, MappingMongoConverter converter)
    {
        super(databaseFactory, converter);
    }

    @Override
    <S, T> List<T> doFind(String collectionName, Document query, Document fields, Class<S> sourceClass,
                          Class<T> targetClass, CursorPreparer preparer) {
        System.out.println("--> collectionName: " + collectionName);
        System.out.println("--> query: " + query);// db.person.find({name: 'Juan Perez'});
        System.out.println("--> fields: " + fields);
        System.out.println("--> sourceClass: " + sourceClass);
        fillLogQuery(collectionName, query, fields, sourceClass);
        return super.doFind(collectionName, query, fields, sourceClass, targetClass, preparer);
    }

    @Override
    protected void executeQuery(Query query, String collectionName, DocumentCallbackHandler documentCallbackHandler, CursorPreparer preparer)
    {
        System.out.println("--> executing: " + query);
        System.out.println("--> collectionName: " + collectionName);
        System.out.println("--> documentCallbackHandler: " + documentCallbackHandler);
        System.out.println("--> preparer: " + preparer);

        super.executeQuery(query, collectionName, documentCallbackHandler, preparer);
    }

    private <S> void fillLogQuery(String collectionName, Document query, Document fields, Class<S> sourceClass) {
        long threadId = Thread.currentThread().getId();
        System.out.println("====>> fillQuery in thread: " + threadId);
        HashMap<String, LogQuery> names = hash.get(threadId);
        assert names.size() == 1 : "Names must have 1 element.";
        LogQuery params = names.values().iterator().next();
        params.setQuery(query.toJson());
        params.setFields(fields.toJson());
        params.setCollectionName(collectionName);
        params.setSourceClass(sourceClass.toString());
    }
}
