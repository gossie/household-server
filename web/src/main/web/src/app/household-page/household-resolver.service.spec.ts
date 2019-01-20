import { TestBed } from '@angular/core/testing';

import { HouseholdResolverService } from './household-resolver.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('HouseholdResolverService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ]
    }));

    it('should be created', () => {
        const service: HouseholdResolverService = TestBed.get(HouseholdResolverService);
        expect(service).toBeTruthy();
    });
});
