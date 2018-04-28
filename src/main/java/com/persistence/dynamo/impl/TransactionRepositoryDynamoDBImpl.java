package com.persistence.dynamo.impl;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
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

        String uid = null;
        if(obj.getUid() != null)
            uid = obj.getUid().toString();

        Date createdAt = DateFormatter.localDateTimeToDate(obj.getCreatedAt());

        return new TransactionItem(uid, obj.getClientName(), obj.getValue(), createdAt);
    }

    @Override
    public TransactionItem create(TransactionItem obj) {

        client.getMapper().save(obj);

        System.out.println(obj.getUid());

        return obj;
    }

    @Override
    public TransactionItem findById(String uid) {

      return  client.getMapper().load(TransactionItem.class, uid);

    }

    @Override
    public List<TransactionItem> list() {
        DynamoDBScanExpression exp = new DynamoDBScanExpression();

        PaginatedScanList<TransactionItem> resultSet = client.getMapper().scan(TransactionItem.class, exp, client.getConfig());

        return resultSet.stream().collect(Collectors.toList());

    }
}
