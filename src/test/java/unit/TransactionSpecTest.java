package unit;


import com.model.Transaction;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.UUID;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

@RunWith(OleasterRunner.class)
public class TransactionSpecTest {

    {
        describe("TransactionSpec", () -> {

            it("Should create a Transaction", () -> {

                Transaction client = new Transaction(UUID.randomUUID(), "Client Name", new BigDecimal(100));

            });


        });

    }

}
