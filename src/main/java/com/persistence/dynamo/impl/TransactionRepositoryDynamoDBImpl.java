package com.persistence.dynamo.impl;

import com.google.inject.Inject;
import com.model.Transaction;
import com.persistence.dynamo.DynamoDBClient;
import com.persistence.repository.TransactionRepository;
import com.persistence.dynamo.item.TransactionItem;
import java.util.List;

public class TransactionRepositoryDynamoDBImpl implements TransactionRepository<Transaction, TransactionItem> {

    @Inject private DynamoDBClient client;

    @Override
    public Transaction persistenceToObject(TransactionItem entity) {
        return null;
    }

    @Override
    public TransactionItem objectToPersistence(Transaction obj) {
        return null;
    }

    @Override
    public TransactionItem create(TransactionItem obj) {


        System.out.println(obj.getUid());
        client.getMapper().save(obj);

        try{
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(obj.getUid());
        return obj;
    }

    @Override
    public TransactionItem findById(String uid) {
        return null;
    }

    @Override
    public List<TransactionItem> list() {
        return null;
    }
}
