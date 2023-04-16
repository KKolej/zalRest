import { Component, OnInit } from '@angular/core';
import { ConnectionService } from 'src/app/services/connection.service';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
import {AuctionRecord} from "../../model/Auction";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  auctions: AuctionRecord[] = []
  auctionForm: FormGroup
  name: FormControl
  amount: FormControl
  price: FormControl
  minPrice: FormControl
  buyNowPrice: FormControl

  constructor(private connectionService: ConnectionService) { }

  ngOnInit(): void {
    this.getAllAuctions();

    this.name = new FormControl('', Validators.required)
    this.amount = new FormControl('', Validators.required)
    this.minPrice = new FormControl('', Validators.required)
    this.buyNowPrice = new FormControl('', Validators.required)
    this.price = new FormControl('', Validators.required)

    this.auctionForm = new FormGroup({
      name: this.name,
      amount: this.amount,
      minPrice: this.minPrice,
      price: this.price,
      buyNowPrice: this.buyNowPrice
    })
  }

  saveDocument(value: any): void {
    this.connectionService.createNewAuction(value).subscribe(r => {
      console.log(r)
      this.getAllAuctions()
    })
  }

  getAllAuctions(): void {
    this.connectionService.getAllAuctions().subscribe(r => {
      console.log(r)
      this.auctions = r
    })
  }
}
