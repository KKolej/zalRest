import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})



export class TokenService {
  ACCESS_TOKEN = 'access_token';

  constructor() {
  }

  getToken() {
    return localStorage.getItem(this.ACCESS_TOKEN);
  }

  saveToken(token: string) {
    localStorage.setItem(this.ACCESS_TOKEN, token);
  }

  removeToken(): void {
    localStorage.removeItem(this.ACCESS_TOKEN);
  }
}
