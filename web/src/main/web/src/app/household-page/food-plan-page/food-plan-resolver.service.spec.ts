import { TestBed } from '@angular/core/testing';
import {FoodPlanResolverService} from "./food-plan-resolver.service";
import {FoodPlanService} from "./food-plan.service";
import {FoodPlanServiceMock} from "./food-plan.service.mock";

describe('FoodPlanResolverService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        providers: [
            { provide: FoodPlanService, useClass: FoodPlanServiceMock }
        ]
    }));

    it('should be created', () => {
        const service: FoodPlanResolverService = TestBed.get(FoodPlanResolverService);
        expect(service).toBeTruthy();
    });
});
