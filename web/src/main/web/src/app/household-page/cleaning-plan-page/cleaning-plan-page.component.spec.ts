import { Component, Input } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CleaningPlanPageComponent } from './cleaning-plan-page.component';
import { Chore } from './chore/chore';
import { ReactiveFormsModule } from "@angular/forms";
import { CleaningPlanService } from "./cleaning-plan.service";
import { CleaningPlanServiceMock } from "./cleaning-plan.service.mock";
import { HouseholdService } from "../household.service";
import { HouseholdServiceMock } from "../household.service.mock";

@Component({
    selector: [
        'app-chore'
    ].join(','),
    template: '',
})
class MockComponent{
    @Input()
    public chore: Chore;
}

describe('CleaningPlanPageComponent', () => {
    let component: CleaningPlanPageComponent;
    let fixture: ComponentFixture<CleaningPlanPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
            declarations: [
                CleaningPlanPageComponent,
                MockComponent
            ],
            providers: [
                { provide: HouseholdService, useClass: HouseholdServiceMock },
                { provide: CleaningPlanService, useClass: CleaningPlanServiceMock }
            ]
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

    it('should be valid', () => {
        expect(component.cleaningPlanForm.valid).toBeFalsy();

        component.cleaningPlanForm.controls.name.setValue('anyChore');
        expect(component.cleaningPlanForm.valid).toBeFalsy();

        component.cleaningPlanForm.controls.repeat.setValue(7);
        expect(component.cleaningPlanForm.valid).toBeTruthy();
    });

    it('should be valid, if repeat is zero', () => {
        expect(component.cleaningPlanForm.valid).toBeFalsy();

        component.cleaningPlanForm.controls.name.setValue('anyChore');
        expect(component.cleaningPlanForm.valid).toBeFalsy();

        component.cleaningPlanForm.controls.repeat.setValue(0);
        expect(component.cleaningPlanForm.valid).toBeTruthy();
    });

    

    it('should be valid, if repeat is below zero', () => {
        expect(component.cleaningPlanForm.valid).toBeFalsy();

        component.cleaningPlanForm.controls.name.setValue('anyChore');
        expect(component.cleaningPlanForm.valid).toBeFalsy();

        component.cleaningPlanForm.controls.repeat.setValue(-1);
        expect(component.cleaningPlanForm.valid).toBeFalsy();
    });
});
