package com.customerhub.service;

import com.customerhub.dto.TransactionDto;
import com.customerhub.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface TransactionService {

    public TransactionDto transfer(String id, UserDto userDto);
    public TransactionDto getTransactionDetailById(String id);
    public List<TransactionDto> getTransactionDetailsByUserId(String id);
    public String getTransactionStatusById(String id);
    public List<TransactionDto> getAllTransactionDetails();

}
