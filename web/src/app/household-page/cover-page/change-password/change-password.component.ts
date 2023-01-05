import { Component, Input, OnInit } from '@angular/core';
import { User } from "../../../user";
import { UntypedFormBuilder, UntypedFormGroup, Validators } from "@angular/forms";
import { UserService } from "../../../user.service";
import { PasswordValidation } from "./password-validation";

@Component({
    selector: 'app-change-password',
    templateUrl: './change-password.component.html',
    styleUrls: ['./change-password.component.sass']
})
export class ChangePasswordComponent implements OnInit {

    @Input()
    public user: User;

    public form: UntypedFormGroup;
    public loading: boolean = false;
    public successVisible = false;
    public errorVisible: boolean = false;

    constructor(private formBuilder: UntypedFormBuilder,
                private userService: UserService) { }

    public ngOnInit() {
        this.errorVisible = false;
        this.successVisible = false;

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
        this.successVisible = false;
        this.userService.changePassword(this.user, this.form.controls['currentPassword'].value, this.form.controls['password'].value)
            .subscribe(() => this.handlePasswordChange(), () => this.handleError());
    }

    private handlePasswordChange(): void {
        this.errorVisible = false;
        this.successVisible = true;
        this.form.reset();
        this.loading = false;
    }

    private handleError(): void {
        this.loading = false;
        this.form.reset();
        this.errorVisible = true;
        this.successVisible = false;
    }

    public hideNotification(): void {
        this.errorVisible = false;
        this.successVisible = false;
    }
}
