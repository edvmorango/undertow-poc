package com.persistence.sqs;


import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.model.Transaction;

import java.util.Queue;

public class TransactionQueue {

    private static final String PENDING_TRANSACTIONS = "PENDING_TRANSACTIONS";
    private static final String FAILED_TRANSACTIONS = "FAILED_TRANSACTIONS";
    private static final String CONCLUDED_TRANSACTIONS = "CONCLUDED_TRANSACTIONS";


    private static ObjectMapper om = new ObjectMapper();
    private SQSClient client;
    private String pendingUrl;

    @Inject
    public TransactionQueue(SQSClient client) {

        this.client = client;

        client.getClient().createQueue(PENDING_TRANSACTIONS);
        client.getClient().createQueue(FAILED_TRANSACTIONS);
        client.getClient().createQueue(CONCLUDED_TRANSACTIONS);

        pendingUrl = client.getClient().getQueueUrl(PENDING_TRANSACTIONS).getQueueUrl();

    }

    public void enqueuePendingTransaction(Transaction t) throws Exception {

        String body = om.writeValueAsString(t);

        SendMessageRequest msg = new SendMessageRequest()
                .withQueueUrl(pendingUrl)
                .withMessageBody(body);

        client.getClient().sendMessage(msg);

        System.out.println("Supposedly queuing message");

    }

    public void getPendingTransactions() throws Exception {

        ReceiveMessageResult res = client.getClient().receiveMessage(pendingUrl);

        res.getMessages().stream().forEach(System.out::println);


    }

}
