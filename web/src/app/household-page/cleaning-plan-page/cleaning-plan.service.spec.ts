import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { CleaningPlanService } from './cleaning-plan.service';

describe('CleaningPlanService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ]
    }));

    it('should be created', () => {
        const service: CleaningPlanService = TestBed.inject(CleaningPlanService);
        expect(service).toBeTruthy();
    });
});
