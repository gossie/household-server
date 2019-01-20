import { TestBed } from '@angular/core/testing';
import { AuthGuardService } from './auth-guard.service';
import { UserService } from './user.service';
import {RouterTestingModule} from '@angular/router/testing';

describe('AuthGuardService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            RouterTestingModule
        ],
        providers: [
            UserService,
            AuthGuardService
        ]
    }));

    it('should be created', () => {
        const service: AuthGuardService = TestBed.get(AuthGuardService);
        expect(service).toBeTruthy();
    });

    it('should not allow access', () => {
        const householdGuardService: AuthGuardService = TestBed.get(AuthGuardService);
        expect(householdGuardService.canActivate(null, null)).toBeFalsy();
    });

    it('should allow access', () => {
        const userService: UserService = TestBed.get(UserService);
        userService.setUserData({
            user: {
                email: '',
                invitations: [],
                links: []
            },
            authData: ''
        });

        const householdGuardService: AuthGuardService = TestBed.get(AuthGuardService);
        expect(householdGuardService.canActivate(null, null)).toBeTruthy();
    });
});
