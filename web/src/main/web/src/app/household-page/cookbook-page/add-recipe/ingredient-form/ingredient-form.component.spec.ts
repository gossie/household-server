import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IngredientFormComponent } from './ingredient-form.component';
import { ReactiveFormsModule } from '@angular/forms';

describe('IngredientFormComponent', () => {
    let component: IngredientFormComponent;
    let fixture: ComponentFixture<IngredientFormComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
            declarations: [
                IngredientFormComponent
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(IngredientFormComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
