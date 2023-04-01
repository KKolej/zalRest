import { Component, OnInit } from '@angular/core';
import {TokenService} from "../login/token.service";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

  constructor(private tokenService: TokenService) { }

  ngOnInit(): void {
  }

    logout() {
      this.tokenService.removeToken()
    }
}
