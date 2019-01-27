import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FoodPlanPageComponent } from './food-plan-page.component';
import { ReactiveFormsModule } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { FoodPlan } from "./food-plan";
import {MealComponent} from "./meal/meal.component";
import {FoodPlanService} from "./food-plan.service";
import {FoodPlanServiceMock} from "./food-plan.service.mock";

describe('FoodPlanPageComponent', () => {
    let component: FoodPlanPageComponent;
    let fixture: ComponentFixture<FoodPlanPageComponent>;
    const foodPlan: FoodPlan = {
        meals: {
            monday: {
                name: '',
            },
            tuesday: {
                name: '',
            },
            wednesday: {
                name: '',
            },
            thursday: {
                name: '',
            },
            friday: {
                name: '',
            },
            saturday: {
                name: '',
            },
            sunday: {
                name: '',
            }
        }
    };

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
            declarations: [
                FoodPlanPageComponent,
                MealComponent
            ],
            providers: [
                { provide: FoodPlanService, useClass: FoodPlanServiceMock },
                { provide: ActivatedRoute, useValue: { snapshot: { data: { foodPlan: foodPlan } } } }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(FoodPlanPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
