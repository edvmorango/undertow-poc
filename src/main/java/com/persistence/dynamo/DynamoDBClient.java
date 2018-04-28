package com.persistence.dynamo;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.google.inject.Singleton;
import com.persistence.dynamo.item.TransactionItem;

@Singleton
public class DynamoDBClient {

    private final AmazonDynamoDBAsync client;
    private final DynamoDBMapper mapper;

    public DynamoDBClient() {

        BasicAWSCredentials credential = new BasicAWSCredentials("accessKey", "secretKey");

        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credential);

        AwsClientBuilder.EndpointConfiguration endpointConf = new AwsClientBuilder.EndpointConfiguration("http://localhost:8000",
                Regions.US_EAST_1.toString());

        AmazonDynamoDBAsync client = AmazonDynamoDBAsyncClientBuilder.standard().withCredentials(credentialsProvider)
                .withEndpointConfiguration(endpointConf)
                .build();

        this.client = client;
        this.mapper = new DynamoDBMapper(client);

        createTables();
    }

    private void createTables(){

        ProvisionedThroughput throughput = new ProvisionedThroughput(10L, 10L);

        mapper.newTableMapper(TransactionItem.class).createTableIfNotExists(throughput);

    }


    public DynamoDBMapper getMapper() {
        return mapper;
    }

    public AmazonDynamoDBAsync getClient() {
        return client;
    }
}
