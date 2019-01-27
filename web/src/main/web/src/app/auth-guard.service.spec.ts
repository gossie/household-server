import { TestBed } from '@angular/core/testing';
import { AuthGuardService } from './auth-guard.service';
import { UserService } from './user.service';
import { RouterTestingModule } from '@angular/router/testing';
import { UserServiceMock } from "./user.service.mock";

describe('AuthGuardService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            RouterTestingModule
        ],
        providers: [
            { provide: UserService, useClass: UserServiceMock },
            AuthGuardService
        ]
    }));

    it('should be created', () => {
        const service: AuthGuardService = TestBed.get(AuthGuardService);
        expect(service).toBeTruthy();
    });

    it('should not allow access', () => {
        const householdGuardService: AuthGuardService = TestBed.get(AuthGuardService);
        const userService: UserService = TestBed.get(UserService);
        userService.setUserData(null);

        expect(householdGuardService.canActivate(null, null)).toBeFalsy();
    });

    it('should allow access', () => {
        const householdGuardService: AuthGuardService = TestBed.get(AuthGuardService);
        expect(householdGuardService.canActivate(null, null)).toBeTruthy();
    });
});
