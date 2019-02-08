import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Meal } from "./meal";
import { FormControl, FormGroup } from "@angular/forms";
import { CookbookService } from "../../cookbook-page/cookbook.service";
import { Cookbook } from "../../cookbook-page/cookbook";
import { Subscription } from "rxjs/index";
import { HouseholdService } from "../../household.service";
import { Household } from "../../household";
import {Recipe} from "../../cookbook-page/recipe/recipe";

@Component({
    selector: 'app-meal',
    templateUrl: './meal.component.html',
    styleUrls: ['./meal.component.css']
})
export class MealComponent implements OnInit, OnDestroy {

    @Input()
    public meal: Meal;
    @Input()
    public day: string;
    @Input()
    public controlName: string
    @Input()
    public parentForm: FormGroup;

    public recipeNames: Array<string> = [];

    private subscriptions: Array<Subscription> = [];
    private cookbook: Cookbook;

    constructor(private householdService: HouseholdService,
                private cookbookService: CookbookService) { }

    public ngOnInit(): void {
        this.createForm();
        this.observeHousehold();
        this.observeCookbook();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private createForm(): void {
        const formControl = new FormControl(this.meal.name);
        formControl.valueChanges.subscribe(() => this.searchForRecipes());
        this.parentForm.addControl(this.controlName, formControl);
    }

    private observeHousehold(): void {
        this.subscriptions.push(this.householdService.observeHousehold()
            .subscribe((household: Household) => {
                this.cookbookService.determineCookbook(household)
                    .subscribe((cookbook: Cookbook) => this.cookbook = cookbook);
            }));
    }

    private observeCookbook(): void {
        this.subscriptions.push(this.cookbookService.observeCookbook()
            .subscribe((cookbook: Cookbook) => this.cookbook = cookbook));
    }

    private searchForRecipes(): void {
        let fieldValue: string = '';
        if (this.parentForm.controls[this.controlName].value !== null) {
            fieldValue = this.parentForm.controls[this.controlName].value.toLowerCase();
        }
        if (fieldValue.length > 0) {
            this.recipeNames = this.cookbook.recipes
                .map((recipe: Recipe) => recipe.name)
                .filter((name: string) => name !== null)
                .filter((name: string) => {
                    return name.toLowerCase().startsWith(fieldValue)
                            && name.toLowerCase() !== fieldValue;
                });
        }
    }

    public selectRecipe(name: string): void {
        this.parentForm.controls[this.controlName].setValue(name);
        this.recipeNames = [];
    }

    public unfocus(): void {
        setTimeout(() => this.recipeNames = [], 50);
    }

}
