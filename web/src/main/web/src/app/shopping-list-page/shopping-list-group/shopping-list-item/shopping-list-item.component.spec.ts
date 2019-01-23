import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ShoppingListItemComponent } from './shopping-list-item.component';
import { ShoppingListService } from '../../shopping-list.service';
import { ShoppingListServiceMock } from '../../shopping-list.service.mock';

describe('ShoppingListItemComponent', () => {
    let component: ShoppingListItemComponent;
    let fixture: ComponentFixture<ShoppingListItemComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ ShoppingListItemComponent ],
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
});
