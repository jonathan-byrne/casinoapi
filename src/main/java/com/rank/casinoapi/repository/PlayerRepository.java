package com.rank.casinoapi.repository;

import com.rank.casinoapi.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("select p.balance " +
            "from Player p " +
            "where p.id = :playerId")
    public Optional<Double> findBalanceByPlayerId(Integer playerId);

    @Query("select p from Player p " +
            "where p.username = :username")
    public Optional<Player> findPlayerByUsername(String username);
}
