import { ComponentFixture, TestBed, fakeAsync, tick, waitForAsync } from '@angular/core/testing';
import { ShoppingListItemComponent } from './shopping-list-item.component';
import { ShoppingListService } from '../../shopping-list.service';
import { ShoppingListServiceMock } from '../../shopping-list.service.mock';
import { CheckboxComponent } from '../../../../common-elements/checkbox/checkbox.component';
import { By } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { ShoppingListItem } from './shopping-list-item';
import { EventEmitter } from '@angular/core';
import { ShoppingList } from '../../shopping-list';
import { of } from 'rxjs';

describe('ShoppingListItemComponent', () => {
    let component: ShoppingListItemComponent;
    let fixture: ComponentFixture<ShoppingListItemComponent>;

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
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

    it('should switch to edit mode', (done) => {
        const shoppingListService: ShoppingListService = TestBed.get(ShoppingListService);

        const shoppingListEmitter = new EventEmitter();
        shoppingListEmitter.subscribe((shoppingList: ShoppingList) => {
            fixture.detectChanges();
            expect(shoppingList).toBeDefined();
            expect(shoppingListService.toggleShoppingListItem).not.toHaveBeenCalled();
            expect(fixture.debugElement.query(By.css('#edit-field'))).toBeNull();
            done();
        });
        component.shoppingListEmitter = shoppingListEmitter;

        spyOn(shoppingListService, 'toggleShoppingListItem');
        spyOn(shoppingListService, 'editShoppingListItem').and.callFake((item: ShoppingListItem) => {
            expect(item.name).toBe('Kartoffeln');
            expect(item.selected).toBeFalsy();
            return of({
                shoppingListGroups: [],
                links: []
            });
        });

        expect(fixture.debugElement.query(By.css('#edit-field'))).toBeNull();
        fixture.debugElement.query(By.css('#edit-mode-button')).nativeElement.click();
        fixture.detectChanges();
        expect(fixture.debugElement.query(By.css('#edit-field'))).not.toBeNull();

        expect(fixture.debugElement.query(By.css('#edit-field')).nativeElement.value).toBe('item');
        fixture.debugElement.query(By.css('#edit-field')).nativeElement.value = 'Kartoffeln';
        const event = new Event('input', {
            bubbles: true,
            cancelable: true,
        });
        fixture.debugElement.query(By.css('#edit-field')).nativeElement.dispatchEvent(event);

        fixture.debugElement.query(By.css('#save-button')).nativeElement.click();
        fixture.detectChanges();
    });

    describe('image', () => {

        it('should not have an image', () => {
            expect(fixture.debugElement.query(By.css('#image-dialog-button'))).toBeNull();
            expect(fixture.debugElement.query(By.css('#image-upload-button'))).not.toBeNull();
        });

        it('should have an image', () => {
            component.shoppingListItem = {
                name: 'item',
                selected: false,
                links: [
                    { rel: 'image', href: '/api/imageUrl' }
                ]
            };
            fixture.detectChanges();

            expect(fixture.debugElement.query(By.css('#image-dialog-button'))).not.toBeNull();
            expect(fixture.debugElement.query(By.css('#image-upload-button'))).not.toBeNull();
        });

    });
});
