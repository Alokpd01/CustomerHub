package com.customerhub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    private String transactionId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "transaction_status", nullable = false)
    private String transactionStatus;

    @Column(name = "amount_transferred", nullable = false)
    private Long amountTransferred;
}
