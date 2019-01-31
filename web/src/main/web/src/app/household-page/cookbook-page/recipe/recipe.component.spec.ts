import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeComponent } from './recipe.component';
import { CookbookService } from "../cookbook.service";
import { CookbookServiceMock } from "../cookbook.service.mock";
import { IngredientComponent } from "./ingredient/ingredient.component";

describe('RecipeComponent', () => {
    let component: RecipeComponent;
    let fixture: ComponentFixture<RecipeComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                RecipeComponent,
                IngredientComponent
            ],
            providers: [
                { provide: CookbookService, useClass: CookbookServiceMock }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(RecipeComponent);
        component = fixture.componentInstance;
        component.recipe = {
            name: 'Recipe'
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
