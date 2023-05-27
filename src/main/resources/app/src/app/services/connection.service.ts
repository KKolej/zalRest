import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import {AuctionAction, AuctionRecord, BasicActionData} from '../model/Auction';
import {CustomUser} from "../model/User";

const BASE_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {
  private option = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }

  constructor(private http: HttpClient) { }

  getAllAuctions(): Observable<AuctionRecord[]> {
    return this.http.get<AuctionRecord[]>(BASE_URL + 'auction/all')
      .pipe(catchError(this.handleError<[]>('getDocumentList', [])))
  }

  getUserInfo(): Observable<CustomUser> {
    return this.http.get<CustomUser>(BASE_URL + 'user/info')
      .pipe(catchError(this.handleError<CustomUser>('getDocumentList')))
  }

  deleteAuction(doc: AuctionAction): Observable<any> {
    return this.http.delete(BASE_URL + 'auction/delete/' + doc.id, this.option)
        .pipe(catchError(this.handleError<[]>('saveDocument')))
  }

  getAllEndedAuctions(seller: boolean): Observable<AuctionRecord[]> {
    return this.http.get<AuctionRecord[]>(BASE_URL + 'auction/ended', {
      params: {
        'seller': seller
      },
    })
      .pipe(catchError(this.handleError<[]>('getDocumentList', [])))
  }

  login(value: any): Observable<any> {
    return this.http.post(BASE_URL + 'login', value, this.option)
      .pipe(catchError(this.handleError<[]>('login')))
  }

  createNewAuction(doc: BasicActionData): Observable<any> {
    return this.http.post(BASE_URL + 'auction/new', doc, this.option)
      .pipe(catchError(this.handleError<[]>('saveDocument')))
  }

  editNewAuction(doc: BasicActionData, auctionAction: AuctionAction): Observable<any> {
    return this.http.patch(BASE_URL + 'auction/edit', {
      'auctionData': doc,
      'action': auctionAction
    }, this.option)
      .pipe(catchError(this.handleError<[]>('saveDocument')))
  }

  bidAuction(doc: AuctionAction): Observable<any> {
    return this.http.post(BASE_URL + 'auction/bidPrice', doc, this.option)
      .pipe(catchError(this.handleError<[]>('saveDocument')))
  }

  buyNowAuction(doc: AuctionAction): Observable<any> {
    return this.http.post(BASE_URL + 'auction/buyNow', doc, this.option)
      .pipe(catchError(this.handleError<[]>('saveDocument')))
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      alert(error.error)
      return of(result as T)
    }
  }
}