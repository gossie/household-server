import {Component, Input, OnInit} from '@angular/core';
import {Ingredient} from "./ingredient";

@Component({
    selector: 'app-ingredient',
    templateUrl: './ingredient.component.html',
    styleUrls: ['./ingredient.component.sass']
})
export class IngredientComponent implements OnInit {

    @Input()
    public ingredient: Ingredient;

    constructor() { }

    ngOnInit() {
    }

}
