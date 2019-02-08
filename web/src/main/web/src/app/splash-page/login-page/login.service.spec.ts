import { TestBed } from '@angular/core/testing';
import { LoginService } from './login.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { User } from './user';

describe('LoginService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ],
        providers: [
            LoginService
        ]
    }));

    it('should be created', () => {
        const service: LoginService = TestBed.get(LoginService);
        expect(service).toBeTruthy();
    });

    it('should login', () => {
        const service: LoginService = TestBed.get(LoginService);
        const httpTestingController: HttpTestingController = TestBed.get(HttpTestingController);

        const expectedUser: User = {
            email: 'user@email.de',
            invitations: [],
            links: []
        };

        service.login('user', 'xxx').subscribe((user: User) => {
            expect(user).toEqual(expectedUser);
        });
        const request = httpTestingController.expectOne('https://ldwas-household-stage.herokuapp.com/api/users/login');
        request.flush(expectedUser);

        httpTestingController.verify();
    });
});
