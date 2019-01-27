import { TestBed } from '@angular/core/testing';
import { FoodPlanService } from './food-plan.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('FoodPlanService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ]
    }));

    it('should be created', () => {
        const service: FoodPlanService = TestBed.get(FoodPlanService);
        expect(service).toBeTruthy();
    });
});
