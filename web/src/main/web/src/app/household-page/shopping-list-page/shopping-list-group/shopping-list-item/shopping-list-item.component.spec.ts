import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ShoppingListItemComponent } from './shopping-list-item.component';
import { ShoppingListService } from '../../shopping-list.service';
import { ShoppingListServiceMock } from '../../shopping-list.service.mock';
import { CheckboxComponent } from "../../../../common-elements/checkbox/checkbox.component";

describe('ShoppingListItemComponent', () => {
    let component: ShoppingListItemComponent;
    let fixture: ComponentFixture<ShoppingListItemComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                CheckboxComponent,
                ShoppingListItemComponent
            ],
            providers: [
                { provide: ShoppingListService, useClass: ShoppingListServiceMock }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ShoppingListItemComponent);
        component = fixture.componentInstance;
        component.shoppingListItem = {
            name: 'item',
            selected: false,
            links: []
        }
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should switch to edit mode', () => {
        expect(component.editMode).toBeFalsy();
        component.enableEditMode();
        expect(component.editMode).toBeTruthy();
    });
});
