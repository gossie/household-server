import { TestBed } from '@angular/core/testing';

import { DeleteHintService } from './delete-hint.service';

describe('DeleteHintService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeleteHintService = TestBed.get(DeleteHintService);
    expect(service).toBeTruthy();
  });
});
