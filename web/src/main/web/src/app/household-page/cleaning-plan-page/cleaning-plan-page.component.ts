import { Component, OnDestroy, OnInit } from '@angular/core';
import { CleaningPlan } from "./cleaning-plan";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CleaningPlanService } from "./cleaning-plan.service";
import { Chore } from "./chore/chore";
import { Subscription } from "rxjs/index";
import { Household } from "../household";
import { HouseholdService } from "../household.service";

@Component({
    selector: 'app-cleaning-plan-page',
    templateUrl: './cleaning-plan-page.component.html',
    styleUrls: ['./cleaning-plan-page.component.sass']
})
export class CleaningPlanPageComponent implements OnInit, OnDestroy {

    public cleaningPlan: CleaningPlan;
    public cleaningPlanForm: FormGroup;

    private subscriptions: Array<Subscription> = [];
    private loading: boolean = false;

    constructor(private householdService: HouseholdService,
                private cleaningPlanService: CleaningPlanService,
                private formBuilder: FormBuilder) { }

    public ngOnInit() {
        this.observeHousehold();
        this.createForm();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private observeHousehold(): void {
        this.subscriptions.push(this.householdService.observeHousehold()
            .subscribe((household: Household) => {
                this.cleaningPlanService.determineCleaningPlan(household)
                    .subscribe((this.handleCleaningPlan.bind(this)));
            }));
    }

    private createForm(): void {
        this.cleaningPlanForm = this.formBuilder.group({
            name: ['', Validators.required],
            repeat: ['', [Validators.required, Validators.min(1), Validators.max(365)]]
        });
    }

    public addChore(): void {
        this.loading = true;

        const chore: Chore = {
            name: this.cleaningPlanForm.controls.name.value,
            repeat: this.cleaningPlanForm.controls.repeat.value,
            lastPerformed: Date.now()
        };

        this.cleaningPlanService.addChore(this.cleaningPlan, chore)
            .subscribe((cleaningPlan: CleaningPlan) => {
                this.handleCleaningPlan(cleaningPlan);
                this.cleaningPlanForm.controls.name.setValue('');
                this.cleaningPlanForm.controls.repeat.setValue('');
            });
    }

    public handleCleaningPlan(cleaningPlan: CleaningPlan): void {
        cleaningPlan.chores.sort((chore1: Chore, chore2: Chore) => chore1.nextTime - chore2.nextTime);
        this.cleaningPlan = cleaningPlan;
        this.loading = false;
    }

    public isLoading(): boolean {
        return this.loading;
    }

}
