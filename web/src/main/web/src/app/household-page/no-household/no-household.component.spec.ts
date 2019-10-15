import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NoHouseholdComponent } from './no-household.component';
import { HouseholdService } from "../household.service";
import { HouseholdServiceMock } from "../household.service.mock";
import { UserService } from "../../user.service";
import { UserServiceMock } from "../../user.service.mock";
import { Component, Input } from "@angular/core";
import { Invitation } from "../../invitation";
import {RouterTestingModule} from "@angular/router/testing";

@Component({
    selector: [
        'app-invitation'
    ].join(','),
    template: '',
})
class MockComponent{
    @Input()
    public invitation: Invitation;
}

describe('NoHouseholdComponent', () => {
    let component: NoHouseholdComponent;
    let fixture: ComponentFixture<NoHouseholdComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule
            ],
            declarations: [
                MockComponent,
                NoHouseholdComponent
            ],
            providers: [
                { provide: UserService, useClass: UserServiceMock },
                { provide: HouseholdService, useClass: HouseholdServiceMock }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(NoHouseholdComponent);
        component = fixture.componentInstance;
        component.user = {
            email: 'user@email.de',
            invitations: []
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
