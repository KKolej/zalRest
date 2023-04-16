import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import {AuctionAction, AuctionRecord, BasicActionData} from '../model/Auction';

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

  login(value: any): Observable<any> {
    return this.http.post(BASE_URL + 'login', value, this.option)
      .pipe(catchError(this.handleError<[]>('login')))
  }

  createNewAuction(doc: BasicActionData): Observable<any> {
    return this.http.post(BASE_URL + 'auction/new', doc, this.option)
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