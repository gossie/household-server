import { Component, Input, OnInit } from '@angular/core';
import { User } from "../../../splash-page/login-page/user";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { PasswordValidation } from "../../../splash-page/registration-page/password-validation";
import { UserService } from "../../../user.service";

@Component({
    selector: 'app-change-password',
    templateUrl: './change-password.component.html',
    styleUrls: ['./change-password.component.sass']
})
export class ChangePasswordComponent implements OnInit {

    @Input()
    public user: User;

    public form: FormGroup;
    public loading: boolean = false;
    public errorVisible: boolean = false;

    constructor(private formBuilder: FormBuilder,
                private userService: UserService) { }

    public ngOnInit() {
        this.form = this.formBuilder.group({
            currentPassword: ['', Validators.required],
            password: ['', Validators.required],
            passwordRepeat: ['', Validators.required],
        }, {
            validator: PasswordValidation.matchPassword
        });
    }

    public save(): void {
        this.loading = true;
        this.errorVisible = false;
        this.userService.changePassword(this.user, this.form.controls.currentPassword.value, this.form.controls.password.value)
            .subscribe(() => this.handlePasswordChange(), () => this.handleError());
    }

    private handlePasswordChange(): void {
        this.errorVisible = false;
        this.form.reset();
        this.loading = false;
    }

    private handleError(): void {
        this.loading = false;
        this.form.reset();
        this.errorVisible = true;
    }

    public hideError(): void {
        this.errorVisible = false;
    }
}
