package com.persistence.dynamo.item;


import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.model.Transaction;
import com.utils.DateFormatter;

import java.math.BigDecimal;
import java.util.Date;

@DynamoDBTable(tableName = "transaction")
public class TransactionItem {

    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    private String uid;

    @DynamoDBAttribute(attributeName = "clientName")
    private String clientName;

    @DynamoDBAttribute(attributeName = "value" )
    private BigDecimal value;


    @DynamoDBRangeKey(attributeName = "createdAt")
    private Date createdAt;


    public TransactionItem(String uid, String clientName, BigDecimal value, Date createdAt) {
        this.uid = uid;
        this.clientName = clientName;
        this.value = value;
        this.createdAt = createdAt;
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
