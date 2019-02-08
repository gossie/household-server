import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import { Recipe } from "./recipe";
import { CookbookService } from "../cookbook.service";
import { Cookbook } from "../cookbook";

@Component({
    selector: 'app-recipe',
    templateUrl: './recipe.component.html',
    styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

    @Input()
    public recipe: Recipe;
    @Output()
    public cookbookEmitter: EventEmitter<Cookbook> = new EventEmitter();
    @Output()
    public recipeEmitter: EventEmitter<Recipe> = new EventEmitter();

    private expanded: boolean = false;

    constructor(private cookbookService: CookbookService) { }

    ngOnInit() {
    }

    public isExpanded(): boolean {
        return this.expanded;
    }

    public toggleRecipe(): void {
        if (!this.expanded) {
            this.cookbookService.determineRecipe(this.recipe)
                .subscribe((recipe: Recipe) => {
                    this.recipe = recipe
                    this.expanded = !this.expanded;
                });
        } else {
            this.expanded = !this.expanded;
        }
    }

    public editRecipe(): void {
        this.recipeEmitter.emit(this.recipe);
    }

    public deleteRecipe(): void {
        this.cookbookService.deleteRecipe(this.recipe)
            .subscribe((cookbook: Cookbook) => {
                this.cookbookEmitter.emit(cookbook);
            });
    }

}
