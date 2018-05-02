package com.persistence.sqs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.regions.ServiceAbbreviations;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.google.inject.Singleton;

import java.util.List;

@Singleton
public class SQSClient {

    private final AmazonSQS client;


    public SQSClient() {


        BasicAWSCredentials credential = new BasicAWSCredentials("accessKey", "secretKey");

        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credential);

        AwsClientBuilder.EndpointConfiguration endpointConf = new AwsClientBuilder.EndpointConfiguration("http://localhost:9324",
                Regions.US_EAST_1.toString());


        client = AmazonSQSClientBuilder.standard().withCredentials(credentialsProvider).withEndpointConfiguration(endpointConf).build();

    }

    public AmazonSQS getClient() {
        return client;
    }
}
