import {async, ComponentFixture, fakeAsync, TestBed, tick} from '@angular/core/testing';
import { ShoppingListPageComponent } from './shopping-list-page.component';
import { ReactiveFormsModule } from "@angular/forms";
import { ShoppingListService } from "./shopping-list.service";
import { ShoppingListServiceMock } from "./shopping-list.service.mock";
import { ActivatedRoute } from "@angular/router";
import { of } from "rxjs/index";
import { ShoppingList } from "./shopping-list";
import { Component, Input } from "@angular/core";
import { ShoppingListGroup } from "./shopping-list-group/shopping-list-group";
import { By } from "@angular/platform-browser";
import { ShoppingListGroupComponent } from "./shopping-list-group/shopping-list-group.component";

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
    const shoppingList: ShoppingList = {
        shoppingListGroups: [],
        links: []
    };

    beforeEach(async(() => {
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
                { provide: ActivatedRoute, useValue: { snapshot: { data: { shoppingList: shoppingList } } } }
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

        const button = fixture.debugElement.query(By.css('.button'));
        expect(component.shoppingListGroupForm.invalid).toBeTruthy();
        expect(button.nativeElement.disabled).toBeTruthy();
        expect(component.shoppingList.shoppingListGroups.length).toBe(0);

        component.shoppingListGroupForm.controls.name.setValue('Neue Gruppe');
        fixture.detectChanges();
        expect(button.nativeElement.disabled).toBeFalsy();

        button.nativeElement.click();
        expect(component.shoppingList.shoppingListGroups.length).toBe(1);
    });

});
