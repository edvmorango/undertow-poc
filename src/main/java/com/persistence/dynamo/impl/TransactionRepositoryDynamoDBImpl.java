package com.persistence.dynamo.impl;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.google.inject.Inject;
import com.model.Transaction;
import com.model.enums.TransactionStatus;
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
    public Transaction persistenceToObject(TransactionItem e) {

        UUID uuid = UUID.fromString(e.getUid());
        LocalDateTime createdAt = DateFormatter.dateToLocalDateTime(e.getCreatedAt());

        return new Transaction(uuid, e.getClientName(), e.getValue(), createdAt, e.getCreditCard(), e.getTransactionStatus(), e.getHistoric());

    }

    @Override
    public TransactionItem objectToPersistence(Transaction obj) throws Exception {

        try {

            Date createdAt = DateFormatter.localDateTimeToDate(obj.getCreatedAt());

            return new TransactionItem(obj.getUid().toString(), createdAt.getTime(), obj.getClientName(), obj.getValue(), createdAt, obj.getCreditCard(), obj.getTransactionStatus(), obj.getHistoric());

        } catch (Exception e) {

            throw e;

        }

    }

    @Override
    public Transaction create(Transaction obj) throws Exception {
        obj.setUid(UUID.randomUUID());
        obj.setCreatedAt(LocalDateTime.now());
        obj.setTransactionStatus(TransactionStatus.PENDING);

        TreeSet<String> historic = new TreeSet<>();

        historic.add(LocalDateTime.now() + " - Creating transaction ");

        obj.setHistoric(historic);

        TransactionItem entity = objectToPersistence(obj);
        client.getMapper().save(entity);

        return persistenceToObject(entity);
    }

    @Override
    public Optional<Transaction> findById(String uid) {


        HashMap<String, AttributeValue> params = new HashMap<>();
        params.put(":uid", new AttributeValue().withS(uid));

        DynamoDBQueryExpression<TransactionItem> exp = new DynamoDBQueryExpression<>();
        exp.withKeyConditionExpression("uid = :uid");
        exp.withExpressionAttributeValues(params);

        PaginatedQueryList<TransactionItem> query = client.getMapper().query(TransactionItem.class, exp, client.getConfig());

        Optional<Transaction> transaction = Optional.ofNullable(query.get(0)).map(this::persistenceToObject);

        return transaction;
    }

    @Override
    public List<Transaction> list() {
        DynamoDBScanExpression exp = new DynamoDBScanExpression();

        PaginatedScanList<TransactionItem> resultSet = client.getMapper().scan(TransactionItem.class, exp, client.getConfig());

        return resultSet.stream().map(this::persistenceToObject).collect(Collectors.toList());

    }
}
