import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectRecipeComponent } from './select-recipe.component';

describe('SelectRecipeComponent', () => {
    let component: SelectRecipeComponent;
    let fixture: ComponentFixture<SelectRecipeComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ SelectRecipeComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(SelectRecipeComponent);
        component = fixture.componentInstance;
        component.recipe = {
            name: 'Chili con carne',
            ingredients: []
        }
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
