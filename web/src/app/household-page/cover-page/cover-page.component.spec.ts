import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { CoverPageComponent } from './cover-page.component';
import { ReactiveFormsModule } from "@angular/forms";
import { InvitationService } from "../../common-elements/invitation/invitation.service";
import { InvitationServiceMock } from "../../common-elements/invitation/invitation.service.mock";
import { HouseholdService } from "../household.service";
import { HouseholdServiceMock } from "../household.service.mock";
import { InvitationComponent } from "../../common-elements/invitation/invitation.component";
import { UserService } from "../../user.service";
import { UserServiceMock } from "../../user.service.mock";
import { ChangePasswordComponent } from "./change-password/change-password.component";

describe('CoverPageComponent', () => {
    let component: CoverPageComponent;
    let fixture: ComponentFixture<CoverPageComponent>;

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
            declarations: [
                CoverPageComponent,
                ChangePasswordComponent
            ],
            providers: [
                { provide: InvitationService, useClass: InvitationServiceMock },
                { provide: HouseholdService, useClass: HouseholdServiceMock },
                { provide: UserService, useClass: UserServiceMock }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CoverPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
