import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Recipe} from "../../cookbook-page/recipe/recipe";
import {Ingredient} from "../../cookbook-page/recipe/ingredient/ingredient";

@Component({
    selector: 'app-select-recipe',
    templateUrl: './select-recipe.component.html',
    styleUrls: ['./select-recipe.component.css']
})
export class SelectRecipeComponent implements OnInit {

    @Input()
    public recipe: Recipe;
    @Output()
    public ingredientsEmitter: EventEmitter<Set<string>> = new EventEmitter();

    public loading: boolean = false;

    private selectedIngredients: Set<string> = new Set();

    constructor() { }

    public ngOnInit() {
        this.loading = false;
    }

    public toggleIngredient(ingredient: Ingredient): void {
        if (this.selectedIngredients.has(ingredient.name)) {
            this.selectedIngredients.delete(ingredient.name);
        } else {
            this.selectedIngredients.add(ingredient.name);
        }
    }

    public saveIngredients(): void {
        this.loading = true;
        this.ingredientsEmitter.emit(this.selectedIngredients);
        this.selectedIngredients = new Set();
    }

}
