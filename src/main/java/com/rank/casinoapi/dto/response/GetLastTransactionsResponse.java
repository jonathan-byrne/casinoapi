package com.rank.casinoapi.dto.response;

public class GetLastTransactionsResponse {
    private Integer id;
    private String transactionType;
    private Double amount;

    public GetLastTransactionsResponse(Integer id, String transactionType, Double amount) {
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
