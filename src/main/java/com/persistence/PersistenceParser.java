package com.persistence;

public interface PersistenceParser<E, T> {

    T persistenceToObject(E entity);

    E objectToPersistence(T obj);

}
