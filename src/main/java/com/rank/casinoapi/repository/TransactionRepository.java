package com.rank.casinoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rank.casinoapi.entity.Transaction;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query(value = "select * from transactions t " +
            " where t.player_id = :playerId " +
            " order by t.date_created desc " +
            " limit :numTransactions", nativeQuery = true)
    Optional<List<Transaction>> findLastTransactions(Integer playerId, Integer numTransactions);
}
