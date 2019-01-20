import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CleaningPlanPageComponent } from './cleaning-plan-page.component';
import {RouterTestingModule} from "@angular/router/testing";

describe('CleaningPlanPageComponent', () => {
    let component: CleaningPlanPageComponent;
    let fixture: ComponentFixture<CleaningPlanPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule
            ],
            declarations: [ CleaningPlanPageComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CleaningPlanPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
