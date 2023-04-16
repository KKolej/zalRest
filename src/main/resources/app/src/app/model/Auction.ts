class SellerData {
    username: String
}

export class AuctionRecord {
    id: string
    name: string
    pages: number
    amount: number
    price: number
    minPrice: number
    buyNowPrice: number
    sellerData: SellerData
    buyerData: SellerData
    endDate: Date
}

export class BasicActionData {
    name: String
    amount: Number
    price: Number
    minPrice: Number
    buyNowPrice: Number
}

export class AuctionAction {
    constructor(id: string, buyNow: boolean, price: number) {
        this.id = id
        this.buyNow = buyNow
        this.price = price
    }
    id: String
    buyNow: boolean
    price: number
}
