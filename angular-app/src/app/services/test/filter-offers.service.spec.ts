import {fakeAsync, getTestBed, TestBed, tick} from '@angular/core/testing';

import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {HttpClient} from "@angular/common/http";
import {RouterTestingModule} from "@angular/router/testing";
import {FilterOffersService} from "../filter-offers.service";
import {statusCodeModel} from "../../model/auth-model";

describe('FilterOfferService', () => {
  let service: FilterOffersService;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;
  let injector;

  beforeEach(() => {

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [FilterOffersService]
    });

    injector = getTestBed();
    service = TestBed.inject(FilterOffersService);
    httpClient = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should pass simple test', () => {
    expect(true).toBe(true);
  });
  it('getTypes() should return some cultural offer types',fakeAsync(() => {
    let offerTypes : String[] = [];

    const mockTypes: String[] = ["Type1","Type2","Type3"];

    service.getTypes().subscribe(res => offerTypes = res);

    const req = httpMock.expectOne('https://localhost:8080/cultural-offer-types/getAll');
    expect(req.request.method).toBe('GET');
    req.flush(mockTypes);

    tick();

    expect(offerTypes.length).toEqual(3);
    expect(offerTypes[0]).toEqual("Type1");
    expect(offerTypes[1]).toEqual("Type2");
    expect(offerTypes[2]).toEqual("Type3");
  }));
  it('getTypes() should fail to return some cultural offer types',fakeAsync(() => {
    let code: statusCodeModel = {
      statusCode: 0
    };

    const mockCode : statusCodeModel = {
      statusCode: 404
    }

    service.getTypes().subscribe(res => code = res);

    const req = httpMock.expectOne('https://localhost:8080/cultural-offer-types/getAll');
    expect(req.request.method).toBe('GET');
    req.flush(mockCode);

    tick();

    expect(code.statusCode).toEqual(404);

  }));

});
