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

    constructor(private formBuilder: FormBuilder,
                private userService: UserService) { }

    public ngOnInit() {
        this.form = this.formBuilder.group({
            currentPassword: ['', Validators.required],
            newPassword: ['', Validators.required],
            newPasswordRepeat: ['', Validators.required],
        }, {
            validator: PasswordValidation.matchPassword
        });
    }
    public save(): void {
        this.loading = true;
        this.userService.changePassword(this.user, this.form.controls.currentPassword.value, this.form.controls.newPassword.value)
            .subscribe(() => {
                this.loading = false;
                this.form.reset();
            });
    }

}
