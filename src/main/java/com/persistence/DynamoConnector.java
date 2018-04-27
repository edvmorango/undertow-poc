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
import org.w3c.dom.Attr;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.amazonaws.client.builder.AwsClientBuilder.*;

public class DynamoConnector {


    public static void main(String[] args) {

        try {
            new DynamoConnector().connect();

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
