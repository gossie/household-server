import { TestBed } from '@angular/core/testing';
import { UserService } from './user.service';
import { User } from "./user";
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
        const service: UserService = TestBed.inject(UserService);
        expect(service).toBeTruthy();
    });

    it('should provide user', done => {
        const userService: UserService = TestBed.inject(UserService);

        const expectedUser: User = {
            email: 'user@email.de',
            invitations: [],
            links: []
        };

        userService.observeUser().subscribe((user: User) => {
            expect(user).toEqual(expectedUser);
            done();
        });

        userService.setUser(expectedUser);
    });
});
