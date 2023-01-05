import { TestBed } from '@angular/core/testing';

import { TokenService } from './token.service';

describe('TokenService', () => {

    describe('already logged in', () => {

        let service: TokenService;

        beforeEach(() => {
            localStorage.setItem('jwt', 'token');
            TestBed.configureTestingModule({});
            service = TestBed.inject(TokenService);
        });

        afterEach(() => {
            localStorage.removeItem('jwt');
        });

        it('should return token', (done) => {
            service.observeToken().subscribe(token => {
                expect(token).toEqual('token');
                done();
            })
        })

    })
    
});
