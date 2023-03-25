package com.rank.casinoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rank.casinoapi.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
