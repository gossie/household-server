import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingListItemComponent } from './shopping-list-item.component';

describe('ShoppingListItemComponent', () => {
  let component: ShoppingListItemComponent;
  let fixture: ComponentFixture<ShoppingListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShoppingListItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingListItemComponent);
    component = fixture.componentInstance;
    component.shoppingListItem = {
        name: 'item',
        links: []
    }
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
