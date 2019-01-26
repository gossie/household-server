import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HouseholdPageComponent } from './household-page.component';
import { RouterTestingModule } from "@angular/router/testing";
import { HouseholdService } from "./household.service";
import { HouseholdServiceMock } from "./household.service.mock";
import { UserService } from "../user.service";
import { UserServiceMock } from "../user.service.mock";
import { InvitationComponent } from "./invitation/invitation.component";

describe('HouseholdPageComponent', () => {
    let component: HouseholdPageComponent;
    let fixture: ComponentFixture<HouseholdPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule
            ],
            declarations: [
                HouseholdPageComponent,
                InvitationComponent
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
