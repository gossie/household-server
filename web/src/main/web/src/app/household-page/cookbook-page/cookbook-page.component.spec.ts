import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CookbookPageComponent } from './cookbook-page.component';
import { RouterTestingModule } from "@angular/router/testing";
import { UserService } from "../../user.service";
import { UserServiceMock } from "../../user.service.mock";
import { RecipeComponent } from "./recipe/recipe.component";
import { HouseholdService } from "../household.service";
import { HouseholdServiceMock } from "../household.service.mock";
import { CookbookServiceMock } from "./cookbook.service.mock";
import { CookbookService } from "./cookbook.service";

describe('CookbookPageComponent', () => {
    let component: CookbookPageComponent;
    let fixture: ComponentFixture<CookbookPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule
            ],
            declarations: [
                CookbookPageComponent,
                RecipeComponent
            ],
            providers: [
                { provide: UserService, useClass: UserServiceMock },
                { provide: HouseholdService, useClass: HouseholdServiceMock },
                { provide: CookbookService, useClass: CookbookServiceMock }
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
});
