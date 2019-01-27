import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RecipesPageComponent } from './recipes-page.component';
import { RouterTestingModule } from "@angular/router/testing";
import { UserService } from "../../user.service";
import { UserServiceMock } from "../../user.service.mock";
import { RecipeComponent } from "./recipe/recipe.component";
import {ActivatedRoute} from "@angular/router";
import {Cookbook} from "./cookbook";

describe('RecipesPageComponent', () => {
    let component: RecipesPageComponent;
    let fixture: ComponentFixture<RecipesPageComponent>;
    const cookbook: Cookbook = {
        recipes: []
    };

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule
            ],
            declarations: [
                RecipesPageComponent,
                RecipeComponent
            ],
            providers: [
                { provide: UserService, useClass: UserServiceMock },
                { provide: ActivatedRoute, useValue: { snapshot: { data: { cookbook: cookbook } } } }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(RecipesPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
