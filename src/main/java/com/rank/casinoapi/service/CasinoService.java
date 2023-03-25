package com.rank.casinoapi.service;

import com.rank.casinoapi.dto.response.PlayerBalanceResponse;
import com.rank.casinoapi.dto.request.UpdatePlayerBalanceRequest;
import com.rank.casinoapi.dto.response.UpdatePlayerBalanceResponse;
import com.rank.casinoapi.entity.Player;
import com.rank.casinoapi.entity.Transaction;
import com.rank.casinoapi.exception.BadRequestException;
import com.rank.casinoapi.repository.PlayerRepository;
import com.rank.casinoapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CasinoService {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Transactional
    public PlayerBalanceResponse findBalanceByPlayerId(Integer playerId) {

        PlayerBalanceResponse playerBalanceResponseDto;
        Optional<Double> playerBalance = playerRepository.findBalanceByPlayerId(playerId);

        if(playerBalance.isPresent()) {
            playerBalanceResponseDto = new PlayerBalanceResponse(playerId, playerBalance.get());
        } else {
            throw new BadRequestException();
        }

        return playerBalanceResponseDto;
    }

    @Transactional
    public UpdatePlayerBalanceResponse updatePlayerBalance(UpdatePlayerBalanceRequest request) {

        if(request.getAmount() < 0) {
            throw new BadRequestException();
        }

        UpdatePlayerBalanceResponse response = new UpdatePlayerBalanceResponse();
        Optional<Player> player = playerRepository.findById(request.getPlayerId());

        if(player.isPresent()) {

            Player playerActual = player.get();

            if(request.getTransactionType().equalsIgnoreCase("wager") &&
                    (playerActual.getBalance() - request.getAmount() < 0) ) {
                throw new BadRequestException();
            }

            if(request.getTransactionType().equalsIgnoreCase("wager")) {
                playerActual.setBalance(playerActual.getBalance() - request.getAmount());
            } else if (request.getTransactionType().equalsIgnoreCase("win")) {
                playerActual.setBalance(playerActual.getBalance() + request.getAmount());
            }

            Transaction transaction = new Transaction(request.getTransactionType(), playerActual.getBalance(), playerActual);
            transactionRepository.save(transaction);
            playerRepository.save(playerActual);
            response.setTransactionId(transaction.getId());
            response.setBalance(playerActual.getBalance());
        } else {
            throw new BadRequestException();
        }

        return response;
    }
}
