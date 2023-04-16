import {Component, Input, OnInit} from '@angular/core';
import {AuctionRecord} from "../../model/Auction";

@Component({
  selector: 'app-auction',
  templateUrl: './auction.component.html',
  styleUrls: ['./auction.component.scss']
})
export class AuctionComponent implements OnInit {
  @Input() auctions: AuctionRecord[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
