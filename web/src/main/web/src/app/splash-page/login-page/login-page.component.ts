import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from './user';
import { Router } from '@angular/router';
import { UserService } from "../../user.service";
import { Page } from "../../page.enum";

@Component({
    selector: 'app-login-page',
    templateUrl: './login-page.component.html',
    styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

    public form: FormGroup;

    private errorVisible: boolean = false;

    constructor(private loginService: LoginService,
                private userService: UserService,
                private formBuilder: FormBuilder,
                private router: Router) { }

    public ngOnInit() {
        this.form = this.formBuilder.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', Validators.required]
        });
    }

    public login(): void {
        const email: string = this.form.get('email').value;
        const password: string = this.form.get('password').value;

        this.loginService.login(email, password).subscribe(
            (user: User) => this.handleSuccessfulRegistration(user, email, password),
            () => this.handleUnsuccessfulRegistration()
        );
    }

    private handleSuccessfulRegistration(user: User, email: string, password: string): void {
        this.userService.setUserData({
            user: user,
            authData: `Basic ${btoa(email + ':' + password)}`
        });
        this.router.navigate([Page.Household]);
    }

    private handleUnsuccessfulRegistration(): void {
        this.errorVisible = true;
    }

    public isErrorVisible(): boolean {
        return this.errorVisible;
    }

    public hideError(): void {
        this.errorVisible = false;
    }

}
