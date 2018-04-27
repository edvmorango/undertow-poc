package com.service.impl;

import com.google.inject.Inject;
import com.model.Transaction;
import com.persistence.dynamo.impl.TransactionRepositoryDynamoDBImpl;
import com.persistence.dynamo.item.TransactionItem;
import com.service.TransactionService;

import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {


    @Inject
    private TransactionRepositoryDynamoDBImpl rep;




    @Override
    public Transaction create(Transaction obj) {

       return Optional.of(rep.objectToPersistence(obj)).map(rep::create).map(rep::persistenceToObject).get();

    }

    @Override
    public Transaction findById(String uid) {
        return null;
    }

    @Override
    public List<Transaction> list() {
        return null;
    }
}
