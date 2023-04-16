package com.kolej.bartosz.zalrest.auction.model

import java.math.BigDecimal
import java.util.UUID

data class AuctionData(
        val name: String,
        val amount: BigDecimal,
        val price: BigDecimal,
        val minPrice: BigDecimal,
        val buyNowPrice: BigDecimal,
)

data class AuctionAction(
        val id: UUID,
        val buyNow: Boolean = false,
        val price: BigDecimal? = null
)