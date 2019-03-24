import { Component, OnDestroy, OnInit } from '@angular/core';
import { FoodPlan } from "./food-plan";
import { FormBuilder, FormGroup } from "@angular/forms";
import { FoodPlanService } from "./food-plan.service";
import { Subscription } from "rxjs/index";
import { HouseholdService } from "../household.service";
import { Household } from "../household";
import { Recipe } from "../cookbook-page/recipe/recipe";
import { ShoppingListService } from "../shopping-list-page/shopping-list.service";
import { ShoppingList } from "../shopping-list-page/shopping-list";
import { CookbookService } from "../cookbook-page/cookbook.service";
import { Cookbook } from "../cookbook-page/cookbook";
import {DeleteHintService} from "../delete-hint.service";

@Component({
    selector: 'app-food-plan-page',
    templateUrl: './food-plan-page.component.html',
    styleUrls: ['./food-plan-page.component.sass']
})
export class FoodPlanPageComponent implements OnInit, OnDestroy {

    public cookbook: Cookbook;
    public foodPlan: FoodPlan;
    public foodPlanForm: FormGroup;
    public selectedRecipe: Recipe;

    private shoppingList: ShoppingList;
    private subscriptions: Array<Subscription> = [];
    private loading: boolean = false;

    constructor(private householdService: HouseholdService,
                private cookbookService: CookbookService,
                private shoppingListService: ShoppingListService,
                private foodPlanService: FoodPlanService,
                private deleteHintService: DeleteHintService,
                private formBuilder: FormBuilder) { }

    public ngOnInit(): void {
        this.observeUndo();
        this.observeHousehold();
        this.observeCookbook();
        this.observeShoppingList();
        this.createForm();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private observeUndo(): void {
        this.subscriptions.push(this.deleteHintService.onUndo()
            .subscribe(() => {
                this.loading = false;
            }));
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

    public onRecipeSelection(recipe: Recipe): void {
        this.selectedRecipe = recipe;
    }

    public onIngredientSelection(ingredients: Set<string>): void {
        const ingredientNames: Array<string> = [];
        ingredients.forEach((name: string) => ingredientNames.push(name));
        this.shoppingListService.addShoppingListItems(this.shoppingList.shoppingListGroups[0], ingredientNames)
            .subscribe(() => this.selectedRecipe = null);

        this.saveFoodPlan();
    }
}
