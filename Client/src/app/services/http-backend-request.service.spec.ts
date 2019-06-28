import { TestBed } from '@angular/core/testing';

import { HttpBackendRequestService } from './http-backend-request.service';

describe('HttpBackendRequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HttpBackendRequestService = TestBed.get(HttpBackendRequestService);
    expect(service).toBeTruthy();
  });
});
