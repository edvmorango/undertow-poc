package com.service.impl;

import com.google.inject.Inject;
import com.model.Transaction;
import com.persistence.dynamo.impl.TransactionRepositoryDynamoDBImpl;
import com.persistence.dynamo.item.TransactionItem;
import com.service.TransactionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService {

    @Inject
    private TransactionRepositoryDynamoDBImpl rep;

    @Override
    public Transaction create(Transaction obj) throws Exception {
        return rep.create(obj);
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
