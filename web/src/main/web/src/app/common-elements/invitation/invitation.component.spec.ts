import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { InvitationComponent } from './invitation.component';
import { InvitationService } from "./invitation.service";
import { InvitationServiceMock } from "./invitation.service.mock";
import { UserService } from "../../user.service";
import { UserServiceMock } from "../../user.service.mock";

describe('InvitationComponent', () => {
    let component: InvitationComponent;
    let fixture: ComponentFixture<InvitationComponent>;

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            imports: [],
            declarations: [ InvitationComponent ],
            providers: [
                { provide: InvitationService, useClass: InvitationServiceMock },
                { provide: UserService, useClass: UserServiceMock }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(InvitationComponent);
        component = fixture.componentInstance;
        component.invitation = {
            sender: 'sender@email.de',
            links: []
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
