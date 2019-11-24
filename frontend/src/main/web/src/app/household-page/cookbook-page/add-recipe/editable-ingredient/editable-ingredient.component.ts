import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Ingredient } from '../../recipe/ingredient/ingredient';

@Component({
    selector: 'app-editable-ingredient',
    templateUrl: './editable-ingredient.component.html',
    styleUrls: ['./editable-ingredient.component.sass']
})
export class EditableIngredientComponent {

    @Input()
    public ingredient: Ingredient;
    @Input()
    public index: number;
    @Output()
    public ingredientDeletionEmitter: EventEmitter<Ingredient> = new EventEmitter();

    public editMode = false;

    constructor() { }

    public editIngredient(): void {
        this.editMode = true;
    }

    public saveIngredient(ingredient: Ingredient): void {
        this.ingredient.amount = ingredient.amount;
        this.ingredient.unit = ingredient.unit;
        this.ingredient.name = ingredient.name;
        this.editMode = false;
    }

    public deleteIngredient(): void {
        this.ingredientDeletionEmitter.emit(this.ingredient);
    }

}
