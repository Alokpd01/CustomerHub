package com.customerhub.controller;

import com.customerhub.dto.TransactionDto;
import com.customerhub.dto.UserDto;
import com.customerhub.service.impl.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class TransactionController {

    private TransactionServiceImpl transactionService;

    @PostMapping("transfer/{id}")
    public TransactionDto transfer(@PathVariable String id, @RequestBody UserDto userDto){
        return transactionService.transfer(id, userDto);
    }

    @GetMapping("transaction/{id}")
    public TransactionDto getTransactionById(@PathVariable String id){
        return transactionService.getTransactionDetailById(id);
    }

    @GetMapping("transaction/status/{id}")
    public String getTransactionStatus(@PathVariable String id){
        return transactionService.getTransactionStatusById(id);
    }

//    @GetMapping("transaction/user")
//    public List<TransactionDto> getTransactionByUserId(@PathVariable String id){
//        return transactionService.getTransactionDetailsByUserId(id);
//    }

    @GetMapping("transaction")
    public List<TransactionDto> getALlTransaction(){
        return transactionService.getAllTransactionDetails();
    }

}
