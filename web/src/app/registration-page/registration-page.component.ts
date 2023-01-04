import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationService } from './registration.service';

@Component({
    selector: 'app-registration-page',
    templateUrl: './registration-page.component.html',
    styleUrls: ['./registration-page.component.sass']
})
export class RegistrationPageComponent implements OnInit {

    registerForm = new FormGroup({
        email: new FormControl(''),
        password: new FormControl(''),
        passwordAgain: new FormControl('')
    });

    errorMessage = ''

    constructor(private registrationService: RegistrationService, private router: Router) { }

    ngOnInit(): void {
    }

    resetErrorState() {
        this.errorMessage = ''
    }

    performRegistration() {
        this.registrationService.registerUser({
            email: this.registerForm.get('email').value,
            password: this.registerForm.get('password').value,
            passwordAgain: this.registerForm.get('passwordAgain').value,
        }).subscribe(
            () => this.router.navigateByUrl('login'),
            e => {
                this.errorMessage = 'Bei der Registrierung ist ein Fehler aufgetreten.'
                console.error('error', e);
            }
        );
    }

    hasError() {
        return this.errorMessage;
    }

}
