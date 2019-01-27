import {Component, Input, OnInit} from '@angular/core';
import {Recipe} from "./recipe";

@Component({
    selector: 'app-recipe',
    templateUrl: './recipe.component.html',
    styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

    @Input()
    public recipe: Recipe;

    private expanded: boolean = false;

    constructor() { }

    ngOnInit() {
    }

    public isExpanded(): boolean {
        return this.expanded;
    }

    public toggleRecipe(): void {
        this.expanded = !this.expanded;
    }

}
