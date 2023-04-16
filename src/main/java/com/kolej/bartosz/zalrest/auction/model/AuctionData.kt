package com.kolej.bartosz.zalrest.auction.model

import java.math.BigDecimal

data class AuctionData(
        val name: String,
        val amount: BigDecimal,
        val price: BigDecimal,
        val minPrice: BigDecimal,
        val buyNowPrice: BigDecimal,
)