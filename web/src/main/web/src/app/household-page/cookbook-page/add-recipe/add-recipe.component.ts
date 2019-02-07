import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Ingredient } from "../recipe/ingredient/ingredient";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CookbookService } from "../cookbook.service";
import { Cookbook } from "../cookbook";
import { Recipe } from "../recipe/recipe";

@Component({
    selector: 'app-add-recipe',
    templateUrl: './add-recipe.component.html',
    styleUrls: ['./add-recipe.component.css']
})
export class AddRecipeComponent implements OnInit {

    @Input()
    public cookbook: Cookbook;
    @Output()
    public cookbookEmitter: EventEmitter<Cookbook> = new EventEmitter();

    public open: boolean = false;
    public loading: boolean = false;
    public ingredients: Array<Ingredient> = [];
    public recipeForm: FormGroup;
    public ingredientForm: FormGroup;

    constructor(private formBuilder: FormBuilder,
                private cookbookService: CookbookService) { }

    ngOnInit() {
        this.createForms();
    }

    private createForms(): void {
        this.recipeForm = this.formBuilder.group({
            recipeName: ['', Validators.required]
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
    }

    public addIngredient(): void {
        this.ingredients.push({
            amount: this.ingredientForm.controls.amount.value,
            unit: this.ingredientForm.controls.unit.value,
            name: this.ingredientForm.controls.name.value,
        });
        this.ingredientForm.controls.amount.reset();
        this.ingredientForm.controls.unit.reset();
        this.ingredientForm.controls.name.reset();
    }

    public createRecipe(): void {
        this.loading = true;
        const recipe: Recipe = {
            name: this.recipeForm.controls.recipeName.value,
            ingredients: this.ingredients
        };
        this.cookbookService.createRecipe(this.cookbook, recipe)
            .subscribe((cookbook: Cookbook) => {
                this.resetFields();

                this.cookbookEmitter.emit(cookbook);
                this.loading = false;
                this.open = false;
            });
    }

    private resetFields(): void {
        this.ingredients = [];

        this.recipeForm.controls.recipeName.reset();
        this.ingredientForm.controls.amount.reset();
        this.ingredientForm.controls.unit.reset();
        this.ingredientForm.controls.name.reset();
    }
}
