package com.customerhub.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private String transactionId;
    private String userId;

    @NotEmpty(message = "Username should not be null or empty")
    private String username;
    private String transactionType;
    private String transactionStatus;

    @NotEmpty(message = "Transfer amount should not be null or empty")
    private Long amountTransferred;

}
