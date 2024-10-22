package com.customerhub.service.impl;

import com.customerhub.dto.TransactionDto;
import com.customerhub.dto.UserDto;
import com.customerhub.entity.User;
import com.customerhub.exception.InsufficientBalanceException;
import com.customerhub.exception.ResourceNotFoundException;
import com.customerhub.mapper.TransactionMapper;
import com.customerhub.repository.TransactionRepository;
import com.customerhub.repository.UserRepository;
import com.customerhub.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;
    private UserRepository userRepository;

    @Override
    public TransactionDto transfer(String id, UserDto userDto) {

        User sender = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));
        User receiver = userRepository.findByUsername(userDto.getUsername()).orElseThrow(() -> new ResourceNotFoundException("User", "Username", userDto.getUsername()));
        TransactionDto senderTransactionDetail = new TransactionDto();
        TransactionDto receiverTransactionDetail = new TransactionDto();

        Long transferAmount = userDto.getAmount();

        if(sender.getAmount() < transferAmount ){
            senderTransactionDetail.setTransactionId(UUID.randomUUID().toString());
            senderTransactionDetail.setTransactionStatus("FAIL");
            senderTransactionDetail.setTransactionType("Sender");
            senderTransactionDetail.setAmountTransferred(transferAmount);
            senderTransactionDetail.setUserId(sender.getUserId());
            senderTransactionDetail.setUsername(receiver.getUsername());
            transactionRepository.save(transactionMapper.mapToTransaction(senderTransactionDetail));
            throw new InsufficientBalanceException("Insufficient Balance");
        }

        sender.setAmount(sender.getAmount() - transferAmount);
        receiver.setAmount(receiver.getAmount() + transferAmount);

        userRepository.save(sender);
        userRepository.save(receiver);

        senderTransactionDetail.setTransactionId(UUID.randomUUID().toString());
        senderTransactionDetail.setTransactionStatus("PASS");
        senderTransactionDetail.setTransactionType("Sent");
        senderTransactionDetail.setAmountTransferred(transferAmount);
        senderTransactionDetail.setUserId(sender.getUserId());
        senderTransactionDetail.setUsername(receiver.getUsername());
        transactionRepository.save(transactionMapper.mapToTransaction(senderTransactionDetail));

        receiverTransactionDetail.setTransactionId(UUID.randomUUID().toString());
        receiverTransactionDetail.setTransactionStatus("PASS");
        receiverTransactionDetail.setTransactionType("Received");
        receiverTransactionDetail.setAmountTransferred(transferAmount);
        receiverTransactionDetail.setUserId(receiver.getUserId());
        receiverTransactionDetail.setUsername(sender.getUsername());
        transactionRepository.save(transactionMapper.mapToTransaction(receiverTransactionDetail));

        return senderTransactionDetail;
    }

    @Override
    public TransactionDto getTransactionDetailById(String id) {
        return transactionMapper.mapToTransactionDto(transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction", "TID", id)));
    }

    @Override
    public List<TransactionDto> getTransactionDetailsByUserId(String id) {
        return transactionRepository
                .findAllByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", "UserID", id))
                .stream()
                .map((transaction -> transactionMapper.mapToTransactionDto(transaction)))
                .toList();
    }

    @Override
    public String getTransactionStatusById(String id) {
        return transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction", "ID", id)).getTransactionStatus();
    }

    @Override
    public List<TransactionDto> getAllTransactionDetails() {
        return transactionRepository.findAll().stream().map((transaction -> transactionMapper.mapToTransactionDto(transaction))).toList();
    }
}
