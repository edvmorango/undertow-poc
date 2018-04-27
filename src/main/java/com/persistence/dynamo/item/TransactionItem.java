package com.persistence.dynamo.item;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.model.Transaction;
import com.utils.DateFormatter;

import java.math.BigDecimal;
import java.util.Date;

@DynamoDBTable(tableName = "transaction")
public class TransactionItem {

    @DynamoDBHashKey(attributeName = "id")
    private String uid;

    @DynamoDBRangeKey(attributeName = "createdAt")
    private Date createdAt;

    @DynamoDBAttribute(attributeName = "clientName")
    private String clientName;

    @DynamoDBAttribute(attributeName = "value" )
    private BigDecimal value;


    public TransactionItem(String uid, Date createdAt, String clientName, BigDecimal value) {
        this.uid = uid;
        this.createdAt = createdAt;
        this.clientName = clientName;
        this.value = value;
    }

    public TransactionItem(Transaction transaction) {
        this.uid = transaction.getUid().toString();
        this.createdAt = DateFormatter.localDateTimeToDate(transaction.getCreatedAt());
        this.clientName = transaction.getClientName();
        this.value = transaction.getValue();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
