import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { CookbookPageComponent } from './cookbook-page.component';
import { RouterTestingModule } from '@angular/router/testing';
import { UserService } from '../../user.service';
import { UserServiceMock } from '../../user.service.mock';
import { RecipeComponent } from './recipe/recipe.component';
import { HouseholdService } from '../household.service';
import { HouseholdServiceMock } from '../household.service.mock';
import { CookbookServiceMock } from './cookbook.service.mock';
import { CookbookService } from './cookbook.service';
import { IngredientComponent } from './recipe/ingredient/ingredient.component';
import { AddRecipeComponent } from './add-recipe/add-recipe.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SelectRecipeComponent } from '../../common-elements/select-recipe/select-recipe.component';
import { CheckboxComponent } from '../../common-elements/checkbox/checkbox.component';
import { ShoppingListService } from '../shopping-list-page/shopping-list.service';
import { ShoppingListServiceMock } from '../shopping-list-page/shopping-list.service.mock';
import { EditableIngredientComponent } from './add-recipe/editable-ingredient/editable-ingredient.component';
import { IngredientFormComponent } from './add-recipe/ingredient-form/ingredient-form.component';
import { AmountPipe } from './amount.pipe';

describe('CookbookPageComponent', () => {
    let component: CookbookPageComponent;
    let fixture: ComponentFixture<CookbookPageComponent>;

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule,
                ReactiveFormsModule // remove again when add-recipe-component is mocked
            ],
            declarations: [
                CookbookPageComponent,
                RecipeComponent,
                AmountPipe,
                IngredientComponent,
                AddRecipeComponent,
                EditableIngredientComponent,
                IngredientFormComponent,
                SelectRecipeComponent,
                CheckboxComponent
            ],
            providers: [
                { provide: UserService, useClass: UserServiceMock },
                { provide: HouseholdService, useClass: HouseholdServiceMock },
                { provide: CookbookService, useClass: CookbookServiceMock },
                { provide: ShoppingListService, useClass: ShoppingListServiceMock }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CookbookPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should order recipes', () => {
        expect(component.cookbook.recipes).toEqual([
            {
                name: 'Chili con carne'
            },
            {
                name: 'chili sin carne'
            },
            {
                name: 'Curry'
            },
            {
                name: 'Käsekuchen'
            }
        ])
    })
});
