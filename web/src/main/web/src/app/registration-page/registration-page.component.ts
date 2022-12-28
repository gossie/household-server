import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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

    constructor(private registrationService: RegistrationService) { }

    ngOnInit(): void {
    }

    performRegistration() {
        this.registrationService.registerUser({
            email: this.registerForm.get('email').value,
            password: this.registerForm.get('password').value,
            passwordAgain: this.registerForm.get('passwordAgain').value,
        }).subscribe(
            () => console.log('success'),
            e => console.error('error', e)
        );
    }

    hasError() {
        return false;
    }

}
