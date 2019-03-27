import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CoverPageComponent } from './cover-page.component';
import { ReactiveFormsModule } from "@angular/forms";
import { InvitationService } from "./invitation.service";
import { InvitationServiceMock } from "./invitation.service.mock";
import { HouseholdService } from "../household.service";
import { HouseholdServiceMock } from "../household.service.mock";
import { InvitationComponent } from "../invitation/invitation.component";
import { UserService } from "../../user.service";
import { UserServiceMock } from "../../user.service.mock";
import { ChangePasswordComponent } from "./change-password/change-password.component";

describe('CoverPageComponent', () => {
    let component: CoverPageComponent;
    let fixture: ComponentFixture<CoverPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
            declarations: [
                CoverPageComponent,
                InvitationComponent,
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
