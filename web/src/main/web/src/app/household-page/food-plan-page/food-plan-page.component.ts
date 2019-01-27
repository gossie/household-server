import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { FoodPlan } from "./food-plan";
import { FormBuilder, FormGroup } from "@angular/forms";
import { FoodPlanService } from "./food-plan.service";

@Component({
    selector: 'app-food-plan-page',
    templateUrl: './food-plan-page.component.html',
    styleUrls: ['./food-plan-page.component.css']
})
export class FoodPlanPageComponent implements OnInit {

    public foodPlan: FoodPlan;
    public foodPlanForm: FormGroup;

    private loading: boolean = false;

    constructor(private foodPlanService: FoodPlanService,
                private route: ActivatedRoute,
                private formBuilder: FormBuilder) { }

    public ngOnInit(): void {
        this.foodPlan = this.route.snapshot.data.foodPlan;
        this.foodPlanForm = this.formBuilder.group({});
    }

    public saveFoodPlan(): void {
        this.loading = true;

        this.foodPlan.meals.monday.name = this.foodPlanForm.controls.monday.value;
        this.foodPlan.meals.tuesday.name = this.foodPlanForm.controls.tuesday.value;
        this.foodPlan.meals.wednesday.name = this.foodPlanForm.controls.wednesday.value;
        this.foodPlan.meals.thursday.name = this.foodPlanForm.controls.thursday.value;
        this.foodPlan.meals.friday.name = this.foodPlanForm.controls.friday.value;
        this.foodPlan.meals.saturday.name = this.foodPlanForm.controls.saturday.value;
        this.foodPlan.meals.sunday.name = this.foodPlanForm.controls.sunday.value;

        this.foodPlanService.saveFoodPlan(this.foodPlan)
            .subscribe((foodPlan: FoodPlan) => {
                this.foodPlan = foodPlan;
                this.loading = false;
            })
    }

    public isLoading(): boolean {
        return this.loading;
    }

}
