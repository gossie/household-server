import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Meal } from './meal';
import { FormControl, FormGroup } from '@angular/forms';
import { CookbookService } from '../../cookbook-page/cookbook.service';
import { Cookbook } from '../../cookbook-page/cookbook';
import { Subscription } from 'rxjs/index';
import { Recipe } from '../../cookbook-page/recipe/recipe';
import { RecipeSelectionEvent } from './recipe-selection.event';
import { ObjectUtils } from 'src/app/object.utils';
import { FoodPlanService } from '../food-plan.service';

@Component({
    selector: 'app-meal',
    templateUrl: './meal.component.html',
    styleUrls: ['./meal.component.sass']
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
    public recipeEmitter: EventEmitter<RecipeSelectionEvent> = new EventEmitter();

    public recipes: Array<Recipe> = [];

    private subscriptions: Array<Subscription> = [];

    constructor(private foodPlanService: FoodPlanService,
                private cookbookService: CookbookService) { }

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
        let fieldValue = '';
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
                this.meal.name = completeRecipe.name;
                this.recipes = [];
                this.recipeEmitter.emit({
                    recipe: completeRecipe,
                    meal: this.meal
                });
            });
    }

    public changeMealName(): void {
        this.meal.name = this.parentForm.controls[this.controlName].value;
        this.foodPlanService.saveMeal(this.meal, null)
                .toPromise();
    }

    public unfocus(): void {
        setTimeout(() => this.recipes = [], 250);
    }

    public hasConnectedRecipe(): boolean {
        return this.meal && ObjectUtils.isObject(this.meal.links.find(link => link.rel === 'recipe'));
    }
}
