package com.rank.casinoapi.dto.response;

public class PlayerBalanceResponse {
    Integer id;
    Double balance;

    public PlayerBalanceResponse(Integer id, Double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
