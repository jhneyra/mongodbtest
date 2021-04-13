package org.springframework.data.mongodb.core;

import org.bson.Document;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class PeruDAMongoTemplate extends MongoTemplate
{
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
}
