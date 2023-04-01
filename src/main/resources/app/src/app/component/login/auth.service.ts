import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {TokenService} from './token.service';
import {catchError, tap} from 'rxjs/operators';

const HTTP_OPTIONS = {
  // headers: new HttpHeaders({
  //   'Content-Type': 'application/x-www-form-urlencoded',
  //   // Authorization: 'Basic ' + btoa(AppSettings.OAUTH_CLIENT + ':' + AppSettings.OAUTH_SECRET)
  // }),
  observe: "response" as 'body', // to display the full response & as 'body' for type cast
  // responseType: "text"
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  redirectUrl = '';
  API_URL = 'http://localhost:8080';

  constructor(private http: HttpClient, private tokenService: TokenService) {
  }

  private static handleError(error: HttpErrorResponse): any {
    console.log(error)
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError(
      'Something bad happened; please try again later.');
  }

  login(loginData: any): Observable<any> {
    this.tokenService.removeToken();
    const body = {
      "username": loginData.username,
      "password": loginData.password
    }

    return this.http.post<any>(this.API_URL + '/login', body, HTTP_OPTIONS)
      .pipe(
        tap(res => {
          this.tokenService.saveToken(res.headers.get("Authorization"));
        }),
        catchError(AuthService.handleError)
      );
  }

  logout(): void {
    this.tokenService.removeToken();
  }

    singUp(value: any) {
      const body = {
        "username": value.usernameS,
        "email": value.emailS,
        "phoneNumber": value.phoneNumberS,
        "password": value.passwordS
      }

      return this.http.post(this.API_URL + '/user/singUp', body, {responseType: 'text'})
          .pipe(
              tap(res => {
                window.alert("user created: " + res);
              }),
              catchError(AuthService.handleError)
          );
    }
}
