import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { RegistrationService } from "./registration.service";
import { User } from "../login-page/user";
import { Router } from "@angular/router";
import { PasswordValidation } from "./password-validation";
import { UserService } from "../../user.service";
import { Page } from "../../page.enum";

@Component({
    selector: 'app-registration-page',
    templateUrl: './registration-page.component.html',
    styleUrls: ['./registration-page.component.sass']
})
export class RegistrationPageComponent implements OnInit {

    public form: FormGroup;

    private errorVisible: boolean = false;

    constructor(private registrationService: RegistrationService,
                private userService: UserService,
                private formBuilder: FormBuilder,
                private router: Router) { }

    public ngOnInit(): void {
        this.form = this.formBuilder.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', Validators.required],
            passwordRepeat: ['', Validators.required],
            termsAndConditions: [false, Validators.requiredTrue]
        }, {
            validator: PasswordValidation.matchPassword
        });
    }

    public register(): void {
        const email: string = this.form.get('email').value;
        const password: string = this.form.get('password').value;

        this.registrationService.register(email, password).subscribe(
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
