import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { InvitationService } from "./invitation.service";
import { Household } from "../household";
import { Subscription } from "rxjs/index";
import { HouseholdService } from "../household.service";

@Component({
    selector: 'app-cover-page',
    templateUrl: './cover-page.component.html',
    styleUrls: ['./cover-page.component.css']
})
export class CoverPageComponent implements OnInit, OnDestroy {

    public household: Household;
    public invitationForm: FormGroup;

    private subscriptions: Array<Subscription> = [];
    private loading: boolean = false;

    constructor(private householdService: HouseholdService,
                private invitationService: InvitationService,
                private formBuilder: FormBuilder) { }

    public ngOnInit(): void {
        this.observeHousehold();
        this.createForm();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private observeHousehold(): void {
        this.subscriptions.push(this.householdService.observeHousehold()
            .subscribe((household: Household) => {
                this.household = household;
            }));
    }

    private createForm(): void {
        this.invitationForm = this.formBuilder.group({
            email: ['', Validators.required]
        })
    }

    public sendInvitation(): void {
        this.loading = true;
        this.invitationService.sendInvitation(this.invitationForm.controls.email.value)
            .subscribe(() => this.loading = false);
    }

    public isLoading(): boolean {
        return this.loading;
    }

}
