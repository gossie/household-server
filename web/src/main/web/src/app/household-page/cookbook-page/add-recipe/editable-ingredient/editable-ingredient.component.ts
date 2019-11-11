import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Ingredient } from '../../recipe/ingredient/ingredient';

@Component({
    selector: 'app-editable-ingredient',
    templateUrl: './editable-ingredient.component.html',
    styleUrls: ['./editable-ingredient.component.sass']
})
export class EditableIngredientComponent implements OnInit {

    @Input()
    public ingredient: Ingredient;
    @Input()
    public index: number;

    @Output()
    public ingredientDeletionEmitter: EventEmitter<Ingredient> = new EventEmitter();

    constructor() { }

    public ngOnInit(): void {
    }

    public editIngredient(): void {
        
    }

    public deleteIngredient(): void {
        this.ingredientDeletionEmitter.emit(this.ingredient);
    }

}
