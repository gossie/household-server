import { Component, OnDestroy, OnInit } from '@angular/core';
import { FoodPlan } from "./food-plan";
import { FormBuilder, FormGroup } from "@angular/forms";
import { FoodPlanService } from "./food-plan.service";
import { Subscription } from "rxjs/index";
import { HouseholdService } from "../household.service";
import { Household } from "../household";

@Component({
    selector: 'app-food-plan-page',
    templateUrl: './food-plan-page.component.html',
    styleUrls: ['./food-plan-page.component.css']
})
export class FoodPlanPageComponent implements OnInit, OnDestroy {

    public foodPlan: FoodPlan;
    public foodPlanForm: FormGroup;

    private subscriptions: Array<Subscription> = [];
    private loading: boolean = false;

    constructor(private householdService: HouseholdService,
                private foodPlanService: FoodPlanService,
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
                this.foodPlanService.determineFoodPlan(household)
                    .subscribe((foodPlan: FoodPlan) => this.foodPlan = foodPlan);
            }));
    }

    private createForm(): void {
        this.foodPlanForm = this.formBuilder.group({});
    }

    public saveFoodPlan(): void {
        this.loading = true;

        this.copyDataFromForm();

        this.foodPlanService.saveFoodPlan(this.foodPlan)
            .subscribe((foodPlan: FoodPlan) => {
                this.foodPlan = foodPlan;
                this.copyDataToForm();
                this.loading = false;
            })
    }

    public clearFoodPlan(): void {
        this.loading = true;

        this.foodPlanService.clearFoodPlan(this.foodPlan)
            .subscribe((foodPlan: FoodPlan) => {
                this.foodPlan = foodPlan;
                this.copyDataToForm();
                this.loading = false;
            })
    }

    public isLoading(): boolean {
        return this.loading;
    }

    private copyDataFromForm(): void {
        this.foodPlan.meals.monday.name = this.foodPlanForm.controls.monday.value;
        this.foodPlan.meals.tuesday.name = this.foodPlanForm.controls.tuesday.value;
        this.foodPlan.meals.wednesday.name = this.foodPlanForm.controls.wednesday.value;
        this.foodPlan.meals.thursday.name = this.foodPlanForm.controls.thursday.value;
        this.foodPlan.meals.friday.name = this.foodPlanForm.controls.friday.value;
        this.foodPlan.meals.saturday.name = this.foodPlanForm.controls.saturday.value;
        this.foodPlan.meals.sunday.name = this.foodPlanForm.controls.sunday.value;
    }

    private copyDataToForm(): void {
        this.foodPlanForm.controls.monday.setValue(this.foodPlan.meals.monday.name);
        this.foodPlanForm.controls.tuesday.setValue(this.foodPlan.meals.tuesday.name);
        this.foodPlanForm.controls.wednesday.setValue(this.foodPlan.meals.wednesday.name);
        this.foodPlanForm.controls.thursday.setValue(this.foodPlan.meals.thursday.name);
        this.foodPlanForm.controls.friday.setValue(this.foodPlan.meals.friday.name);
        this.foodPlanForm.controls.saturday.setValue(this.foodPlan.meals.saturday.name);
        this.foodPlanForm.controls.sunday.setValue(this.foodPlan.meals.sunday.name);
    }

}
