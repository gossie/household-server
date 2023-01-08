import { TestBed } from '@angular/core/testing';
import { CookbookService } from './cookbook.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { UserService } from '../../user.service';
import { UserServiceMock } from '../../user.service.mock';

describe('CookbookService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ],
        providers: [
            { provide: UserService, useClass: UserServiceMock }
        ]
    }));

    it('should be created', () => {
        const service: CookbookService = TestBed.inject(CookbookService);
        expect(service).toBeTruthy();
    });
});
