package com.persistence;

public interface PersistenceParser<T,E> {

    T persistenceToObject(E entity);

    E objectToPersistence(T obj);

}
