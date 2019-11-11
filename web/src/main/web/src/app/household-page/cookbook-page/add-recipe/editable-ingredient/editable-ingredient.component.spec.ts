import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { EditableIngredientComponent } from './editable-ingredient.component';
import { EventEmitter } from '@angular/core';
import { Ingredient } from '../../recipe/ingredient/ingredient';

describe('EditableIngredientComponent', () => {
    let component: EditableIngredientComponent;
    let fixture: ComponentFixture<EditableIngredientComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ EditableIngredientComponent ]
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
});
