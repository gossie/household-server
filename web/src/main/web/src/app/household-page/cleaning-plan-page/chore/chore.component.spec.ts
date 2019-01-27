import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoreComponent } from './chore.component';
import { CleaningPlanService } from "../cleaning-plan.service";
import { CleaningPlanServiceMock } from "../cleaning-plan.service.mock";

describe('ChoreComponent', () => {
    let component: ChoreComponent;
    let fixture: ComponentFixture<ChoreComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ ChoreComponent ],
            providers: [
                { provide: CleaningPlanService, useClass: CleaningPlanServiceMock }
            ]
        }).compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ChoreComponent);
        component = fixture.componentInstance;
        component.chore = {
            name: 'chore',
            lastPerformed: new Date(),
            nextTime: new Date(),
            repeat: 0,
            links: []
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
