package com.customerhub.mapper;

import com.customerhub.dto.TransactionDto;
import com.customerhub.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDto mapToTransactionDto(Transaction transaction){

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId(transaction.getTransactionId());
        transactionDto.setUserId(transaction.getUserId());
        transactionDto.setUsername(transaction.getUsername());
        transactionDto.setTransactionType(transaction.getTransactionType());
        transactionDto.setAmountTransferred(transaction.getAmountTransferred());
        transactionDto.setTransactionStatus(transaction.getTransactionStatus());

        return transactionDto;
    }

    public Transaction mapToTransaction(TransactionDto transactionDto){

        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionDto.getTransactionId());
        transaction.setUserId(transactionDto.getUserId());
        transaction.setUsername(transactionDto.getUsername());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setAmountTransferred(transactionDto.getAmountTransferred());
        transaction.setTransactionStatus(transactionDto.getTransactionStatus());

        return transaction;
    }
}
