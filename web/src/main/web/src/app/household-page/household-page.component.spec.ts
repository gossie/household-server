import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HouseholdPageComponent } from './household-page.component';
import { RouterTestingModule } from "@angular/router/testing";
import { HouseholdService } from "./household.service";
import { HouseholdServiceMock } from "./household.service.mock";
import { UserService } from "../user.service";
import { UserServiceMock } from "../user.service.mock";
import { Component, Input } from "@angular/core";
import { User } from "../splash-page/login-page/user";

@Component({
    selector: [
        'app-no-household'
    ].join(','),
    template: '',
})
class MockComponent{
    @Input()
    public user: User;
}

describe('HouseholdPageComponent', () => {
    let component: HouseholdPageComponent;
    let fixture: ComponentFixture<HouseholdPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule
            ],
            declarations: [
                MockComponent,
                HouseholdPageComponent
            ],
            providers: [
                { provide: HouseholdService, useClass: HouseholdServiceMock },
                { provide: UserService, useClass: UserServiceMock }
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
