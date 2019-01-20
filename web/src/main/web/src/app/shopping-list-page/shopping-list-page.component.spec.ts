import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingListPageComponent } from './shopping-list-page.component';
import { RouterTestingModule } from "@angular/router/testing";

describe('ShoppingListPageComponent', () => {
    let component: ShoppingListPageComponent;
    let fixture: ComponentFixture<ShoppingListPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule
            ],
            declarations: [ ShoppingListPageComponent ]
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
