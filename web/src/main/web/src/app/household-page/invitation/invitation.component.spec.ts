import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { InvitationComponent } from './invitation.component';
import { InvitationService } from "../../cover-page/invitation.service";
import { InvitationServiceMock } from "../../cover-page/invitation.service.mock";
import { UserService } from "../../user.service";
import { UserServiceMock } from "../../user.service.mock";

describe('InvitationComponent', () => {
    let component: InvitationComponent;
    let fixture: ComponentFixture<InvitationComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
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
