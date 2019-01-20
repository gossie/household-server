import { TestBed } from '@angular/core/testing';

import {HttpClientTestingModule} from "@angular/common/http/testing";
import {CleaningPlanResolverService} from "./cleaning-plan-resolver.service";

describe('CleaningPlanResolverService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ]
    }));

    it('should be created', () => {
        const service: CleaningPlanResolverService = TestBed.get(CleaningPlanResolverService);
        expect(service).toBeTruthy();
    });
});
