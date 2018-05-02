package com.service.impl;

import com.google.inject.Inject;
import com.model.Transaction;
import com.persistence.dynamo.impl.TransactionRepositoryDynamoDBImpl;
import com.persistence.dynamo.item.TransactionItem;
import com.persistence.sqs.TransactionQueue;
import com.service.TransactionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService {

    private TransactionRepositoryDynamoDBImpl rep;
    private TransactionQueue queue;

    @Inject
    public TransactionServiceImpl(TransactionRepositoryDynamoDBImpl rep, TransactionQueue queue) {
        this.rep = rep;
        this.queue = queue;
    }

    @Override
    public Transaction create(Transaction obj) throws Exception {

        Transaction transaction = rep.create(obj);

        queue.enqueuePendingTransaction(transaction);
        queue.getPendingTransactions();
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
