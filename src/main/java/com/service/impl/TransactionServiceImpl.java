package com.service.impl;

import com.google.inject.Inject;
import com.model.Transaction;
import com.persistence.dynamo.impl.TransactionRepositoryDynamoDBImpl;
import com.messaging.TransactionTopic;
import com.service.TransactionService;

import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {

    private TransactionRepositoryDynamoDBImpl rep;
    private TransactionTopic topics;
    @Inject
    public TransactionServiceImpl(TransactionRepositoryDynamoDBImpl rep, TransactionTopic topics) {
        this.rep = rep;
        this.topics = topics;
    }

    @Override
    public Transaction create(Transaction obj) throws Exception {

        Transaction transaction = rep.create(obj);

        topics.publishPendingTransaction(transaction);
        
        return transaction;
    }

    @Override
    public Optional<Transaction> findById(String uid) {
        return rep.findById(uid);
    }

    @Override
    public List<Transaction> list() {
        return rep.list();
    }
}
