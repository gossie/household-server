import { Component, OnDestroy, OnInit } from '@angular/core';
import { Cookbook } from "./cookbook";
import { HouseholdService } from "../household.service";
import { Subscription } from "rxjs/index";
import { Household } from "../household";
import { CookbookService } from "./cookbook.service";
import { Recipe } from "./recipe/recipe";
import { ShoppingListService } from "../shopping-list-page/shopping-list.service";
import { ShoppingList } from "../shopping-list-page/shopping-list";
import {CookbookEvent} from "./cookbook-event";
import {CookbookAction} from "./cookbook-action.enum";

@Component({
    selector: 'app-cookbook-page',
    templateUrl: './cookbook-page.component.html',
    styleUrls: ['./cookbook-page.component.sass']
})
export class CookbookPageComponent implements OnInit, OnDestroy {

    public cookbook: Cookbook;
    public recipeToEdit: Recipe;
    public recipeToBuy: Recipe;

    private shoppingList: ShoppingList;
    private subscriptions: Array<Subscription> = [];

    constructor(private householdService: HouseholdService,
                private cookbookService: CookbookService,
                private shoppingListService: ShoppingListService) { }

    public ngOnInit() {
        this.observeHousehold();
        this.observeShoppingList();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private observeHousehold(): void {
        this.subscriptions.push(this.householdService.observeHousehold()
            .subscribe((household: Household) => {
                this.cookbookService.determineCookbook(household)
                    .subscribe(this.handleCookbook.bind(this));
                this.shoppingListService.determineShoppingList(household)
                    .subscribe((shoppingList: ShoppingList) => this.shoppingList = shoppingList);
            }));
    }

    private observeShoppingList(): void {
        this.subscriptions.push(this.shoppingListService.observeShoppingList()
            .subscribe((shoppingList: ShoppingList) => this.shoppingList = shoppingList));
    }

    public onIngredientSelection(ingredients: Set<string>): void {
        const ingredientNames: Array<string> = [];
        ingredients.forEach((name: string) => ingredientNames.push(name));
        this.shoppingListService.addShoppingListItems(this.shoppingList.shoppingListGroups[0], ingredientNames)
            .subscribe(() => this.recipeToBuy = null);
    }

    public handleCookbook(cookbook: Cookbook): void {
        cookbook.recipes.sort((recipe1: Recipe, recipe2: Recipe) => recipe1.name.toLowerCase().localeCompare(recipe2.name.toLowerCase()));
        this.cookbook = cookbook;
        this.recipeToEdit = undefined;
        this.recipeToBuy = undefined;
    }

    public handleRecipe(event: CookbookEvent): void {
        if (event.action === CookbookAction.Edit) {
            this.recipeToEdit = event.recipe;
        } else {
            this.recipeToBuy = event.recipe
        }
    }
}
