import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { Ingredient } from "../recipe/ingredient/ingredient";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CookbookService } from "../cookbook.service";
import { Cookbook } from "../cookbook";
import { Recipe } from "../recipe/recipe";
import { ObjectUtils } from "../../../object.utils";

@Component({
    selector: 'app-add-recipe',
    templateUrl: './add-recipe.component.html',
    styleUrls: ['./add-recipe.component.sass']
})
export class AddRecipeComponent implements OnInit, OnChanges {

    @Input()
    public cookbook: Cookbook;
    @Input()
    public recipe: Recipe;
    @Output()
    public cookbookEmitter: EventEmitter<Cookbook> = new EventEmitter();

    public open = false;
    public loading = false;
    public ingredients: Array<Ingredient> = [];
    public recipeForm: FormGroup;
    public ingredientForm: FormGroup;

    constructor(private formBuilder: FormBuilder,
                private cookbookService: CookbookService) { }

    public ngOnInit() {
        this.createForms();
    }

    public ngOnChanges() {
        if (ObjectUtils.isObject(this.recipe)) {
            this.recipeForm.controls.recipeName.setValue(this.recipe.name);
            this.recipeForm.controls.recipeUrl.setValue(this.recipe.url)
            this.ingredients = this.recipe.ingredients;
            this.open = true;
        }
    }

    private createForms(): void {
        this.recipeForm = this.formBuilder.group({
            recipeName: ['', Validators.required],
            recipeUrl: ['']
        });

        this.ingredientForm = this.formBuilder.group({
            amount: [''],
            unit: [''],
            name: ['', Validators.required]
        });
    }

    public openDialog(): void {
        this.open = true;
    }

    public closeDialog(): void {
        this.resetFields();
        this.open = false;
        this.cookbookEmitter.emit(this.cookbook);
    }

    public addIngredient(): void {
        this.ingredients.push({
            amount: parseFloat(this.ingredientForm.controls.amount.value),
            unit: this.ingredientForm.controls.unit.value,
            name: this.ingredientForm.controls.name.value,
        });
        this.ingredientForm.controls.amount.reset();
        this.ingredientForm.controls.unit.reset();
        this.ingredientForm.controls.name.reset();
    }

    public deleteIngredient(ingredient: Ingredient): void {
        this.ingredients.splice(this.ingredients.indexOf(ingredient), 1);
    }

    public saveRecipe(): void {
        this.loading = true;
        if (ObjectUtils.isObject(this.recipe)) {
            this.editRecipe();
        } else {
            this.createRecipe();
        }
    }

    private editRecipe(): void {
        this.recipe.name = this.recipeForm.controls.recipeName.value;
        this.recipe.url = this.recipeForm.controls.recipeUrl.value;
        this.recipe.ingredients = this.ingredients;
        this.cookbookService.editRecipe(this.recipe)
            .subscribe((cookbook: Cookbook) => {
                this.resetFields();

                this.loading = false;
                this.open = false;
                this.cookbookEmitter.emit(cookbook);
            });
    }

    private createRecipe(): void {
        const recipe: Recipe = {
            name: this.recipeForm.controls.recipeName.value,
            url: this.recipeForm.controls.recipeUrl.value,
            ingredients: this.ingredients
        };
        this.cookbookService.createRecipe(this.cookbook, recipe)
            .subscribe((cookbook: Cookbook) => {
                this.resetFields();

                this.loading = false;
                this.open = false;
                this.cookbookEmitter.emit(cookbook);
            });
    }

    private resetFields(): void {
        this.ingredients = [];

        this.recipeForm.controls.recipeName.reset();
        this.recipeForm.controls.recipeUrl.reset();
        this.ingredientForm.controls.amount.reset();
        this.ingredientForm.controls.unit.reset();
        this.ingredientForm.controls.name.reset();
    }
}
