import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Ingredient} from "../recipe/ingredient/ingredient";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CookbookService} from "../cookbook.service";
import {Cookbook} from "../cookbook";
import {Recipe} from "../recipe/recipe";

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
    public ingredientForm: FormGroup;

    constructor(private formBuilder: FormBuilder,
                private cookbookService: CookbookService) { }

    ngOnInit() {
        this.createForm();
    }

    private createForm(): void {
        this.ingredientForm = this.formBuilder.group({
            recipeName: ['', Validators.required],
            amount: [''],
            unit: [''],
            name: ['', Validators.required]
        });
    }

    public openDialog(): void {
        this.open = true;
    }

    public closeDialog(): void {
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
            name: this.ingredientForm.controls.recipeName.value,
            ingredients: this.ingredients
        };
        this.cookbookService.createRecipe(this.cookbook, recipe)
            .subscribe((cookbook: Cookbook) => {
                this.ingredients = [];
                
                this.ingredientForm.controls.recipeName.reset();
                this.ingredientForm.controls.amount.reset();
                this.ingredientForm.controls.unit.reset();
                this.ingredientForm.controls.name.reset();

                this.cookbookEmitter.emit(cookbook);
                this.loading = false;
                this.open = false;
            });
    }
}
