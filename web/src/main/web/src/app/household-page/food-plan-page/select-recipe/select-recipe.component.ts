import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Recipe} from "../../cookbook-page/recipe/recipe";
import {Ingredient} from "../../cookbook-page/recipe/ingredient/ingredient";
import {CheckboxValue} from "../../../common-elements/checkbox/checkbox-value";

@Component({
    selector: 'app-select-recipe',
    templateUrl: './select-recipe.component.html',
    styleUrls: ['./select-recipe.component.sass']
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

    public toggleIngredient(value: CheckboxValue): void {
        if (this.selectedIngredients.has(value.value)) {
            this.selectedIngredients.delete(value.value);
        } else {
            this.selectedIngredients.add(value.value);
        }
    }

    public isSelected(ingredient: Ingredient): boolean {
        return this.selectedIngredients.has(ingredient.name);
    }

    public saveIngredients(): void {
        this.loading = true;
        this.ingredientsEmitter.emit(this.selectedIngredients);
        this.selectedIngredients = new Set();
    }

}
