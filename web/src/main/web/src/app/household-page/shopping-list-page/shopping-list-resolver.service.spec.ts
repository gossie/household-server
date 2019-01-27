import { TestBed } from '@angular/core/testing';

import { ShoppingListResolverService } from './shopping-list-resolver.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('ShoppingListResolverService', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ]
    }));

    it('should be created', () => {
        const service: ShoppingListResolverService = TestBed.get(ShoppingListResolverService);
        expect(service).toBeTruthy();
    });
});
