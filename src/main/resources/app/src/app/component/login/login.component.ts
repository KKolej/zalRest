import {FormGroup, Validators, FormControl} from '@angular/forms';
import {Component, OnInit} from '@angular/core';
import {ConnectionService} from 'src/app/services/connection.service';
import {Router} from '@angular/router';
import {AuthService} from "./auth.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    loginForm: FormGroup
    singUpForm: FormGroup
    username: FormControl
    password: FormControl
    phoneNumberS: FormControl;
    emailS: FormControl;
    passwordS: FormControl;
    usernameS: FormControl;

    constructor(private connectionService: ConnectionService,
                private authService: AuthService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.username = new FormControl('', Validators.required);
        this.password = new FormControl('', Validators.required);
        this.phoneNumberS = new FormControl('', Validators.required);
        this.emailS = new FormControl('', Validators.required);
        this.passwordS = new FormControl('', Validators.required);
        this.usernameS = new FormControl('', Validators.required);

        this.loginForm = new FormGroup({
            username: this.username,
            password: this.password
        })

        this.singUpForm = new FormGroup({
            phoneNumberS: this.phoneNumberS,
            emailS: this.emailS,
            passwordS: this.passwordS,
            usernameS: this.usernameS
        })
    }

    login(value: any) {
        if (this.loginForm.valid) {
            this.authService.login(this.loginForm.value)
                .subscribe(() => {
                    this.router.navigate(['/home']).then(_ => console.log('logged'));
                }, (err: any) => {
                    console.log(err);
                });
        }
    }

    singUp(value: any) {
        this.authService.singUp(value)
            .subscribe(() => {
                this.singUpForm.reset()
                }
            )
    }
}
