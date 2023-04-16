package com.kolej.bartosz.zalrest.auction

import com.kolej.bartosz.zalrest.auction.model.Auction
import com.kolej.bartosz.zalrest.auction.model.AuctionData
import com.kolej.bartosz.zalrest.auction.repository.AuctionRepository
import com.kolej.bartosz.zalrest.customer.UserService
import org.springframework.stereotype.Service

@Service
class AuctionService(
        private val auctionRepository: AuctionRepository,
        private val userService: UserService
) {

    fun getAllAuctions(): List<Auction> = auctionRepository.findAll()

    fun createAuction(auctionData: AuctionData, name: String?): Auction {
        val userData = userService.getUser(name)
        val auction = Auction(userData, auctionData)
        return auctionRepository.save(auction)
    }
}