import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingListPageComponent } from './shopping-list-page.component';
import { RouterTestingModule } from "@angular/router/testing";
import { ShoppingListGroupComponent } from "./shopping-list-group/shopping-list-group.component";
import { ShoppingListItem } from "./shopping-list-group/shopping-list-item/shopping-list-item";
import {ShoppingListItemComponent} from "./shopping-list-group/shopping-list-item/shopping-list-item.component";

describe('ShoppingListPageComponent', () => {
    let component: ShoppingListPageComponent;
    let fixture: ComponentFixture<ShoppingListPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule
            ],
            declarations: [
                ShoppingListPageComponent,
                ShoppingListGroupComponent,
                ShoppingListItemComponent
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
});
