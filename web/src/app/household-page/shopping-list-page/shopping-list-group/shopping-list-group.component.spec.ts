import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { ShoppingListGroupComponent } from './shopping-list-group.component';
import { Component, Input } from "@angular/core";
import { ShoppingListItem } from "./shopping-list-item/shopping-list-item";
import { ReactiveFormsModule } from "@angular/forms";
import { ShoppingListService } from "../shopping-list.service";
import { ShoppingListServiceMock } from "../shopping-list.service.mock";
import {CheckboxComponent} from "../../../common-elements/checkbox/checkbox.component";

@Component({
    selector: [
        'app-shopping-list-item',
        'app-progress-bar'
    ].join(','),
    template: '',
})
class MockComponent{
    @Input()
    public shoppingListItem: ShoppingListItem;
    @Input()
    public numberOfSelectedItems: number;
    @Input()
    public numberOfItems: number;
}

describe('ShoppingListGroupComponent', () => {
    let component: ShoppingListGroupComponent;
    let fixture: ComponentFixture<ShoppingListGroupComponent>;

    beforeEach(waitForAsync(() => {
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
    });

    it('should create', () => {
        fixture.detectChanges();
        expect(component).toBeTruthy();
        expect(component.numberOfSelectedItems).toEqual(0);
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
        expect(component.numberOfSelectedItems).toEqual(1);
    });

    it('should deactivate clear button', () => {
        fixture.detectChanges();
        expect(component.clearButtonActive).toBeFalsy();
    });

    describe('toggle', () => {

        it('should be initially expanded', () => {
            component.shoppingListGroup.name = 'Global';
            fixture.detectChanges();

            expect(component.expanded).toBeTruthy();
        });

        it('should be initially collapsed', () => {
            fixture.detectChanges();

            expect(component.expanded).toBeFalsy();
        });

    });

});
