import {ComponentFixture, getTestBed, TestBed} from '@angular/core/testing';

import { FilterOffersComponent } from './filter-offers.component';
import {FilterOffersService} from "../../services/filter-offers.service";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {HttpClient} from "@angular/common/http";

describe('FilterOffersComponent', () => {
  let component: FilterOffersComponent;
  let service: FilterOffersService;
  let fixture: ComponentFixture<FilterOffersComponent>;
  let injector;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ FilterOffersComponent ],
      providers: [FilterOffersService]
    })
    .compileComponents();
    injector = getTestBed();
    service = TestBed.inject(FilterOffersService);
    httpClient = TestBed.inject(HttpClient);
    httpMock = TestBed.inject(HttpTestingController);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FilterOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
