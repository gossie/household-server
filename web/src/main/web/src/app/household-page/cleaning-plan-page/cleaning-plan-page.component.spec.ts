import { Component, Input } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from "@angular/router";
import { CleaningPlanPageComponent } from './cleaning-plan-page.component';
import { CleaningPlan } from './cleaning-plan';
import { Chore } from './chore/chore';
import {ReactiveFormsModule} from "@angular/forms";
import {CleaningPlanService} from "./cleaning-plan.service";
import {CleaningPlanServiceMock} from "./cleaning-plan.service.mock";

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
    const cleaningPlan: CleaningPlan = {
        chores: [],
        links: []
    }

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
                { provide: ActivatedRoute, useValue: { snapshot: { data: { cleaningPlan: cleaningPlan } } } },
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
});
