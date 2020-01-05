import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AddRecipeComponent } from './add-recipe.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CookbookService } from '../cookbook.service';
import { CookbookServiceMock } from '../cookbook.service.mock';
import { By } from '@angular/platform-browser';
import { Cookbook } from '../cookbook';
import { EventEmitter } from '@angular/core';
import { EditableIngredientComponent } from './editable-ingredient/editable-ingredient.component';
import { IngredientFormComponent } from './ingredient-form/ingredient-form.component';
import { AmountPipe } from '../amount.pipe';

describe('AddRecipeComponent', () => {
    let component: AddRecipeComponent;
    let fixture: ComponentFixture<AddRecipeComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
            declarations: [
                AmountPipe,
                EditableIngredientComponent,
                IngredientFormComponent,
                AddRecipeComponent
            ],
            providers: [
                { provide: CookbookService, useClass: CookbookServiceMock }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(AddRecipeComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should create a new recipe', done => {
        const recipeNameField = fixture.debugElement.query(By.css('#recipe-name-field')).nativeElement;
        const amountField = fixture.debugElement.query(By.css('#amount-field')).nativeElement;
        const unitField = fixture.debugElement.query(By.css('#unit-field')).nativeElement;
        const nameField = fixture.debugElement.query(By.css('#name-field')).nativeElement;

        const cookbookEmitter: EventEmitter<Cookbook> = new EventEmitter();
        cookbookEmitter.subscribe((cookbook: Cookbook) => {
            expect(cookbook.recipes.length).toEqual(1);
            expect(cookbook.recipes[0].name).toEqual('Chili con carne');
            expect(cookbook.recipes[0].ingredients.length).toEqual(1);
            expect(cookbook.recipes[0].ingredients[0].amount).toEqual(500);
            expect(cookbook.recipes[0].ingredients[0].unit).toEqual('g');
            expect(cookbook.recipes[0].ingredients[0].name).toEqual ('Hack');
            expect(recipeNameField.value).toEqual('');
            expect(amountField.value).toEqual('');
            expect(unitField.value).toEqual('');
            expect(nameField.value).toEqual('');
            expect(component.ingredients).toEqual([]);
            expect(component.open).toBeFalsy();
            expect(component.loading).toBeFalsy();
            done();
        });
        component.cookbookEmitter = cookbookEmitter;
        component.ngOnChanges();
        fixture.detectChanges();

        expect(component.open).toBeFalsy();
        fixture.debugElement.query(By.css('a')).nativeElement.click();
        fixture.detectChanges();
        expect(component.open).toBeTruthy();

        const saveRecipeButton = fixture.debugElement.query(By.css('#save-recipe-button')).nativeElement;
        expect(saveRecipeButton.disabled).toBeTruthy();

        recipeNameField.value = 'Chili con carne';
        recipeNameField.dispatchEvent(new Event('input'));
        fixture.detectChanges();
        expect(saveRecipeButton.disabled).toBeFalsy();

        amountField.value = 500;
        amountField.dispatchEvent(new Event('input'));
        fixture.detectChanges();

        unitField.value = 'g';
        unitField.dispatchEvent(new Event('input'));
        fixture.detectChanges();

        nameField.value = 'Hack';
        nameField.dispatchEvent(new Event('input'));
        fixture.detectChanges();

        fixture.debugElement.query(By.css('#add-ingredient-button')).nativeElement.click();
        fixture.detectChanges();

        amountField.value = 500;
        amountField.dispatchEvent(new Event('input'));
        fixture.detectChanges();

        unitField.value = 'g';
        unitField.dispatchEvent(new Event('input'));
        fixture.detectChanges();

        nameField.value = 'Hack';
        nameField.dispatchEvent(new Event('input'));
        fixture.detectChanges();

        fixture.debugElement.query(By.css('#add-ingredient-button')).nativeElement.click();
        fixture.detectChanges();

        fixture.debugElement.query(By.css('#delete-ingredient-button-1')).nativeElement.click();
        fixture.detectChanges();

        fixture.debugElement.query(By.css('#save-recipe-button')).nativeElement.click();
        fixture.detectChanges();
    });

    it('should edit a recipe', done => {
        const recipeNameField = fixture.debugElement.query(By.css('#recipe-name-field')).nativeElement;
        const amountField = fixture.debugElement.query(By.css('#amount-field')).nativeElement;
        const unitField = fixture.debugElement.query(By.css('#unit-field')).nativeElement;
        const nameField = fixture.debugElement.query(By.css('#name-field')).nativeElement;
        const cookbookEmitter: EventEmitter<Cookbook> = new EventEmitter();

        cookbookEmitter.subscribe((cookbook: Cookbook) => {
            expect(cookbook.recipes.length).toEqual(1);
            expect(cookbook.recipes[0].name).toEqual('Chili con carne');
            expect(cookbook.recipes[0].ingredients.length).toEqual(2);
            expect(cookbook.recipes[0].ingredients[0].amount).toEqual(500);
            expect(cookbook.recipes[0].ingredients[0].unit).toEqual('g');
            expect(cookbook.recipes[0].ingredients[0].name).toEqual ('Hack');
            expect(cookbook.recipes[0].ingredients[1].amount).toEqual(1);
            expect(cookbook.recipes[0].ingredients[1].unit).toEqual('');
            expect(cookbook.recipes[0].ingredients[1].name).toEqual ('Zwiebel');
            expect(recipeNameField.value).toEqual('');
            expect(amountField.value).toEqual('');
            expect(unitField.value).toEqual('');
            expect(nameField.value).toEqual('');
            expect(component.ingredients).toEqual([]);
            expect(component.open).toBeFalsy();
            expect(component.loading).toBeFalsy();
            done();
        });

        component.recipe = {
            name: 'Chili con carne',
            ingredients: [
                {
                    amount: 500,
                    unit: 'g',
                    name: 'Hack'
                }
            ]
        };
        component.cookbookEmitter = cookbookEmitter;
        component.ngOnChanges();
        fixture.detectChanges();

        expect(component.open).toBeTruthy();

        const saveRecipeButton = fixture.debugElement.query(By.css('#save-recipe-button')).nativeElement;
        expect(saveRecipeButton.disabled).toBeFalsy();

        expect(recipeNameField.value).toEqual('Chili con carne');

        amountField.value = 1;
        amountField.dispatchEvent(new Event('input'));
        fixture.detectChanges();

        nameField.value = 'Zwiebel';
        nameField.dispatchEvent(new Event('input'));
        fixture.detectChanges();

        fixture.debugElement.query(By.css('#add-ingredient-button')).nativeElement.click();
        fixture.detectChanges();

        fixture.debugElement.query(By.css('#save-recipe-button')).nativeElement.click();
        fixture.detectChanges();
    });

    it('should close the dialog manually', () => {
        const recipeNameField = fixture.debugElement.query(By.css('#recipe-name-field')).nativeElement;
        const amountField = fixture.debugElement.query(By.css('#amount-field')).nativeElement;
        const unitField = fixture.debugElement.query(By.css('#unit-field')).nativeElement;
        const nameField = fixture.debugElement.query(By.css('#name-field')).nativeElement;

        component.recipe = {
            name: 'Chili con carne',
            ingredients: [
                {
                    amount: 500,
                    unit: 'g',
                    name: 'Hack'
                }
            ]
        };
        component.ngOnChanges();
        fixture.detectChanges();

        expect(component.open).toBeTruthy();

        fixture.debugElement.query(By.css('#close-button')).nativeElement.click();

        expect(component.recipe.name).toEqual('Chili con carne');
        expect(component.recipe.ingredients.length).toEqual(1);
        expect(component.recipe.ingredients[0].amount).toEqual(500);
        expect(component.recipe.ingredients[0].unit).toEqual('g');
        expect(component.recipe.ingredients[0].name).toEqual ('Hack');
        expect(recipeNameField.value).toEqual('');
        expect(amountField.value).toEqual('');
        expect(unitField.value).toEqual('');
        expect(nameField.value).toEqual('');
        expect(component.ingredients).toEqual([]);
        expect(component.open).toBeFalsy();
        expect(component.loading).toBeFalsy();
    });
});
