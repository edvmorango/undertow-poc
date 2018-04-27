package com.persistence.repository;

import com.persistence.PersistenceParser;
import com.persistence.repository.GenericRepository;


public interface TransactionRepository<Transaction, E> extends PersistenceParser<Transaction, E>, GenericRepository<E> {

    // Should implement specific queries of Transaction

}