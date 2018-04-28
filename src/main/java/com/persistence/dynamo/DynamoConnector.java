package com.persistence.dynamo;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.util.Modules;
import com.inject.ApplicationModule;
import com.model.Transaction;
import com.service.TransactionService;
import com.service.impl.TransactionServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static com.amazonaws.client.builder.AwsClientBuilder.*;

public class DynamoConnector {



    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new ApplicationModule());

        try {
            TransactionService service = injector.getInstance(TransactionServiceImpl.class);

            Transaction tra = new Transaction(UUID.randomUUID(), "Eduardo", new BigDecimal(100), LocalDateTime.now());
            service.create(tra);
            Thread.sleep(5000);
            System.out.println("Finished" );
        } catch (Exception e) {
            System.out.println(
                    e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void connect() {

        BasicAWSCredentials credential = new BasicAWSCredentials("accessKey", "secretKey");

        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credential);

        EndpointConfiguration endpointConf = new EndpointConfiguration("http://localhost:8000",
                Regions.US_EAST_1.toString());

        AmazonDynamoDBAsync client = AmazonDynamoDBAsyncClientBuilder.standard().withCredentials(credentialsProvider)
                .withEndpointConfiguration(endpointConf)
                .build();


        client.deleteTableAsync("name");

        try{
            Thread.sleep(1000);

        }catch (Exception e){


        }

        ArrayList<KeySchemaElement> keySchemaElements = new ArrayList<>();
        KeySchemaElement identifier = new KeySchemaElement("tbid", KeyType.HASH);
        keySchemaElements.add(identifier);

        ArrayList<AttributeDefinition> attributes = new ArrayList<>();
        attributes.add(new AttributeDefinition().withAttributeName("tbid").withAttributeType("S" ));
        ProvisionedThroughput tp = new ProvisionedThroughput(10L, 10L);

        CreateTableRequest createTableRequest = new CreateTableRequest(attributes, "name", keySchemaElements, tp);

        client.createTable(createTableRequest);

    }






}
