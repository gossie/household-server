import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ChoreComponent } from './chore.component';
import { CleaningPlanService } from "../cleaning-plan.service";
import { CleaningPlanServiceMock } from "../cleaning-plan.service.mock";
import { ReactiveFormsModule } from "@angular/forms";

describe('ChoreComponent', () => {
    const THREE_DAYS: number = 259200000;
    const TWELVE_HOURS: number = 43200000;
    const OFFSET: number = 10;

    let component: ChoreComponent;
    let fixture: ComponentFixture<ChoreComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
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
            lastPerformed: Date.now(),
            nextTime: Date.now() + 60000,
            repeat: 0,
            links: []
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    describe('red chore', () => {

        it('should be red because date is reached', () => {
            component.chore = {
                name: 'chore',
                lastPerformed: Date.now() - THREE_DAYS,
                nextTime: Date.now(),
                repeat: 0,
                links: []
            };
            fixture.detectChanges();

            expect(component.isRed()).toBeTruthy();
        });

        it('should be red because date has passed', () => {
            component.chore = {
                name: 'chore',
                lastPerformed: Date.now() - THREE_DAYS,
                nextTime: Date.now() - OFFSET,
                repeat: 0,
                links: []
            };
            fixture.detectChanges();

            expect(component.isRed()).toBeTruthy();
        });

    });

    describe('yellow chore', () => {

        it('should be yellow because date is almost reached', () => {
            component.chore = {
                name: 'chore',
                lastPerformed: Date.now() - THREE_DAYS,
                nextTime: Date.now() + OFFSET,
                repeat: 0,
                links: []
            };
            fixture.detectChanges();

            expect(component.isYellow()).toBeTruthy();
        });

        it('should be yellow because limit is reached', () => {
            component.chore = {
                name: 'chore',
                lastPerformed: Date.now() - THREE_DAYS,
                nextTime: Date.now() + TWELVE_HOURS,
                repeat: 0,
                links: []
            };
            fixture.detectChanges();

            expect(component.isYellow()).toBeTruthy();
        });

    });

    describe('green chore', () => {

        it('should be green because limit is not yet reached', () => {
            component.chore = {
                name: 'chore',
                lastPerformed: Date.now() - THREE_DAYS,
                nextTime: Date.now() + TWELVE_HOURS + OFFSET,
                repeat: 0,
                links: []
            };
            fixture.detectChanges();

            expect(component.isGreen()).toBeTruthy();
        });

    });

});
