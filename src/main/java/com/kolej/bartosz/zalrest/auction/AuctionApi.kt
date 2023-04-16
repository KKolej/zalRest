package com.kolej.bartosz.zalrest.auction

import com.kolej.bartosz.zalrest.auction.model.Auction
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
        return ResponseEntity(auctionService.getAllAuctions(), HttpStatus.OK)
    }

    @PostMapping("/new")
    fun createAuction(@RequestBody auctionData: AuctionData, principal: Principal): ResponseEntity<Auction> {
        return ResponseEntity.ok( auctionService.createAuction(auctionData, principal.name))
    }
}