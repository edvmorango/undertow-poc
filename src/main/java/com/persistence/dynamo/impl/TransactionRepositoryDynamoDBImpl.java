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


public class TransactionRepositoryDynamoDBImpl implements TransactionRepository<Transaction, TransactionItem> {

    @Inject
    private DynamoDBClient client;

    @Override
    public Transaction persistenceToObject(TransactionItem entity) {

        UUID uuid = UUID.fromString(entity.getUid());
        LocalDateTime createdAt = DateFormatter.dateToLocalDateTime(entity.getCreatedAt());

        return new Transaction(uuid, entity.getClientName(), entity.getValue(), createdAt);

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

        return new TransactionItem(uid, uid.hashCode(), obj.getClientName(), obj.getValue(), createdAt);
    }

    @Override
    public TransactionItem create(TransactionItem obj) {

        client.getMapper().save(obj);
        System.out.println(obj.getUid());

        return obj;
    }
    @Override
    public Optional<TransactionItem> findById(String uid) {

        return Optional.ofNullable(client.getMapper().load(TransactionItem.class, uid, uid.hashCode()));

    }

    @Override
    public List<TransactionItem> list() {
        DynamoDBScanExpression exp = new DynamoDBScanExpression();

        PaginatedScanList<TransactionItem> resultSet = client.getMapper().scan(TransactionItem.class, exp, client.getConfig());

        return resultSet.stream().collect(Collectors.toList());

    }
}
