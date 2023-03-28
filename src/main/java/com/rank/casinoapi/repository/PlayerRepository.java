package com.rank.casinoapi.repository;

import com.rank.casinoapi.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("select p.balance " +
            "from Player p " +
            "where p.id = :playerId")
    Optional<Double> findBalanceByPlayerId(Integer playerId);

    @Query("select p from Player p " +
            "where p.username = :username")
    Optional<Player> findPlayerByUsername(String username);

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Player save(Player player);

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    <S extends Player> List<S> saveAll(Iterable<S> entities);
}
