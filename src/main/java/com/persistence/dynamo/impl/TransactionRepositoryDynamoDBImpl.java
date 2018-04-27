package com.persistence.dynamo.impl;

import com.google.inject.Inject;
import com.model.Transaction;
import com.persistence.dynamo.DynamoDBClient;
import com.persistence.repository.TransactionRepository;
import com.persistence.dynamo.item.TransactionItem;
import com.utils.DateFormatter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransactionRepositoryDynamoDBImpl implements TransactionRepository<Transaction, TransactionItem> {

    @Inject private DynamoDBClient client;

    @Override
    public Transaction persistenceToObject(TransactionItem entity) {

        UUID uuid = UUID.fromString(entity.getClientName());
        LocalDateTime createdAt = DateFormatter.dateToLocalDateTime(entity.getCreatedAt());

        return new Transaction(uuid, entity.getClientName(), entity.getValue(), createdAt);

    }

    @Override
    public TransactionItem objectToPersistence(Transaction obj) {

        String uid = obj.getUid().toString();
        Date createdAt = DateFormatter.localDateTimeToDate(obj.getCreatedAt());

        return new TransactionItem(uid, obj.getClientName(), obj.getValue(), createdAt);
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