import { TestBed } from '@angular/core/testing';
import { UserService } from './user.service';
import { UserData } from './user-data';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('UserService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ],
        providers: [
            UserService
        ]
    }));

    it('should be created', () => {
        const service: UserService = TestBed.get(UserService);
        expect(service).toBeTruthy();
    });

    it('should provide user', done => {
        const userService: UserService = TestBed.get(UserService);

        const expectedUserData: UserData = {
            user: {
                email: 'user@email.de',
                invitations: [],
                links: []
            },
            authData: 'authData'
        };

        userService.observeUserData().subscribe((userData: UserData) => {
            expect(userData).toEqual(expectedUserData);
            done();
        });

        userService.setUserData(expectedUserData);
    });
});
