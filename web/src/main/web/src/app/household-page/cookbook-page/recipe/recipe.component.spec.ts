import { Component, EventEmitter, Input} from "@angular/core";
import { async, ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { RecipeComponent } from './recipe.component';
import { CookbookService } from "../cookbook.service";
import { CookbookServiceMock } from "../cookbook.service.mock";
import { Ingredient } from "./ingredient/ingredient";
import { Recipe } from "./recipe";
import { By } from "@angular/platform-browser";
import {Cookbook} from "../cookbook";

@Component({
    selector: [
        'app-ingredient'
    ].join(','),
    template: ''
})
class MockComponent {
    @Input()
    public ingredient: Ingredient;
}

describe('RecipeComponent', () => {
    let component: RecipeComponent;
    let fixture: ComponentFixture<RecipeComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                RecipeComponent,
                MockComponent
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

    it('should edit recipe', fakeAsync(() => {
        let selectedRecipe = null;

        const recipeEmitter: EventEmitter<Recipe> = new EventEmitter();
        recipeEmitter.subscribe((recipe: Recipe) => {
            selectedRecipe = recipe;
        });

        component.recipeEmitter = recipeEmitter;
        fixture.detectChanges();

        fixture.debugElement.query(By.css('.card-header-title')).nativeElement.click();
        fixture.detectChanges();
        tick();

        fixture.debugElement.query(By.css('#edit-button')).nativeElement.click();
        tick();
        expect(selectedRecipe).not.toBeNull();
    }));

    it('should delete recipe', fakeAsync(() => {
        let selectedCookbook = null;

        const cookbookEmitter: EventEmitter<Cookbook> = new EventEmitter();
        cookbookEmitter.subscribe((cookbook: Cookbook) => {
            selectedCookbook = cookbook;
        });

        component.cookbookEmitter = cookbookEmitter;
        fixture.detectChanges();

        fixture.debugElement.query(By.css('.card-header-title')).nativeElement.click();
        fixture.detectChanges();
        tick();

        fixture.debugElement.query(By.css('#delete-button')).nativeElement.click();
        tick();
        expect(selectedCookbook).not.toBeNull();
    }));

    it('should toggle recipe', fakeAsync(() => {
        fixture.debugElement.query(By.css('.card-header-title')).nativeElement.click();
        fixture.detectChanges();
        tick()

        fixture.debugElement.query(By.css('.card-header-title')).nativeElement.click();
        fixture.detectChanges();
        tick()
    }));
});
