package com.customerhub.repository;

import com.customerhub.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    public Optional<List<Transaction>> findAllByUserId(String id);
}
