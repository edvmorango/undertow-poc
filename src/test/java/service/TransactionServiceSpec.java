package service;


import com.model.Transaction;
import com.mscharhag.oleaster.matcher.util.Expectations;
import com.mscharhag.oleaster.runner.OleasterRunner;
import factory.TransactionFactory;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.UUID;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

@RunWith(OleasterRunner.class)
public class TransactionServiceSpec {

    {
        describe("TransactionServiceSpec", () -> {

            it("Should create a Transaction", () -> {


                Transaction transaction = new TransactionFactory().getNewTransaction();

                expect(transaction).toBeNotNull();


            });


        });

    }

}