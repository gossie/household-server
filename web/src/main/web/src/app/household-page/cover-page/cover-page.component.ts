import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { InvitationService } from "./invitation.service";
import { Household } from "../household";
import { Subscription } from "rxjs/index";
import { HouseholdService } from "../household.service";
import {UserService} from "../../user.service";
import {User} from "../../splash-page/login-page/user";
import {UserData} from "../../user-data";
import {ObjectUtils} from "../../object.utils";

@Component({
    selector: 'app-cover-page',
    templateUrl: './cover-page.component.html',
    styleUrls: ['./cover-page.component.sass']
})
export class CoverPageComponent implements OnInit, OnDestroy {

    public user: User;
    public household: Household;
    public invitationForm: FormGroup;

    private subscriptions: Array<Subscription> = [];
    private loading: boolean = false;
    private errorVisible: boolean = false;

    constructor(private userService: UserService,
                private householdService: HouseholdService,
                private invitationService: InvitationService,
                private formBuilder: FormBuilder) { }

    public ngOnInit(): void {
        this.observeUser();
        this.observeHousehold();
        this.createForm();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private observeUser(): void {
        this.subscriptions.push(this.userService.observeUserData()
            .subscribe((userData: UserData) => {
                this.user = userData.user;
            })
        );
    }

    private observeHousehold(): void {
        this.subscriptions.push(this.householdService.observeHousehold()
            .subscribe((household: Household) => {
                this.household = household;
            }));
    }

    private createForm(): void {
        this.invitationForm = this.formBuilder.group({
            email: ['', [Validators.required, Validators.email]]
        })
    }

    public trimEmail(): void {
        const email: string = this.invitationForm.controls.email.value;
        if (email) {
            this.invitationForm.controls.email.setValue(email.trim());
        }
    }

    public sendInvitation(): void {
        this.loading = true;
        this.invitationService.sendInvitation(this.invitationForm.controls.email.value)
            .subscribe(
                this.handleSuccessfulInvitation.bind(this),
                this.handleUnsuccessfulInvitation.bind(this)
            );
    }

    private handleSuccessfulInvitation() {
        this.loading = false
        this.invitationForm.controls.email.setValue('');
    }

    private handleUnsuccessfulInvitation(error: any) {
        if (error.status === 404) {
            this.errorVisible = true;
        }
        this.loading = false;
    }

    public isLoading(): boolean {
        return this.loading;
    }

    public isErrorVisible(): boolean {
        return this.errorVisible;
    }

    public hideError(): void {
        this.errorVisible = false;
    }

}
