package com.rank.casinoapi.controller;

import com.rank.casinoapi.dto.request.GetLastTransactionsRequest;
import com.rank.casinoapi.dto.request.UpdatePlayerBalanceRequest;
import com.rank.casinoapi.dto.response.GetLastTransactionsResponse;
import com.rank.casinoapi.dto.response.PlayerBalanceResponse;
import com.rank.casinoapi.dto.response.UpdatePlayerBalanceResponse;
import com.rank.casinoapi.service.CasinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/casino")
public class CasinoController {

    @Autowired
    private CasinoService casinoService;

    @GetMapping("/player/{playerId}/balance")
    public PlayerBalanceResponse getPlayerBalance(@PathVariable Integer playerId) {

        return casinoService.findBalanceByPlayerId(playerId);
    }

    @PostMapping("/player/{playerId}/balance/update")
    public UpdatePlayerBalanceResponse updatePlayerBalance(@RequestBody UpdatePlayerBalanceRequest request) {

        return casinoService.updatePlayerBalance(request);
    }

    @PostMapping("/admin/player/transactions")
    public List<GetLastTransactionsResponse> getLastTenTransactions(@RequestBody GetLastTransactionsRequest request) {

        return casinoService.findLastTransactionsByUsernameAndLimit(request.getUsername(), 10);
    }
}
