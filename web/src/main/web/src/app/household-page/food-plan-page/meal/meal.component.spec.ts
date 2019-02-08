import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MealComponent } from './meal.component';
import { FormGroup, ReactiveFormsModule } from "@angular/forms";
import { CookbookService } from "../../cookbook-page/cookbook.service";
import { CookbookServiceMock } from "../../cookbook-page/cookbook.service.mock";
import { HouseholdService } from "../../household.service";
import { HouseholdServiceMock } from "../../household.service.mock";
import { By } from "@angular/platform-browser";

describe('MealComponent', () => {
    let component: MealComponent;
    let fixture: ComponentFixture<MealComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
            declarations: [
                MealComponent
            ],
            providers: [
                { provide: HouseholdService, useClass: HouseholdServiceMock },
                { provide: CookbookService, useClass: CookbookServiceMock }
            ]

        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(MealComponent);
        component = fixture.componentInstance;
        component.controlName='monday';
        component.parentForm = new FormGroup({});
        component.meal = {
            name: ''
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should search for recipes', () => {
        const inputField: HTMLInputElement = fixture.debugElement.query(By.css('#monday')).nativeElement;
        inputField.value = 'Ch';
        inputField.dispatchEvent(new Event('input'));
        fixture.detectChanges();

        expect(component.recipeNames).toEqual(['Chili con carne']);

        component.selectRecipe('Chili con carne');
        expect(component.parentForm.controls.monday.value).toEqual('Chili con carne');
    });
});
