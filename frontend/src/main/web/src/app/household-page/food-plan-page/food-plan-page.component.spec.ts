import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FoodPlanPageComponent } from './food-plan-page.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MealComponent } from './meal/meal.component';
import { FoodPlanService } from './food-plan.service';
import { FoodPlanServiceMock } from './food-plan.service.mock';
import { HouseholdService } from '../household.service';
import { HouseholdServiceMock } from '../household.service.mock';
import { CookbookService } from '../cookbook-page/cookbook.service';
import { CookbookServiceMock } from '../cookbook-page/cookbook.service.mock';
import { SelectRecipeComponent } from '../../common-elements/select-recipe/select-recipe.component';
import { ShoppingListService } from '../shopping-list-page/shopping-list.service';
import { ShoppingListServiceMock } from '../shopping-list-page/shopping-list.service.mock';
import { CheckboxComponent } from '../../common-elements/checkbox/checkbox.component';
import { RouterTestingModule } from '@angular/router/testing';

describe('FoodPlanPageComponent', () => {
    let component: FoodPlanPageComponent;
    let fixture: ComponentFixture<FoodPlanPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule,
                RouterTestingModule
            ],
            declarations: [
                CheckboxComponent,
                FoodPlanPageComponent,
                MealComponent,
                SelectRecipeComponent
            ],
            providers: [
                { provide: FoodPlanService, useClass: FoodPlanServiceMock },
                { provide: ShoppingListService, useClass: ShoppingListServiceMock },
                { provide: HouseholdService, useClass: HouseholdServiceMock },
                { provide: CookbookService, useClass: CookbookServiceMock }
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
