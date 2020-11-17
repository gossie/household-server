import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { SelectRecipeComponent } from './select-recipe.component';
import { EventEmitter } from "@angular/core";
import { By } from "@angular/platform-browser";
import { CheckboxComponent } from "../checkbox/checkbox.component";

describe('SelectRecipeComponent', () => {
    let component: SelectRecipeComponent;
    let fixture: ComponentFixture<SelectRecipeComponent>;

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            declarations: [
                CheckboxComponent,
                SelectRecipeComponent
            ]
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
        };
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

        component.toggleIngredient({
            selected: true,
            value: 'Hack'
        });

        component.toggleIngredient({
            selected: true,
            value: 'Zwiebel'
        });

        component.toggleIngredient({
            selected: true,
            value: 'Knoblauchzehe'
        });

        component.toggleIngredient({
            selected: false,
            value: 'Zwiebel'
        });
        fixture.debugElement.query(By.css('#save-ingredients-button')).nativeElement.click();
    });
});
