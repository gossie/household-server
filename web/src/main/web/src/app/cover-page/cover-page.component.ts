import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Household} from "../household-page/household";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {InvitationService} from "./invitation.service";

@Component({
    selector: 'app-cover-page',
    templateUrl: './cover-page.component.html',
    styleUrls: ['./cover-page.component.css']
})
export class CoverPageComponent implements OnInit {

    public household: Household;
    public invitationForm: FormGroup;

    private loading: boolean = false;

    constructor(private invitationService: InvitationService,
                private route: ActivatedRoute,
                private formBuilder: FormBuilder) { }

    public ngOnInit(): void {
        this.household = this.route.snapshot.data.household;
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
