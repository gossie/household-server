import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Page } from '../page.enum';
import { TokenService } from '../token.service';
import { LoginResponse } from './login-data';
import { LoginService } from './login.service';

@Component({
    selector: 'app-login-page',
    templateUrl: './login-page.component.html',
    styleUrls: ['./login-page.component.sass']
})
export class LoginPageComponent implements OnInit {

    errorMessage = '';
    loginForm = new FormGroup({
        email: new FormControl(''),
        password: new FormControl('')
    });

    constructor(private loginService: LoginService,
                private tokenService: TokenService,
                private router: Router) { }

    ngOnInit(): void {
    }

    performLogin() {
        this.loginService.loginUser({
            email: this.loginForm.get('email').value,
            password: this.loginForm.get('password').value
        }).subscribe(
            (loginResponse: LoginResponse) => {
                this.tokenService.publishToken(loginResponse.token);
                this.router.navigateByUrl(`/${Page.Household}`);
            },
            () => this.errorMessage = 'Der Login ist fehlgeschlagen.'
        );
    }

    hasError() {
        return this.errorMessage
    }

    resetErrorState() {
        this.errorMessage = ''
    }

}
