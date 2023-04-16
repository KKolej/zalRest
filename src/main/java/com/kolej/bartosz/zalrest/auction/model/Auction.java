package com.kolej.bartosz.zalrest.auction.model;

import com.kolej.bartosz.zalrest.customer.model.CustomUser;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Auction(CustomUser sellerData, AuctionData auctionData) {
        this.sellerData = sellerData;
        this.name = auctionData.getName();
        this.amount = auctionData.getAmount();
        this.price = auctionData.getPrice();
        this.minPrice = auctionData.getMinPrice();
        this.buyNowPrice = auctionData.getBuyNowPrice();
        this.endDate = LocalDate.now().plusDays(7);
    }
}
