import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { ShoppingListPageComponent } from './shopping-list-page.component';
import { ReactiveFormsModule } from "@angular/forms";
import { ShoppingListService } from "./shopping-list.service";
import { ShoppingListServiceMock } from "./shopping-list.service.mock";
import { of } from "rxjs/index";
import { Component, Input } from "@angular/core";
import { ShoppingListGroup } from "./shopping-list-group/shopping-list-group";
import { By } from "@angular/platform-browser";
import { HouseholdService } from "../household.service";
import { HouseholdServiceMock } from "../household.service.mock";

@Component({
    selector: [
        'app-shopping-list-group'
    ].join(','),
    template: '',
})
class MockComponent{
    @Input()
    public shoppingListGroup: ShoppingListGroup
}

describe('ShoppingListPageComponent', () => {
    let component: ShoppingListPageComponent;
    let fixture: ComponentFixture<ShoppingListPageComponent>;

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
            declarations: [
                ShoppingListPageComponent,
                MockComponent
            ],
            providers: [
                { provide: ShoppingListService, useClass: ShoppingListServiceMock },
                { provide: HouseholdService, useClass: HouseholdServiceMock }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ShoppingListPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should add a new shopping list group', () => {
        const shoppingListService: ShoppingListService = TestBed.get(ShoppingListService);
        spyOn(shoppingListService, 'addShoppingListGroup').and.returnValue(of({
            shoppingListGroups: [{
                name: 'Neue Gruppe',
                shoppingListItems: [],
                links: []
            }],
            links: []
        }));

        expect(component.shoppingListGroupForm.invalid).toBeTruthy();
        expect(component.shoppingList.shoppingListGroups.length).toBe(0);
        component.shoppingListGroupForm.controls.name.setValue('Neue Gruppe');

        fixture.detectChanges();

        const button = fixture.debugElement.query(By.css('#add-group-button'));
        button.nativeElement.click();
        expect(component.shoppingList.shoppingListGroups.length).toBe(1);
    });

});
