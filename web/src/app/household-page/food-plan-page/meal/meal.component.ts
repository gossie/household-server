import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Meal } from './meal';
import { UntypedFormControl, UntypedFormGroup } from '@angular/forms';
import { CookbookService } from '../../cookbook-page/cookbook.service';
import { Cookbook } from '../../cookbook-page/cookbook';
import { Subscription } from 'rxjs/index';
import { Recipe } from '../../cookbook-page/recipe/recipe';
import { RecipeSelectionEvent } from './recipe-selection.event';
import { ObjectUtils } from 'src/app/object.utils';
import { FoodPlanService } from '../food-plan.service';
import { Router } from '@angular/router';
import { FoodPlan } from '../food-plan';

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
    public date: Date;
    @Input()
    public controlName: string;
    @Input()
    public parentForm: UntypedFormGroup;
    @Output()
    public foodPlanEmitter: EventEmitter<FoodPlan> = new EventEmitter();
    @Output()
    public recipeEmitter: EventEmitter<RecipeSelectionEvent> = new EventEmitter();

    public recipes: Array<Recipe> = [];
    public label: string;

    private subscriptions: Array<Subscription> = [];

    constructor(private foodPlanService: FoodPlanService,
                private cookbookService: CookbookService,
                private router: Router) { }

    public ngOnInit(): void {
        this.createForm();
        this.createLabel();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private createForm(): void {
        const formControl = new UntypedFormControl(this.meal.name);
        formControl.valueChanges.subscribe(() => this.searchForRecipes());
        this.parentForm.addControl(this.controlName, formControl);
    }

    private createLabel(): void {
        if (ObjectUtils.isObject(this.date)) {
            const year = this.date.getFullYear();
            const month = this.date.getMonth() < 9 ? `0${this.date.getMonth() + 1}` : this.date.getMonth() + 1;
            const day = this.date.getDate() < 10 ? `0${this.date.getDate()}` : this.date.getDate();
            this.label = `${this.day} (${day}.${month}.${year})`;
        }
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
        window.setTimeout(() => {
            this.meal.name = this.parentForm.controls[this.controlName].value;
            this.foodPlanService.saveMeal(this.meal, null)
                .subscribe((foodPlan: FoodPlan) => this.foodPlanEmitter.emit(foodPlan));
        }, 50);
    }

    public unfocus(): void {
        setTimeout(() => this.recipes = [], 250);
    }

    public hasConnectedRecipe(): boolean {
        return this.meal && ObjectUtils.isObject(this.meal.links.find(link => link.rel === 'recipe'));
    }

    public jumpToRecipe(): void {
        this.router.navigate(['/cookbook'])
            .then(() => {
                const url: string = this.meal.links.find(link => link.rel === 'recipe').href;
                this.cookbookService.determineRecipeByUrl(url);
            });
    }
}
