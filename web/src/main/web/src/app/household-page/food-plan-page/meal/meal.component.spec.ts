import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MealComponent } from './meal.component';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CookbookService } from '../../cookbook-page/cookbook.service';
import { CookbookServiceMock } from '../../cookbook-page/cookbook.service.mock';
import { By } from '@angular/platform-browser';
import { FoodPlanService } from '../food-plan.service';
import { FoodPlanServiceMock } from '../food-plan.service.mock';
import { RouterTestingModule } from '@angular/router/testing';

describe('MealComponent', () => {
    let component: MealComponent;
    let fixture: ComponentFixture<MealComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule,
                RouterTestingModule
            ],
            declarations: [
                MealComponent
            ],
            providers: [
                { provide: CookbookService, useClass: CookbookServiceMock },
                { provide: FoodPlanService, useClass: FoodPlanServiceMock }
            ]

        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(MealComponent);
        component = fixture.componentInstance;
        component.controlName='monday';
        component.parentForm = new FormGroup({});
        component.cookbook = {
            recipes: [
                {
                    name: 'Chili con carne'
                },
                {
                    name: 'Curry'
                },
                {
                    name: 'Käsekuchen'
                }
            ]
        };
        component.meal = {
            name: '',
            links: []
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should search for recipes', () => {
        const inputField: HTMLInputElement = fixture.debugElement.query(By.css('#monday')).nativeElement;
        inputField.value = 'Ch';
        inputField.focus();
        inputField.dispatchEvent(new Event('input'));
        fixture.detectChanges();

        expect(component.recipes).toEqual([{name: 'Chili con carne'}, {name: 'Käsekuchen'}]);

        const link: HTMLAnchorElement = fixture.debugElement.queryAll(By.css('a'))[0].nativeElement;
        link.click();
        link.dispatchEvent(new Event('click'));
        fixture.detectChanges();

        expect(component.parentForm.controls.monday.value).toEqual('Chili con carne');
        expect(component.recipes).toEqual([]);
    });
});
