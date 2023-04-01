import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { myDocument } from '../model/myDocument';

const BASE_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {
  private option = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }

  constructor(private http: HttpClient) { }

  getString(): Observable<any> {
    return this.http.get<any>(BASE_URL + 'word')
      .pipe(catchError(this.handleError<string>('getString', "")))
  }

  getDocumentList(): Observable<myDocument[]> {
    return this.http.get<myDocument[]>(BASE_URL + 'documents')
      .pipe(catchError(this.handleError<[]>('getDocumentList', [])))
  }

  login(value: any): Observable<any> {
    return this.http.post(BASE_URL + 'login', value, this.option)
      .pipe(catchError(this.handleError<[]>('login')))
  }

  saveDocument(doc: myDocument): Observable<any> {
    return this.http.post(BASE_URL + 'document/save', doc, this.option)
      .pipe(catchError(this.handleError<[]>('saveDocument')))
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(error)
      return of(result as T)
    }
  }
}