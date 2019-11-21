import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ProgressBarComponent } from './progress-bar.component';

describe('ProgressBarComponent', () => {
    let component: ProgressBarComponent;
    let fixture: ComponentFixture<ProgressBarComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [ ProgressBarComponent ]
        })
        .compileComponents();
    }));

    it('should be warning', () => {
        fixture = TestBed.createComponent(ProgressBarComponent);
        component = fixture.componentInstance;
        component.numberOfItems = 100;
        component.numberOfSelectedItems = 24;
        fixture.detectChanges();

        expect(component.percentage).toEqual(24);
        expect(component.isWarning()).toBeTruthy();
        expect(component.isInfo()).toBeFalsy();
        expect(component.isSuccess()).toBeFalsy();
    });

    it('should be lower bound of info', () => {
        fixture = TestBed.createComponent(ProgressBarComponent);
        component = fixture.componentInstance;
        component.numberOfItems = 100;
        component.numberOfSelectedItems = 25;
        fixture.detectChanges();

        expect(component.percentage).toEqual(25);
        expect(component.isWarning()).toBeFalsy();
        expect(component.isInfo()).toBeTruthy();
        expect(component.isSuccess()).toBeFalsy();
    });

    it('should be upper bound of info', () => {
        fixture = TestBed.createComponent(ProgressBarComponent);
        component = fixture.componentInstance;
        component.numberOfItems = 100;
        component.numberOfSelectedItems = 74;
        fixture.detectChanges();

        expect(component.percentage).toEqual(74);
        expect(component.isWarning()).toBeFalsy();
        expect(component.isInfo()).toBeTruthy();
        expect(component.isSuccess()).toBeFalsy();
    });

    it('should be success', () => {
        fixture = TestBed.createComponent(ProgressBarComponent);
        component = fixture.componentInstance;
        component.numberOfItems = 100;
        component.numberOfSelectedItems = 75;
        fixture.detectChanges();

        expect(component.percentage).toEqual(75);
        expect(component.isWarning()).toBeFalsy();
        expect(component.isInfo()).toBeFalsy();
        expect(component.isSuccess()).toBeTruthy();
    });

    it('should handle empty group', () => {
        fixture = TestBed.createComponent(ProgressBarComponent);
        component = fixture.componentInstance;
        component.numberOfItems = 0;
        component.numberOfSelectedItems = 0;
        fixture.detectChanges();

        expect(component.percentage).toEqual(0);
        expect(component.isWarning()).toBeTruthy();
        expect(component.isInfo()).toBeFalsy();
        expect(component.isSuccess()).toBeFalsy();
    });

});
