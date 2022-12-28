import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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

    constructor(private loginService: LoginService) { }

    ngOnInit(): void {
    }

    performLogin() {
        this.loginService.loginUser({
            email: this.loginForm.get('email').value,
            password: this.loginForm.get('password').value
        }).subscribe(
            () => console.log('success'),
            e => console.error('error', e)
        );
    }

    hasError() {
        return false
    }

}
