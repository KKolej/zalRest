package com.kolej.bartosz.zalrest.auction.model;

import com.kolej.bartosz.zalrest.customer.model.CustomUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CustomUser sellerData;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CustomUser buyerData;
    private String name;
    private BigDecimal amount;
    private BigDecimal price;
    private BigDecimal minPrice;
    private BigDecimal buyNowPrice;
    private LocalDate endDate;
    private boolean active;

    public Auction(CustomUser sellerData, AuctionData auctionData) {
        this.sellerData = sellerData;
        this.name = auctionData.getName();
        this.amount = auctionData.getAmount();
        this.price = auctionData.getPrice();
        this.minPrice = auctionData.getMinPrice();
        this.buyNowPrice = auctionData.getBuyNowPrice();
        this.endDate = LocalDate.now().plusDays(7);
        this.active = true;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CustomUser getSellerData() {
        return sellerData;
    }

    public void setSellerData(CustomUser sellerData) {
        this.sellerData = sellerData;
    }

    public CustomUser getBuyerData() {
        return buyerData;
    }

    public void setBuyerData(CustomUser buyerData) {
        this.buyerData = buyerData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getBuyNowPrice() {
        return buyNowPrice;
    }

    public void setBuyNowPrice(BigDecimal buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @NotNull
    public Auction updateFromData(@NotNull AuctionData auctionData) {
        this.setName(auctionData.getName());
        return this;
    }
}
