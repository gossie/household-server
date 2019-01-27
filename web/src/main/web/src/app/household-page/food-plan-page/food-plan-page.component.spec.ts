import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodPlanPageComponent } from './food-plan-page.component';
import {RouterTestingModule} from "@angular/router/testing";

describe('FoodPlanPageComponent', () => {
    let component: FoodPlanPageComponent;
    let fixture: ComponentFixture<FoodPlanPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule
            ],
            declarations: [ FoodPlanPageComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(FoodPlanPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});