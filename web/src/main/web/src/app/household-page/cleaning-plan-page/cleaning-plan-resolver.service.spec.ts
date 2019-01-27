import { TestBed } from '@angular/core/testing';
import { CleaningPlanResolverService } from "./cleaning-plan-resolver.service";
import { CleaningPlanService } from "./cleaning-plan.service";
import { CleaningPlanServiceMock } from "./cleaning-plan.service.mock";

describe('CleaningPlanResolverService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        providers: [
            { provide: CleaningPlanService, useClass: CleaningPlanServiceMock }
        ]
    }));

    it('should be created', () => {
        const service: CleaningPlanResolverService = TestBed.get(CleaningPlanResolverService);
        expect(service).toBeTruthy();
    });
});
