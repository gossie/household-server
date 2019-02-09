import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectRecipeComponent } from './select-recipe.component';
import {Ingredient} from "../../cookbook-page/recipe/ingredient/ingredient";
import {EventEmitter} from "@angular/core";
import {By} from "@angular/platform-browser";

describe('SelectRecipeComponent', () => {
    let component: SelectRecipeComponent;
    let fixture: ComponentFixture<SelectRecipeComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ SelectRecipeComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(SelectRecipeComponent);
        component = fixture.componentInstance;
        component.recipe = {
            name: 'Chili con carne',
            ingredients: [
                {
                    amount: 500,
                    unit: 'g',
                    name: 'Hack'
                },
                {
                    amount: 1,
                    unit: '',
                    name: 'Zwiebel'
                },
                {
                    amount: 1,
                    unit: '',
                    name: 'Knoblauchzehe'
                }
            ]
        }
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should select ingredients', done => {
        const ingredientsEmitter: EventEmitter<Set<string>> = new EventEmitter();
        ingredientsEmitter.subscribe((ingredientNames: Set<string>) => {
            expect(ingredientNames.size).toEqual(2);
            expect(ingredientNames.has('Hack')).toBeTruthy();
            expect(ingredientNames.has('Knoblauchzehe')).toBeTruthy();
            done();
        });

        component.ingredientsEmitter = ingredientsEmitter;

        fixture.debugElement.query(By.css('#ingredient-0')).nativeElement.click();
        fixture.debugElement.query(By.css('#ingredient-1')).nativeElement.click();
        fixture.debugElement.query(By.css('#ingredient-2')).nativeElement.click();
        fixture.debugElement.query(By.css('#ingredient-1')).nativeElement.click();
        fixture.debugElement.query(By.css('#save-ingredients-button')).nativeElement.click();
    });
});
