package com.persistence;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.ClientConfigurationFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.Attribute;
import com.amazonaws.services.dynamodbv2.model.*;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.AttributeType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.amazonaws.client.builder.AwsClientBuilder.*;

public class DynamoConnector {


    public static void main(String[] args) {


        try {
            new DynamoConnector().connect();

            Thread.sleep(5000);

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


        KeySchemaElement keySchema = new KeySchemaElement("identifier", KeyType.HASH);
        KeySchemaElement sort = new KeySchemaElement("sort", KeyType.RANGE);

        ArrayList<KeySchemaElement> keySchemaElements = new ArrayList<>();

        keySchemaElements.add(keySchema);

        AttributeDefinition attributen = new AttributeDefinition("asstss", ScalarAttributeType.N);
        AttributeDefinition attribute = new AttributeDefinition("atttt", ScalarAttributeType.S);
        ArrayList<AttributeDefinition> attDef = new ArrayList<>();
        attDef.add(attributen);
        attDef.add(attribute);

        ProvisionedThroughput tp = new ProvisionedThroughput(10L, 10L);
        CreateTableRequest createTableRequest = new CreateTableRequest(attDef, "name", keySchemaElements, tp);

        client.createTable(createTableRequest);


    }


}
