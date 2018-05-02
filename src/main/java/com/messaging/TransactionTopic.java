package com.messaging;


import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.model.Transaction;
import com.utils.async.CompletablePromise;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


public class TransactionTopic {

    private static final String PENDING_TRANSACTIONS = "PENDING_TRANSACTIONS";
    private static final String FAILED_TRANSACTIONS = "FAILED_TRANSACTIONS";
    private static final String CONCLUDED_TRANSACTIONS = "CONCLUDED_TRANSACTIONS";


    private static ObjectMapper om = new ObjectMapper();
    private SNSClient sns;
    private String pendingArn;

    @Inject
    public TransactionTopic(SNSClient sns) {

        this.sns = sns;

        pendingArn = sns.getClient().createTopic(PENDING_TRANSACTIONS).getTopicArn();

        System.out.println(pendingArn);
    }


    public void publishPendingTransaction(Transaction t) throws Exception {

        String body = om.writeValueAsString(t);
        Future<PublishResult> action = sns.getClient().publishAsync(pendingArn, om.writeValueAsString(body));


        CompletablePromise<PublishResult> promise = new CompletablePromise<>(action);

        Thread.sleep(2000);

        promise.whenComplete((r, f) -> {
            System.out.println(r.getMessageId());
            System.out.println(r.toString());
        });

    }

}
