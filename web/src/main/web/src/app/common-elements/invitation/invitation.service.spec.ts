import { TestBed } from '@angular/core/testing';
import { InvitationService } from './invitation.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { UserService } from "../../user.service";
import { UserServiceMock } from "../../user.service.mock";

describe('InvitationService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ],
        providers: [
            InvitationService,
            { provide: UserService, useClass: UserServiceMock }
        ]
    }));

    it('should be created', () => {
        const service: InvitationService = TestBed.get(InvitationService);
        expect(service).toBeTruthy();
    });
});
