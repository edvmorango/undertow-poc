package com.persistence.dynamo.impl;

import com.persistence.dynamo.item.TransactionItem;
import com.persistence.repository.GenericRepository;
import java.util.ArrayList;

public class TransactionRepositoryDynamoDB implements GenericRepository<TransactionItem> {

    @Override
    public TransactionItem create(TransactionItem obj) {
        return null;
    }

    @Override
    public TransactionItem find(String uid) {
        return null;
    }

    @Override
    public ArrayList<TransactionItem> list() {
        return null;
    }

}