import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseholdPageComponent } from './household-page.component';
import { RouterTestingModule } from "@angular/router/testing";
import { HouseholdService } from "./household.service";
import { HouseholdServiceMock } from "./household.service.mock";

describe('HouseholdPageComponent', () => {
    let component: HouseholdPageComponent;
    let fixture: ComponentFixture<HouseholdPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule
            ],
            declarations: [ HouseholdPageComponent ],
            providers: [
                { provide: HouseholdService, useClass: HouseholdServiceMock }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(HouseholdPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
