import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ShoppingListGroupComponent } from './shopping-list-group.component';
import { Component, Input } from "@angular/core";
import { ShoppingListItem } from "./shopping-list-item/shopping-list-item";
import { ReactiveFormsModule } from "@angular/forms";
import { ShoppingListService } from "../shopping-list.service";
import { ShoppingListServiceMock } from "../shopping-list.service.mock";
import {CheckboxComponent} from "../../../common-elements/checkbox/checkbox.component";

@Component({
    selector: [
        'app-shopping-list-item'
    ].join(','),
    template: '',
})
class MockComponent{
    @Input()
    public shoppingListItem: ShoppingListItem
}

describe('ShoppingListGroupComponent', () => {
    let component: ShoppingListGroupComponent;
    let fixture: ComponentFixture<ShoppingListGroupComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
            declarations: [
                CheckboxComponent,
                ShoppingListGroupComponent,
                MockComponent
            ],
            providers: [
                { provide: ShoppingListService, useClass: ShoppingListServiceMock }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ShoppingListGroupComponent);
        component = fixture.componentInstance;
        component.shoppingListGroup = {
            name: 'group',
            shoppingListItems: [
                {
                    name: 'Item 1',
                    selected: false
                },
                {
                    name: 'Item 2',
                    selected: false
                }
            ],
            links: []
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should activate clear button', () => {
        component.shoppingListGroup = {
            name: 'group',
            shoppingListItems: [
                {
                    name: 'Item 1',
                    selected: false
                },
                {
                    name: 'Item 2',
                    selected: true
                }
            ],
            links: []
        };
        fixture.detectChanges();
        component.ngOnChanges();
        expect(component.clearButtonActive).toBeTruthy();
    });

    it('should deactivate clear button', () => {
        expect(component.clearButtonActive).toBeFalsy();
    });
});
