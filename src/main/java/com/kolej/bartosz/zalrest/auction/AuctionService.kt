package com.kolej.bartosz.zalrest.auction

import com.kolej.bartosz.zalrest.auction.model.Auction
import com.kolej.bartosz.zalrest.auction.model.AuctionAction
import com.kolej.bartosz.zalrest.auction.model.AuctionData
import com.kolej.bartosz.zalrest.auction.repository.AuctionRepository
import com.kolej.bartosz.zalrest.customer.UserService
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.UnprocessableEntity
import java.lang.RuntimeException

@Service
class AuctionService(
        private val auctionRepository: AuctionRepository,
        private val userService: UserService
) {

    fun getAllActiveAuctions(): List<Auction> = auctionRepository.findAll().filter { r -> r.isActive}

    fun createAuction(auctionData: AuctionData, name: String): Auction {
        val userData = userService.getUser(name)
        val auction = Auction(userData, auctionData)
        return auctionRepository.save(auction)
    }

    fun bidPrice(action: AuctionAction, name: String): Auction {
        val userData = userService.getUser(name)
        val auction: Auction = auctionRepository.getReferenceById(action.id)

        validate(auction, name, action)

        auction.price = action.price
        auction.buyerData = userData

        return auctionRepository.save(auction)
    }


    fun buyNow(action: AuctionAction, name: String): Auction {
        val userData = userService.getUser(name)
        val auction: Auction = auctionRepository.getReferenceById(action.id)
        validate(auction, name, action)

        auction.price = action.price
        auction.buyerData = userData
        auction.isActive = false

        return auctionRepository.save(auction)
    }

    private fun validate(auction: Auction, name: String, action: AuctionAction) {
        if (auction.sellerData.username == name)
            throw RuntimeException("seller cannot buy Auction")
        if (!action.buyNow && auction.price > action.price)
            throw RuntimeException("Amount must be bigger")
    }
}