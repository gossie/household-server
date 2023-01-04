import { Component, OnDestroy, OnInit } from '@angular/core';
import { FoodPlan } from './food-plan';
import { UntypedFormBuilder, UntypedFormGroup } from '@angular/forms';
import { FoodPlanService } from './food-plan.service';
import { Subscription } from 'rxjs/index';
import { HouseholdService } from '../household.service';
import { Household } from '../household';
import { Recipe } from '../cookbook-page/recipe/recipe';
import { ShoppingListService } from '../shopping-list-page/shopping-list.service';
import { ShoppingList } from '../shopping-list-page/shopping-list';
import { CookbookService } from '../cookbook-page/cookbook.service';
import { Cookbook } from '../cookbook-page/cookbook';
import { DeleteHintService } from '../delete-hint.service';
import { Meal } from './meal/meal';
import { RecipeSelectionEvent } from './meal/recipe-selection.event';

@Component({
    selector: 'app-food-plan-page',
    templateUrl: './food-plan-page.component.html',
    styleUrls: ['./food-plan-page.component.sass']
})
export class FoodPlanPageComponent implements OnInit, OnDestroy {

    public cookbook: Cookbook;
    public foodPlan: FoodPlan;
    public foodPlanForm: UntypedFormGroup;
    public selectedRecipe: Recipe;
    public dates: Array<Date> = new Array(7);

    private currentMeal: Meal;
    private shoppingList: ShoppingList;
    private subscriptions: Array<Subscription> = [];
    private loading = false;

    constructor(private householdService: HouseholdService,
                private cookbookService: CookbookService,
                private shoppingListService: ShoppingListService,
                private foodPlanService: FoodPlanService,
                private deleteHintService: DeleteHintService,
                private formBuilder: UntypedFormBuilder) { }

    public ngOnInit(): void {
        this.observeUndo();
        this.observeHousehold();
        this.observeCookbook();
        this.observeShoppingList();
        this.createForm();
        this.createDates();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private observeUndo(): void {
        this.subscriptions.push(this.deleteHintService.onUndo()
            .subscribe(() => this.loading = false));
    }

    private observeHousehold(): void {
        this.subscriptions.push(this.householdService.observeHousehold()
            .subscribe((household: Household) => {
                this.foodPlanService.determineFoodPlan(household)
                    .subscribe((foodPlan: FoodPlan) => this.foodPlan = foodPlan);

                this.shoppingListService.determineShoppingList(household)
                    .subscribe((shoppingList: ShoppingList) => this.shoppingList = shoppingList);

                this.cookbookService.determineCookbook(household)
                    .subscribe((cookbook: Cookbook) => this.cookbook = cookbook);
            }));
    }

    private observeShoppingList(): void {
        this.subscriptions.push(this.shoppingListService.observeShoppingList()
            .subscribe((shoppingList: ShoppingList) => this.shoppingList = shoppingList));
    }

    private observeCookbook(): void {
        this.subscriptions.push(this.cookbookService.observeCookbook()
            .subscribe((cookbook: Cookbook) => this.cookbook = cookbook));
    }

    private createForm(): void {
        this.foodPlanForm = this.formBuilder.group({});
    }

    private createDates(): void {
        const today = new Date();
        const monday = new Date(today.getTime() - ((today.getDay() + 6) % 7) * 86400000);
        this.dates[0] = monday;
        for (let i = 1; i < 7; i++) {
            this.dates[i] = new Date(monday.getTime() + i * 86400000);
        }
    }

    public clearFoodPlan(): void {
        this.loading = true;

        this.foodPlanService.clearFoodPlan(this.foodPlan)
            .subscribe((foodPlan: FoodPlan) => {
                this.foodPlan = foodPlan;
                this.copyDataToForm();
                this.loading = false;
            });
    }

    public isLoading(): boolean {
        return this.loading;
    }

    private copyDataToForm(): void {
        this.foodPlanForm.controls['monday'].setValue(this.foodPlan.meals.monday.name);
        this.foodPlanForm.controls['tuesday'].setValue(this.foodPlan.meals.tuesday.name);
        this.foodPlanForm.controls['wednesday'].setValue(this.foodPlan.meals.wednesday.name);
        this.foodPlanForm.controls['thursday'].setValue(this.foodPlan.meals.thursday.name);
        this.foodPlanForm.controls['friday'].setValue(this.foodPlan.meals.friday.name);
        this.foodPlanForm.controls['saturday'].setValue(this.foodPlan.meals.saturday.name);
        this.foodPlanForm.controls['sunday'].setValue(this.foodPlan.meals.sunday.name);
    }

    public onRecipeSelection(event: RecipeSelectionEvent): void {
        this.selectedRecipe = event.recipe;
        this.currentMeal = event.meal;
    }

    public onMealNameChange(foodPlan: FoodPlan): void {
        this.foodPlan = foodPlan;
    }

    public onIngredientSelection(ingredients: Set<string>): void {
        this.loading = true;

        const ingredientNames: Array<string> = [];
        ingredients.forEach((name: string) => ingredientNames.push(name));
        this.shoppingListService.addShoppingListItems(this.shoppingList.shoppingListGroups[0], ingredientNames)
            .subscribe(() => {
                this.foodPlanService.saveMeal(this.currentMeal, this.selectedRecipe)
                    .subscribe((foodPlan: FoodPlan) => {
                        this.foodPlan = foodPlan;
                        this.currentMeal = null;
                        this.selectedRecipe = null;
                        this.loading = false;
                    });
            });
    }
}
