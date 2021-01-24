import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CulturalOffersComponent } from './cultural-offers.component';
import {Observable, of} from 'rxjs';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {MatSlideToggle, MatSlideToggleModule} from '@angular/material/slide-toggle';
import {CommentItemComponent} from '../comment-item/comment-item.component';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {HttpClient} from '@angular/common/http';
import {CulturalOfferItemComponent} from '../cultural-offer-item/cultural-offer-item.component';
import {MatDialog, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {Overlay} from '@angular/cdk/overlay';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Role} from '../../model/token.model';
import {EditOfferComponent} from '../edit-offer/edit-offer.component';
import { MapService } from 'src/app/services/map.service';
import { HttpResponse } from '@angular/common/http';
import {AddPostComponent} from '../add-post/add-post.component';
describe('CulturalOffersComponent', () => {
  let component: CulturalOffersComponent;
  let fixture: ComponentFixture<CulturalOffersComponent>;
  let offerService: CulturalOfferService;
  let mapService: MapService;
  let router: Router;
  let authService: AuthService;
  let matDialog: MatDialog;
  let dialogRef: MatDialogRef<EditOfferComponent>;

  beforeEach( () => {


    let mapServiceMock = {
      myMethod: jasmine.createSpy('myMethod')
    }

    let offerServiceMock = {
      getByPage: jasmine.createSpy('getByPage')
        .and.returnValue(of({
          "content": [
            {
              "id": 1,
              "name": "Sonsing",
              "images": [],
              "location": {
                "locationId": 126,
                "latitude": 44.4167,
                "longitude": 21.9333,
                "place": "Majdanpek"
              },
              "description": "tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh",
              "avgGrade": 5.0,
              "subscribersCount": 4.0
            },
            {
              "id": 2,
              "name": "Y-Solowarm",
              "images": [],
              "location": {
                "locationId": 49,
                "latitude": 44.3656,
                "longitude": 19.3619,
                "place": "Krupanj"
              },
              "description": "dictumst maecenas ut massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur",
              "avgGrade": 0.0,
              "subscribersCount": 1.0
            }
          ],
          "totalElements": 1021,
          "last": false,
          "totalPages": 128,
          "size": 2,
          "number": 0,
          "numberOfElements": 2,
          "first": true,
          "empty": false,
          "pageNumber": 0,
          "pageSize": 2
        })),
      getSubscribedItems: jasmine.createSpy('getSubscribedItems')
        .and.returnValue(of([{
            "id": 3,
            "name": "Exit",
            "images": [],
            "location": {
              "locationId": 3,
              "latitude": 43.02593743203676,
              "longitude": 21.74925614031963,
              "place": "Bojnik"
            },
            "description": "magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum sagittis sapien cum sociis natoque penatibus et magnis",
            "avgGrade": 0.0,
            "subscribersCount": 1.0
          }])),
      deleteOffer: jasmine.createSpy('deleteOffer')
        .and.returnValue(of({})),
      updateOffer: jasmine.createSpy('updateOffer')
        .and.returnValue(of({})),
      getByPageFilter: jasmine.createSpy('getByPageFilter')
        .and.returnValue(of({
          "content": [
            {
              "id": 1,
              "name": "Sonsing",
              "images": [],
              "location": {
                "locationId": 126,
                "latitude": 44.4167,
                "longitude": 21.9333,
                "place": "Majdanpek"
              },
              "description": "tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh",
              "avgGrade": 5.0,
              "subscribersCount": 4.0
            },
            {
              "id": 2,
              "name": "Y-Solowarm",
              "images": [],
              "location": {
                "locationId": 49,
                "latitude": 44.3656,
                "longitude": 19.3619,
                "place": "Krupanj"
              },
              "description": "dictumst maecenas ut massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur",
              "avgGrade": 0.0,
              "subscribersCount": 1.0
            }
          ],
          "totalElements": 1021,
          "last": false,
          "totalPages": 128,
          "size": 2,
          "number": 0,
          "numberOfElements": 2,
          "first": true,
          "empty": false,
          "pageNumber": 0,
          "pageSize": 2
        }))
    };

    let authServiceMock = {
      isUser: jasmine.createSpy('isUser')
        .and.returnValue(true),
      isLoggedIn: jasmine.createSpy('isLoggedIn')
        .and.returnValue(true),
      getToken: jasmine.createSpy('getToken')
        .and.returnValue("TOKEN"),
      decodeToken: jasmine.createSpy('decodeToken')
        .and.returnValue({
          "iss": "1",
          "sub": "2",
          "aud": "3",
          "iat": "4",
          "exp": "5",
          "user_firstName": "Pera",
          "user_lastName": "Peric",
          "user_id": "1",
          "authority": []
        })
    };

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    let matDialogMock = {
      open: jasmine.createSpy('open')
        .and.returnValue(dialogRef)
    };

    let dialogRefMock = {
      afterClosed: jasmine.createSpy('afterClosed')
    }

    TestBed.configureTestingModule({
      imports: [
        MatDialogModule,
        BrowserAnimationsModule
      ],
      declarations: [ CulturalOffersComponent ],
      providers:    [
        { provide: CulturalOfferService, useValue: offerServiceMock },
        { provide: Router, useValue: routerMock } ,
        { provide: MatDialog, useValue: matDialogMock },
        { provide: MatDialogRef, useValue: dialogRefMock },
        { provide: AuthService, useValue: authServiceMock},
        { provide: MapService, useValue: mapServiceMock}]
    }).compileComponents();

    fixture = TestBed.createComponent(CulturalOffersComponent);
    component    = fixture.componentInstance;
    offerService = TestBed.inject(CulturalOfferService);
    authService = TestBed.inject(AuthService);
    mapService = TestBed.inject(MapService);
    router = TestBed.inject(Router);
    matDialog = TestBed.inject(MatDialog);
    dialogRef = TestBed.inject(MatDialogRef);

    component.dialog = matDialog;
    component.isFilter = false;

    spyOn(component, 'retrieveOffers');


    fixture.detectChanges();
  });


  it('should be created', () => {
    expect(component).toBeTruthy();

    expect(offerService.getByPage).toHaveBeenCalled();
    expect(authService.isUser).toHaveBeenCalled();
    expect(authService.isLoggedIn).toHaveBeenCalled();
  });

  it('should remove offer', () => {
    component.removeOffer(26);

    expect(offerService.deleteOffer).toHaveBeenCalled();
    expect(offerService.deleteOffer).toHaveBeenCalledWith(26);
  })

  it('should return offers', () => {
    let of = component.getAllOffers();

    expect(of.length).toBe(2);
  });

  it('should retrieve offers for first page', () => {
    component.firstPage();

    expect(component.pageNum).toBe(1);
    expect(component.retrieveOffers).toHaveBeenCalled();
  });

  it('should retrieve offers for last page', () => {
    component.lastPage();

    expect(component.pageNum).toBe(component.totalPages);
    expect(component.retrieveOffers).toHaveBeenCalled();
  });

  it('should retrieve offers for next page', () => {
    let oldPage = component.pageNum;

    component.nextPage();

    expect(component.pageNum).toBe(oldPage+1);
    expect(component.retrieveOffers).toHaveBeenCalled();
  });

  it('should retrieve offers for previous page', () => {
    component.pageNum = 10;

    let oldPage = component.pageNum;

    component.previousPage();

    expect(component.pageNum).toBe(oldPage - 1);
    expect(component.retrieveOffers).toHaveBeenCalled();
  });

  it('should retrieve offers', () => {
    component.isFilter = false;
    component.pageNum = 1;

    component.retrieveOffers();

    expect(offerService.getByPage).toHaveBeenCalled();
    expect(offerService.getByPage).toHaveBeenCalledWith(1);
  });

  it('should filter offers and retrieve first page', () => {
    component.isFilter = true;
    component.pageNum = 1;
    component.filterObj.exp = 'exit';
    component.filterObj.types = ['t1', 't2'];

    component.filterOffers(component.filterObj);

    expect(offerService.getByPageFilter).toHaveBeenCalled();
    expect(offerService.getByPageFilter).toHaveBeenCalledWith(1, 'exit', ['t1', 't2']);
  });

  it('should determine if user is subscribed to offer', () => {
    let is: boolean = component.isSubscribed({
      "id": 1,
      "name": "Sonsing",
      "images": [],
      "location": {
        "locationId": 126,
        "latitude": 44.4167,
        "longitude": 21.9333,
        "place": "Majdanpek"
      },
      "description": "tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh",
      "avgGrade": 5.0,
      "subscribersCount": 4.0
    });

    expect(authService.isLoggedIn).toHaveBeenCalled();
    expect(is).toBeFalse();
  });

});
