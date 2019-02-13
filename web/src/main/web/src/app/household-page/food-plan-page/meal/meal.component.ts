import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Meal } from "./meal";
import { FormControl, FormGroup } from "@angular/forms";
import { CookbookService } from "../../cookbook-page/cookbook.service";
import { Cookbook } from "../../cookbook-page/cookbook";
import { Subscription } from "rxjs/index";
import { HouseholdService } from "../../household.service";
import { Household } from "../../household";
import { Recipe } from "../../cookbook-page/recipe/recipe";

@Component({
    selector: 'app-meal',
    templateUrl: './meal.component.html',
    styleUrls: ['./meal.component.css']
})
export class MealComponent implements OnInit, OnDestroy {

    @Input()
    public cookbook: Cookbook;
    @Input()
    public meal: Meal;
    @Input()
    public day: string;
    @Input()
    public controlName: string;
    @Input()
    public parentForm: FormGroup;
    @Output()
    public recipeEmitter: EventEmitter<Recipe> = new EventEmitter();

    public recipes: Array<Recipe> = [];

    private subscriptions: Array<Subscription> = [];

    constructor(private cookbookService: CookbookService) { }

    public ngOnInit(): void {
        this.createForm();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private createForm(): void {
        const formControl = new FormControl(this.meal.name);
        formControl.valueChanges.subscribe(() => this.searchForRecipes());
        this.parentForm.addControl(this.controlName, formControl);
    }

    private searchForRecipes(): void {
        let fieldValue: string = '';
        if (this.parentForm.controls[this.controlName].value !== null) {
            fieldValue = this.parentForm.controls[this.controlName].value.toLowerCase();
        }

        if (fieldValue.length > 0) {
            this.recipes = this.cookbook.recipes
                .filter((recipe: Recipe) => this.recipeMatchesSearchString(recipe, fieldValue));
        } else {
            this.recipes = [];
        }
    }

    private recipeMatchesSearchString(recipe: Recipe, searchString: string): boolean {
        return recipe.name.toLowerCase().indexOf(searchString.toLowerCase()) >= 0;
    }

    public selectRecipe(recipe: Recipe): void {
        this.cookbookService.determineRecipe(recipe)
            .subscribe((completeRecipe: Recipe) => {
                this.parentForm.controls[this.controlName].setValue(completeRecipe.name);
                this.recipes = [];
                this.recipeEmitter.emit(completeRecipe);
            });
    }

    public unfocus(): void {
        setTimeout(() => this.recipes = [], 250);
    }

}
