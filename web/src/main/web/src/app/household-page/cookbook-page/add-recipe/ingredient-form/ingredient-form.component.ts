import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { UntypedFormGroup, UntypedFormBuilder, Validators } from '@angular/forms';
import { Ingredient } from '../../recipe/ingredient/ingredient';

@Component({
    selector: 'app-ingredient-form',
    templateUrl: './ingredient-form.component.html',
    styleUrls: ['./ingredient-form.component.sass']
})
export class IngredientFormComponent implements OnInit {

    @Input()
    public editMode = false;
    @Input()
    public initialAmount = '';
    @Input()
    public initialUnit = '';
    @Input()
    public initialName = '';

    @Output()
    public ingredientEmitter: EventEmitter<Ingredient> = new EventEmitter();

    public ingredientForm: UntypedFormGroup;

    constructor(private formBuilder: UntypedFormBuilder) { }

    public ngOnInit(): void {
        this.ingredientForm = this.formBuilder.group({
            amount: [this.initialAmount],
            unit: [this.initialUnit],
            name: [this.initialName, Validators.required]
        });
    }

    public handleIngredient(): void {
        this.ingredientEmitter.emit({
            amount: parseFloat(this.ingredientForm.controls.amount.value),
            unit: this.ingredientForm.controls.unit.value,
            name: this.ingredientForm.controls.name.value,
        });
        this.ingredientForm.controls.amount.reset();
        this.ingredientForm.controls.unit.reset();
        this.ingredientForm.controls.name.reset();
    }
}
