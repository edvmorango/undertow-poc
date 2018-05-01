package com.service;

import com.model.Transaction;
import com.persistence.dynamo.item.TransactionItem;
import com.persistence.repository.TransactionRepository;

import java.util.List;

public interface TransactionService extends GenericService<Transaction> {

}

