package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Entity class representing a bidList in the database
 */
@Entity
@Table(name = "Bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidListId")
    private Integer bidListId;
    @NotBlank(message = "Account is mandatory")
    @Column(name = "account", nullable = false)
    @Size(max = 100)
    private String account;
    @NotBlank(message = "Type is mandatory")
    @Column(name = "type", nullable = false)
    @Size(max = 100)
    private String type;
    @Column(name = "bidQuantity")
    @Min(value = 0)
    private Double bidQuantity;
    @Column(name = "askQuantity")
    @Min(value = 0)
    private Double askQuantity;
    @Column(name = "bid")
    @Min(value = 0)
    private Double bid;
    @Column(name = "ask")
    @Min(value = 0)
    private Double ask;
    @Column(name = "benchmark")
    @Size(max = 100)
    private String benchmark;
    @Column(name = "bidListDate")
    private Timestamp bidListDate;
    @Column(name = "commentary")
    @Size(max = 100)
    private String commentary;
    @Column(name = "security")
    @Size(max = 100)
    private String security;
    @Column(name = "status")
    @Size(max = 100)
    private String status;
    @Column(name = "trader")
    @Size(max = 100)
    private String trader;
    @Column(name = "book")
    @Size(max = 100)
    private String book;
    @Column(name = "creationName")
    @Size(max = 100)
    private String creationName;
    @Column(name = "creationDate")
    private Timestamp creationDate;
    @Column(name = "revisionName")
    @Size(max = 100)
    private String revisionName;
    @Column(name = "revisionDate")
    private Timestamp revisionDate;
    @Column(name = "dealName")
    @Size(max = 100)
    private String dealName;
    @Column(name = "dealType")
    @Size(max = 100)
    private String dealType;
    @Column(name = "sourceListId")
    @Size(max = 100)
    private String sourceListId;
    @Column(name = "side")
    @Size(max = 100)
    private String side;

    public Integer getBidListId() {
        return bidListId;
    }

    public void setBidListId(Integer bidListId) {
        this.bidListId = bidListId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public Double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getBidListDate() {
        return bidListDate;
    }

    public void setBidListDate(Timestamp bidListDate) {
        this.bidListDate = bidListDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Timestamp getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
