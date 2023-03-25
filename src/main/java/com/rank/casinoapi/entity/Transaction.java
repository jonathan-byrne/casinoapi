package com.rank.casinoapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String transactionType;
    private Double amount;
    @ManyToOne
    @JoinColumn(name="player_id", nullable=false)
    private Player player;

    public Transaction() {

    }

    public Transaction(String transactionType, Double amount, Player player) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.player = player;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
