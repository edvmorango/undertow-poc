package com.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.messaging.SNSClient;
import com.persistence.dynamo.DynamoDBClient;
import com.persistence.dynamo.impl.TransactionRepositoryDynamoDBImpl;
import com.persistence.repository.TransactionRepository;
import com.messaging.SQSClient;
import com.service.TransactionService;
import com.service.impl.TransactionServiceImpl;

import java.util.ServiceLoader;

public class ApplicationModule extends AbstractModule {

    public static Injector injector = Guice.createInjector(new ApplicationModule());

    @Override
    protected void configure() {
        ServiceLoader<Module> modules = ServiceLoader.load(Module.class);

        for(Module m: modules) {
            install(m);
        }

        bind(DynamoDBClient.class).asEagerSingleton();
        bind(SQSClient.class).asEagerSingleton();
        bind(SNSClient.class).asEagerSingleton();

        bind(TransactionRepository.class).annotatedWith(Names.named("TransactionRepositoryDynamo")).to(TransactionRepositoryDynamoDBImpl.class);
        bind(TransactionService.class).to(TransactionServiceImpl.class);


    }

}
