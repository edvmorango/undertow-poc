package com.persistence.dynamo.impl;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.google.inject.Inject;
import com.model.Transaction;
import com.persistence.dynamo.DynamoDBClient;
import com.persistence.repository.TransactionRepository;
import com.persistence.dynamo.item.TransactionItem;
import com.utils.DateFormatter;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class TransactionRepositoryDynamoDBImpl implements TransactionRepository<TransactionItem, Transaction> {

    @Inject
    private DynamoDBClient client;

    @Override
    public Transaction persistenceToObject(TransactionItem entity) {

        UUID uuid = UUID.fromString(entity.getUid());
        LocalDateTime createdAt = DateFormatter.dateToLocalDateTime(entity.getCreatedAt());

        return new Transaction(uuid, entity.getClientName(), entity.getValue(), createdAt, entity.getCreditCard());

    }

    @Override
    public TransactionItem objectToPersistence(Transaction obj) {

        String uid;
        if (obj.getUid() == null) {
            uid = UUID.randomUUID().toString();
        } else {
            uid = obj.getUid().toString();
        }

        Date createdAt;

        if (obj.getCreatedAt() == null) {
            createdAt = new Date();
        } else {
            createdAt = DateFormatter.localDateTimeToDate(obj.getCreatedAt());
        }

        return new TransactionItem(uid, uid.hashCode(), obj.getClientName(), obj.getValue(), createdAt, obj.getCreditCard());
    }

    @Override
    public Transaction create(Transaction obj) {

        TransactionItem entity = objectToPersistence(obj);

        client.getMapper().save(entity);

        return persistenceToObject(entity);
    }

    @Override
    public Optional<Transaction> findById(String uid) {

        Optional<Transaction> transaction = Optional.ofNullable(client.getMapper().load(TransactionItem.class, uid, uid.hashCode())).map(this::persistenceToObject);
        return transaction;
    }

    @Override
    public List<Transaction> list() {
        DynamoDBScanExpression exp = new DynamoDBScanExpression();

        PaginatedScanList<TransactionItem> resultSet = client.getMapper().scan(TransactionItem.class, exp, client.getConfig());

        return resultSet.stream().map(this::persistenceToObject).collect(Collectors.toList());

    }
}
