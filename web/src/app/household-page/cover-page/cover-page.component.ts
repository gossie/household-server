import { Component, OnDestroy, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from "@angular/forms";
import { Subscription } from "rxjs/index";
import { InvitationService } from "../../common-elements/invitation/invitation.service";
import { Household } from "../household";
import { HouseholdService } from "../household.service";
import { UserService } from "../../user.service";
import { User } from "../../user";
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-cover-page',
    templateUrl: './cover-page.component.html',
    styleUrls: ['./cover-page.component.sass']
})
export class CoverPageComponent implements OnInit, OnDestroy {

    public user: User;
    public household: Household;
    public invitationForm: UntypedFormGroup;

    private subscriptions: Array<Subscription> = [];
    private loading: boolean = false;
    private errorVisible: boolean = false;

    constructor(private invitationService: InvitationService,
                private activatedRoute: ActivatedRoute,
                private formBuilder: UntypedFormBuilder) { }

    public ngOnInit(): void {
        this.subscriptions.push(this.activatedRoute.data
                .subscribe(({user, household}) => {
                    this.user = user;
                    this.household = household;
                }));
        this.createForm();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private createForm(): void {
        this.invitationForm = this.formBuilder.group({
            email: ['', [Validators.required, Validators.email]]
        })
    }

    public trimEmail(): void {
        const email: string = this.invitationForm.controls['email'].value;
        if (email) {
            this.invitationForm.controls['email'].setValue(email.trim());
        }
    }

    public sendInvitation(): void {
        this.loading = true;
        this.invitationService.sendInvitation(this.invitationForm.controls['email'].value)
            .subscribe(
                this.handleSuccessfulInvitation.bind(this),
                this.handleUnsuccessfulInvitation.bind(this)
            );
    }

    private handleSuccessfulInvitation() {
        this.loading = false;
        this.invitationForm.controls['email'].setValue('');
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
