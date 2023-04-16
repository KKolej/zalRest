package com.kolej.bartosz.zalrest.auction.repository

import com.kolej.bartosz.zalrest.auction.model.Auction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AuctionRepository: JpaRepository<Auction, UUID> {
}