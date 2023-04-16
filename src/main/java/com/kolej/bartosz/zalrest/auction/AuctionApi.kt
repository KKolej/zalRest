package com.kolej.bartosz.zalrest.auction

import com.kolej.bartosz.zalrest.auction.model.Auction
import com.kolej.bartosz.zalrest.auction.model.AuctionAction
import com.kolej.bartosz.zalrest.auction.model.AuctionData
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
@RestController
@RequestMapping("/auction")
class AuctionApi(private val auctionService: AuctionService) {

    @GetMapping("/all")
    fun getAllAuctions(): ResponseEntity<List<Auction>> {
        return ResponseEntity(auctionService.getAllActiveAuctions(), HttpStatus.OK)
    }

    @PostMapping("/new")
    fun createAuction(@RequestBody auctionData: AuctionData, principal: Principal): ResponseEntity<Auction> =
         ResponseEntity.ok( auctionService.createAuction(auctionData, principal.name))

    @PostMapping("/bidPrice")
    fun bidPrice(@RequestBody action: AuctionAction, principal: Principal): ResponseEntity<Any> =
        try {
            ResponseEntity.ok( auctionService.bidPrice(action, principal.name))
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }

    @PostMapping("/buyNow")
    fun buyNow(@RequestBody action: AuctionAction, principal: Principal): ResponseEntity<Any> =
            try {
                ResponseEntity.ok( auctionService.buyNow(action, principal.name))
            } catch (e: Exception) {
                ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
            }
}

