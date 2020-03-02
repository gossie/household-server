import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ShoppingListItemComponent } from './shopping-list-item.component';
import { ShoppingListService } from '../../shopping-list.service';
import { ShoppingListServiceMock } from '../../shopping-list.service.mock';
import { CheckboxComponent } from '../../../../common-elements/checkbox/checkbox.component';
import { By } from '@angular/platform-browser';

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
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should switch to edit mode', () => {
        const shoppingListService: ShoppingListService = TestBed.get(ShoppingListService);
        spyOn(shoppingListService, 'toggleShoppingListItem');

        expect(fixture.debugElement.query(By.css('#edit-field'))).toBeNull();
        fixture.debugElement.query(By.css('#edit-mode-button')).nativeElement.click();
        fixture.detectChanges();
        expect(fixture.debugElement.query(By.css('#edit-field'))).not.toBeNull();
        expect(shoppingListService.toggleShoppingListItem).not.toHaveBeenCalled();

        // TODO: change text

        fixture.debugElement.query(By.css('#save-button')).nativeElement.click();
        fixture.detectChanges();
        expect(fixture.debugElement.query(By.css('#edit-field'))).toBeNull();
        expect(shoppingListService.toggleShoppingListItem).not.toHaveBeenCalled();

        // TODO: check rest call
    });
});
