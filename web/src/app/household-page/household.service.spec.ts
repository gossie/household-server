import { TestBed } from '@angular/core/testing';
import { HouseholdService } from './household.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('HouseholdService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ]
    }));

    it('should be created', () => {
        const service: HouseholdService = TestBed.inject(HouseholdService);
        expect(service).toBeTruthy();
    });
});
