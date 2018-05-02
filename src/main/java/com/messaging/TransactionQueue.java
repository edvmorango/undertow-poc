package com.messaging;


import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.model.Transaction;


public class TransactionQueue {

    private static final String PENDING_TRANSACTIONS = "PENDING_TRANSACTIONS";
    private static final String FAILED_TRANSACTIONS = "FAILED_TRANSACTIONS";
    private static final String CONCLUDED_TRANSACTIONS = "CONCLUDED_TRANSACTIONS";


    private static ObjectMapper om = new ObjectMapper();
    private SQSClient sqs;
    private SNSClient sns;
    private String pendingUrl;

    @Inject
    public TransactionQueue(SQSClient sqs, SNSClient sns) {

        this.sqs = sqs;
        this.sns = sns;

        sqs.getClient().createQueue(PENDING_TRANSACTIONS);
        sqs.getClient().createQueue(FAILED_TRANSACTIONS);
        sqs.getClient().createQueue(CONCLUDED_TRANSACTIONS);

        sns.getClient().createTopicAsync(PENDING_TRANSACTIONS);

        pendingUrl = sqs.getClient().getQueueUrl(PENDING_TRANSACTIONS).getQueueUrl();


    }

    public void enqueuePendingTransaction(Transaction t) throws Exception {

        String body = om.writeValueAsString(t);

        SendMessageRequest msg = new SendMessageRequest()
                .withQueueUrl(pendingUrl)
                .withMessageBody(body);

        sqs.getClient().sendMessage(msg);

        System.out.println("Supposedly queuing message");

    }

    public void getPendingTransactions() throws Exception {

        ReceiveMessageResult res = sqs.getClient().receiveMessage(pendingUrl);

        res.getMessages().stream().forEach(System.out::println);

    }

}
