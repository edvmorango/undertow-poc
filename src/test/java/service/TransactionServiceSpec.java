package service;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.model.Transaction;
import com.mscharhag.oleaster.runner.OleasterRunner;
import com.persistence.dynamo.DynamoDBClient;
import com.persistence.dynamo.impl.TransactionRepositoryDynamoDBImpl;
import com.persistence.dynamo.item.TransactionItem;
import factory.TransactionFactory;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
import static org.mockito.Mockito.*;

@RunWith(OleasterRunner.class)
public class TransactionServiceSpec {


    {
        describe("TransactionServiceSpec", () -> {

            it("Should convert/undo Transaction to TransactionItem", () -> {

                DynamoDBClient client = mock(DynamoDBClient.class);
                TransactionRepositoryDynamoDBImpl rep = new TransactionRepositoryDynamoDBImpl(client);

                Transaction tr = new TransactionFactory().getNewTransaction();

                TransactionItem item = rep.objectToPersistence(tr);
                Transaction undo = rep.persistenceToObject(item);

                expect(item.getSid()).toBeNotNull();
                expect(undo.getUid().equals(tr.getUid()));
                expect(undo.getCreatedAt().compareTo(tr.getCreatedAt())).toEqual(0L);

            });

            it("Should create a new Transaction", () -> {


                DynamoDBMapper mapper = mock(DynamoDBMapper.class);
                DynamoDBClient client = mock(DynamoDBClient.class);

                TransactionRepositoryDynamoDBImpl rep = new TransactionRepositoryDynamoDBImpl(client);
//                TransactionQueue queue;
//
//                TransactionService service = new TransactionServiceImpl(rep);
//                Transaction transaction = new TransactionFactory().getNewTransactionWithUUID(null);
//
//                when(client.getMapper()).thenReturn(mapper);
//
//                doNothing().when(mapper).save(any(TransactionItem.class));
//
//                Transaction newTransaction = service.create(transaction);
//
//                expect(newTransaction.getUid()).toBeNotNull();
//                expect(newTransaction.getTransactionStatus()).toEqual(TransactionStatus.PENDING);

            });


        });

    }

}