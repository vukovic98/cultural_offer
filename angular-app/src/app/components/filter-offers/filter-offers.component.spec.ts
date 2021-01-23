import {ComponentFixture, fakeAsync, getTestBed, TestBed} from '@angular/core/testing';

import { FilterOffersComponent } from './filter-offers.component';
import {FilterOffersService} from "../../services/filter-offers.service";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {CulturalOfferItemComponent} from "../cultural-offer-item/cultural-offer-item.component";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {CulturalOfferService} from "../../services/culturalOffer.service";
import {AuthService} from "../../services/auth.service";
import {of} from "rxjs";
import {NO_ERRORS_SCHEMA} from "@angular/core";
import {FilterObject} from "../../model/filter-model";

describe('FilterOffersComponent', () => {
  let component: FilterOffersComponent;
  let service: FilterOffersService;
  let fixture: ComponentFixture<FilterOffersComponent>;
  let router: Router;


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ FilterOffersComponent ],
      providers: [FilterOffersService]
    })
    .compileComponents();
  });

  beforeEach(() => {
    let filterServiceMock = {
      getTypes: jasmine.createSpy('getTypes').and
        .returnValue(of(
          ["Type1", "Type2", "Type3"]
        ))
    };

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [ FilterOffersComponent ],
      providers:    [
        { provide: FilterOffersService, useValue: filterServiceMock },
        { provide: Router, useValue: routerMock },
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(FilterOffersComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(FilterOffersService);
    router = TestBed.inject(Router);
    spyOn(component.filterEvent, 'emit');

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('sholud get types', fakeAsync(() => {
    component.getTypes();
    expect(service.getTypes).toHaveBeenCalled();
    expect(component.types.length).toEqual(3);
  }));
  it('sholud select one type', fakeAsync(() => {
    component.addType("Type1");
    expect(component.selectedTypes.length).toEqual(1);
    expect(component.selectedTypes.pop()).toEqual("Type1");


  }));
  it('sholud deselect one type', fakeAsync(() => {
    component.addType("Type1");
    component.addType("Type2");
    component.removeType("Type1");
    expect(component.selectedTypes.length).toEqual(1);
    expect(component.selectedTypes.pop()).toEqual("Type2");

  }));

  it('sholud emit filterObject to parent component', fakeAsync(() => {

    const filterParams = {
      exp: "Novi Sad",
      types: ["Type1", "Type2"]
    };
    component.expression = "Novi Sad";
    component.addType("Type1");
    component.addType("Type2");
    component.onApplyFilter();
    expect(component.filterEvent.emit).toHaveBeenCalledWith(filterParams);

  }));
});
