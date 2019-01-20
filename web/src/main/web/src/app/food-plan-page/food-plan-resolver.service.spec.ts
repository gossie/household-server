import { TestBed } from '@angular/core/testing';

import {HttpClientTestingModule} from "@angular/common/http/testing";
import {FoodPlanResolverService} from "./food-plan-resolver.service";

describe('FoodPlanResolverService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ]
    }));

    it('should be created', () => {
        const service: FoodPlanResolverService = TestBed.get(FoodPlanResolverService);
        expect(service).toBeTruthy();
    });
});
