import { TestBed } from '@angular/core/testing';
import { RegistrationService } from './registration.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('RegistrationService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ]
    }));

    it('should be created', () => {
        const service: RegistrationService = TestBed.get(RegistrationService);
        expect(service).toBeTruthy();
    });
});
