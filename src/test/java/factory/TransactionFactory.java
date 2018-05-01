package factory;

import com.model.Transaction;
import com.model.enums.CreditCard;
import com.model.enums.TransactionStatus;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.types.enums.RandomType;
import net.andreinc.mockneat.unit.objects.Constructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class TransactionFactory {

    private MockNeat gen =  new MockNeat(RandomType.SECURE, MockConf.seed);

    public Transaction getNewTransaction() {

        Transaction transaction = gen.constructor(Transaction.class).params().val();


        transaction.setUid(UUID.fromString(gen.uuids().val()));
        transaction.setClientName(gen.names().full().val());
        transaction.setValue(gen.doubles().range(0.01, 100000.00).map(BigDecimal::new).val());
        transaction.setCreatedAt(gen.localDates().between(LocalDate.MIN,LocalDate.MAX).val().atStartOfDay());
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setCreditCard(gen.from(CreditCard.class).val());
        return transaction;

    }

    public List<Transaction> getListOfTransactions(Integer n){

       return gen.bools().map( (u) -> getNewTransaction()).list(n).val();

    }

    public Transaction getNewTransactionWithUUID(UUID uid){

        Transaction newTransaction = getNewTransaction();
        newTransaction.setUid(uid);
        return newTransaction;

    }

    public Transaction getNewTransactionWithTransactionStatus(TransactionStatus status){

        Transaction newTransaction = getNewTransaction();
        newTransaction.setTransactionStatus(status);
        return newTransaction;

    }

    public Transaction getNewTransactionWithTransactionValue(BigDecimal value){

        Transaction newTransaction = getNewTransaction();
        newTransaction.setValue(value);
        return newTransaction;

    }


}
