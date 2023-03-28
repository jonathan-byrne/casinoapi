package com.rank.casinoapi.dto.response;

import java.util.Date;

public class GetLastTransactionsResponse {
    private Integer id;
    private String transactionType;
    private Double amount;
    private Date dateCreated;

    public GetLastTransactionsResponse(Integer id, String transactionType, Double amount, Date dateCreated) {
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
        this.dateCreated = dateCreated;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
