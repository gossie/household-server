import { Component, OnDestroy, OnInit } from '@angular/core';
import { Cookbook } from './cookbook';
import { Subscription } from 'rxjs/index';
import { Recipe } from './recipe/recipe';
import { ShoppingListService } from '../shopping-list-page/shopping-list.service';
import { ShoppingList } from '../shopping-list-page/shopping-list';
import { CookbookEvent } from './cookbook-event';
import { CookbookAction } from './cookbook-action.enum';
import { ActivatedRoute } from '@angular/router';

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

    constructor(private shoppingListService: ShoppingListService,
                private activatedRoute: ActivatedRoute) { }

    public ngOnInit() {
        this.activatedRoute.data.subscribe(({cookbook, shoppingList}) => {
            this.cookbook = cookbook;
            this.shoppingList = shoppingList;
        });
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
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
