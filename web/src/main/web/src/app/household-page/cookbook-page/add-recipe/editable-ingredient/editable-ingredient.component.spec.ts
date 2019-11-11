import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { EditableIngredientComponent } from './editable-ingredient.component';
import { EventEmitter } from '@angular/core';
import { Ingredient } from '../../recipe/ingredient/ingredient';
import { IngredientFormComponent } from '../ingredient-form/ingredient-form.component';
import { ReactiveFormsModule } from '@angular/forms';

describe('EditableIngredientComponent', () => {
    let component: EditableIngredientComponent;
    let fixture: ComponentFixture<EditableIngredientComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
            declarations: [
                IngredientFormComponent,
                EditableIngredientComponent
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(EditableIngredientComponent);
        component = fixture.componentInstance;
        component.index = 0;
        component.ingredient = {
            amount: 500,
            unit: 'g',
            name: 'Hack'
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
        expect(component.editMode).toBeFalsy();
    });

    it('should emit`deleted event', done => {
        const ingredientDeletionEmitter: EventEmitter<Ingredient> = new EventEmitter();
        ingredientDeletionEmitter.subscribe((ingredient: Ingredient) => {
            expect(ingredient).toEqual({
                amount: 500,
                unit: 'g',
                name: 'Hack'
            });
            done();
        });
        component.ingredientDeletionEmitter = ingredientDeletionEmitter;

        component.deleteIngredient();
    });

    it('should edit an ingredient', () => {
        component.editIngredient();
        expect(component.editMode).toBeTruthy();

        component.saveIngredient({
            amount: 750,
            unit: 'g',
            name: 'Hack'
        });

        expect(component.editMode).toBeFalsy();
        expect(component.ingredient).toEqual({
            amount: 750,
            unit: 'g',
            name: 'Hack'
        });
    });
});
