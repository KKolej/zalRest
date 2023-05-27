import {Component, Input, OnInit} from '@angular/core';
import {AuctionAction, AuctionRecord} from "../../model/Auction";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ConnectionService} from "../../services/connection.service";
import {CustomUser} from "../../model/User";

@Component({
  selector: 'app-auction',
  templateUrl: './auction.component.html',
  styleUrls: ['./auction.component.scss']
})
export class AuctionComponent implements OnInit {
  @Input() auction: AuctionRecord;
  @Input() refreshAuctions: Function;

  newPrice: FormGroup
  price: FormControl
  user: CustomUser

  constructor(private connectionService: ConnectionService) { }

  ngOnInit(): void {
    this.price = new FormControl('', Validators.compose([
        Validators.required, Validators.min(this.auction.price + 0.1)
        ])
    )

    this.connectionService.getUserInfo().subscribe(r => {
      console.log('user ' , r)
      this.user =  r
    })

    this.newPrice = new FormGroup({
      price: this.price
    })
  }

  updatePrice(price: number) {
    const action = new AuctionAction(this.auction.id, false, this.price.value)
    console.log(action)
    this.connectionService.bidAuction(action).subscribe(r => {
      console.log(r)
      window.location.reload();
    })
  }

  buyNow() {
    const action = new AuctionAction(this.auction.id, true, this.auction.buyNowPrice)
    this.connectionService.buyNowAuction(action).subscribe(r => {
      console.log(r)
      window.location.reload();
    })
  }

  isAuctionOwner() {
    return this.auction.sellerData.username == this.user.username;
  }

  delete() {
    const action = new AuctionAction(this.auction.id, false, this.auction.buyNowPrice)
    this.connectionService.deleteAuction(action).subscribe(r => {
      console.log(r)
      window.location.reload();
    })
  }
}
