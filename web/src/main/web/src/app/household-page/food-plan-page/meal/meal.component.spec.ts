import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MealComponent } from './meal.component';
import { FormGroup, ReactiveFormsModule } from "@angular/forms";

describe('MealComponent', () => {
    let component: MealComponent;
    let fixture: ComponentFixture<MealComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
            declarations: [ MealComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(MealComponent);
        component = fixture.componentInstance;
        component.parentForm = new FormGroup({});
        component.meal = {
            name: ''
        }
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
