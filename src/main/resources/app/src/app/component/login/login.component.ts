import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ConnectionService } from 'src/app/services/connection.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup
  username: FormControl
  password: FormControl

  constructor(private connectionService: ConnectionService,
    private router: Router) { }

  ngOnInit(): void {
    this.username = new FormControl('', Validators.required);
    this.password = new FormControl('', Validators.required);
    
    this.loginForm = new FormGroup({
      username: this.username,
      password: this.password
    })
  }

  login(value: any) {
    if (this.loginForm.valid) {
      this.connectionService.login(value).subscribe(res => {
          console.log(res) 
          console.log("elo")
      })
    }
  }

}
