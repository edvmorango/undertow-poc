package com.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.model.Transaction;
import com.persistence.dynamo.DynamoConnector;
import com.persistence.dynamo.DynamoDBClient;
import com.persistence.dynamo.impl.TransactionRepositoryDynamoDBImpl;
import com.persistence.repository.TransactionRepository;
import com.service.impl.TransactionServiceImpl;
import org.omg.IOP.TransactionService;

import java.util.ServiceLoader;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        ServiceLoader<Module> modules = ServiceLoader.load(Module.class);

        for(Module m: modules) {
            install(m);
        }

        bind(DynamoDBClient.class).asEagerSingleton();
        bind(TransactionRepository.class).annotatedWith(Names.named("TransactionRepositoryDynamo")).to(TransactionRepositoryDynamoDBImpl.class);

//        bind(TransactionService.class).to(TransactionServiceImpl.class);

    }

}
