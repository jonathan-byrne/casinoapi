package com.rank.casinoapi.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String transactionType;
    private Double amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @ManyToOne
    @JoinColumn(name="player_id", nullable=false)
    private Player player;

    public Transaction() {

    }

    public Transaction(String transactionType, Double amount, Date dateCreated, Player player) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.dateCreated = dateCreated;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
