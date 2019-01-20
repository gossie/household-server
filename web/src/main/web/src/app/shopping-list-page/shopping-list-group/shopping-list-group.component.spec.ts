import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ShoppingListGroupComponent } from './shopping-list-group.component';
import { ShoppingListItemComponent } from "./shopping-list-item/shopping-list-item.component";

describe('ShoppingListGroupComponent', () => {
    let component: ShoppingListGroupComponent;
    let fixture: ComponentFixture<ShoppingListGroupComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                ShoppingListGroupComponent,
                ShoppingListItemComponent
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ShoppingListGroupComponent);
        component = fixture.componentInstance;
        component.shoppingListGroup = {
            name: 'group',
            shoppingListItems: [],
            links: []
        }
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
