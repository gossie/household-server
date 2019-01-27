import { TestBed } from '@angular/core/testing';

import {HttpClientTestingModule} from "@angular/common/http/testing";
import {CookbookResolverService} from "./cookbook-resolver.service";

describe('CookbookResolverService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ]
    }));

    it('should be created', () => {
        const service: CookbookResolverService = TestBed.get(CookbookResolverService);
        expect(service).toBeTruthy();
    });
});
