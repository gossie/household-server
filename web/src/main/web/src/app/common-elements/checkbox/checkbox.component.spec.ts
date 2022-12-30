import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { CheckboxComponent } from './checkbox.component';
import { EventEmitter } from "@angular/core";
import { CheckboxValue } from "./checkbox-value";

describe('CheckboxComponent', () => {
    let component: CheckboxComponent;
    let fixture: ComponentFixture<CheckboxComponent>;

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            declarations: [ CheckboxComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CheckboxComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
        expect(component.selected).toBeFalsy();
    });

    it('should select checkbox', done => {
        const selectEmitter: EventEmitter<CheckboxValue> = new EventEmitter();
        selectEmitter.subscribe((value: CheckboxValue) => {
            expect(value).toEqual({
                selected: true,
                value: 'The label'
            });
            expect(component.selected).toBeTruthy();
            done();
        });

        component.onSelect = selectEmitter;
        component.label = 'The label';
        fixture.detectChanges();

        component.toggleCheckbox()
    });

    it('should deselect checkbox', done => {
        const selectEmitter: EventEmitter<CheckboxValue> = new EventEmitter();
        selectEmitter.subscribe((value: CheckboxValue) => {
            expect(value).toEqual({
                selected: false,
                value: 'The label'
            });
            expect(component.selected).toBeFalsy();
            done();
        });

        component.onSelect = selectEmitter;
        component.label = 'The label';
        component.selected = true;
        fixture.detectChanges();

        component.toggleCheckbox()
    });
});
