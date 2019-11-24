import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IngredientComponent } from './ingredient.component';
import { AmountPipe } from '../../amount.pipe';

describe('IngredientComponent', () => {
    let component: IngredientComponent;
    let fixture: ComponentFixture<IngredientComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [
                AmountPipe,
                IngredientComponent
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(IngredientComponent);
        component = fixture.componentInstance;
        component.ingredient = {
            amount: 500,
            unit: 'ml',
            name: 'Wasser'
        }
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
