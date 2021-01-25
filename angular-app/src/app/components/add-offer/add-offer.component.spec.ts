import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { TypeService } from '../../services/type.service';
import { CulturalOfferService } from '../../services/culturalOffer.service';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddOfferComponent } from './add-offer.component';
import { of } from 'rxjs';

describe('AddOfferComponent', () => {
  let component: AddOfferComponent;
  let fixture: ComponentFixture<AddOfferComponent>;

  beforeEach(() => {
    const categoryServiceStub = () => ({

      getCategories: jasmine.createSpy('getCategories')
        .and.returnValue(of({
          body:
            [
              {
                "id": 17,
                "name": "Institution",
                "types": [
                  {
                    "id": 9,
                    "name": "Parliament",
                    "categoryId": 17,
                    "categoryName": "Institution"
                  },
                  {
                    "id": 10,
                    "name": "Museum",
                    "categoryId": 17,
                    "categoryName": "Institution"
                  },
                ]
              },
              {
                "id": 18,
                "name": "Manifestacion",
                "types": []
              },
              {
                "id": 19,
                "name": "Landmark",
                "types": [
                  {
                    "id": 11,
                    "name": "Tower",
                    "categoryId": 19,
                    "categoryName": "Landmark"
                  },
                  {
                    "id": 12,
                    "name": "Stadium",
                    "categoryId": 19,
                    "categoryName": "Landmark"
                  }
                ]
              },
            ]
        }))
    });
    const typeServiceStub = () => ({
      getTypesForCategory: jasmine.createSpy('getTypesForCategory')
        .and.returnValue(of([
          {
            "id": 9,
            "name": "Parliament",
            "categoryId": 17,
            "categoryName": "Institution"
          },
          {
            "id": 10,
            "name": "Museum",
            "categoryId": 17,
            "categoryName": "Institution"
          },
          {
            "id": 47,
            "name": "asdasdsa",
            "categoryId": 17,
            "categoryName": "Institution"
          }
        ]
        ))
    });
    const culturalOfferServiceStub = () => ({
      getLocationName: jasmine.createSpy('getLocationName')
        .and.returnValue(of(
          {
            "place_id": "223185979",
            "licence": "https://locationiq.com/attribution",
            "osm_type": "way",
            "osm_id": "680095226",
            "lat": "51.4982995996088",
            "lon": "-0.11004656304756",
            "display_name": "Westminster Bridge Road, South Bank, Lambeth, London Borough of Lambeth, London, Greater London, England, SE1 7RU, United Kingdom",
            "address": {
              "road": "Westminster Bridge Road",
              "neighbourhood": "South Bank",
              "suburb": "Lambeth",
              "city": "London",
              "state_district": "Greater London",
              "state": "England",
              "postcode": "SE1 7RU",
              "country": "United Kingdom",
              "country_code": "gb"
            },
            "boundingbox": [
              "51.4982947",
              "51.4983029",
              "-0.1101011",
              "-0.1099656"
            ]
          }
        )),
      createOffer: jasmine.createSpy('createOffer')
        .and.returnValue(of(
          {
            "id": 21,
            "name": "asdadasas",
            "images": [
              {
                "id": 26,
                "picByte": "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"
              }
            ],
            "location": {
              "locationId": 21,
              "latitude": 51.50874245880335,
              "longitude": -0.10780947046350021,
              "place": "Upper Ground, London, United Kingdom"
            },
            "description": "asdasdsdsa",
            "avgGrade": 0,
            "subscribersCount": 0
          }
        ))
    });
    //const routerStub = () => ({ /*navigate: array => ({})*/ });
    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      imports: [FormsModule, ReactiveFormsModule],
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [AddOfferComponent],
      providers: [
        { provide: CategoryService, useFactory: categoryServiceStub },
        { provide: TypeService, useFactory: typeServiceStub },
        { provide: CulturalOfferService, useFactory: culturalOfferServiceStub },
        { provide: Router, useValue: routerMock }
      ]
    });
    fixture = TestBed.createComponent(AddOfferComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it(`categories has default value`, () => {
    expect(component.categories).toEqual([]);
  });

  it(`types has default value`, () => {
    expect(component.types).toEqual([]);
  });

  it(`files has default value`, () => {
    expect(component.files).toEqual([]);
  });

  describe('getCategories', () => {
    it('makes expected calls', () => {
      const categoryServiceStub: CategoryService = fixture.debugElement.injector.get(
        CategoryService
      );
      component.getCategories();
      expect(categoryServiceStub.getCategories).toHaveBeenCalled();
    });
  });

  describe('submit', () => {
    it('makes expected calls', () => {
      const culturalOfferServiceStub: CulturalOfferService = fixture.debugElement.injector.get(
        CulturalOfferService
      );
      //const routerStub: Router = fixture.debugElement.injector.get(Router);
      //spyOn(culturalOfferServiceStub, 'createOffer').and.callThrough();
      //spyOn(routerStub, 'navigate').and.callThrough();
      component.myForm.value.name = "dasdadada";
      component.myForm.value.description = "adasdasd";
      component.myForm.value.category = "17";
      component.myForm.value.type = "9";
      component.myForm.value.place = "Pardoner Street, London, United Kingdom";
      component.myForm.value.location = {
        "lat": 51.4978433510224,
        "lng": -0.08720300292542006
      };
      component.myForm.value.file = "C:\\fakepath\\index.jpg";
      component.myForm.value.fileSource =  [
        "data:image/jpeg;base64,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"
      ];

      component.submit();

      expect(culturalOfferServiceStub.createOffer).toHaveBeenCalled();
      //expect(routerStub.navigate).toHaveBeenCalled();
    });
  });
});
