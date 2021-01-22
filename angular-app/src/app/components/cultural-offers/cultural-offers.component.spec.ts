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
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import {Overlay} from '@angular/cdk/overlay';

describe('CulturalOffersComponent', () => {
  let component: CulturalOffersComponent;
  let fixture: ComponentFixture<CulturalOffersComponent>;
  let offerService: any;
  let router: any;
  let httpClient: any;

  beforeEach(async () => {
    let offerServiceMock = {
      getByPage: jasmine.createSpy('getByPage')
        .and.returnValue(),
      getSubscribedItems: jasmine.createSpy('getSubscribedItems')
        .and.returnValue(of({body: [
              {
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
              }]}))
    }

    localStorage.setItem("accessToken", "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZGltaXJ2dWtvdmljOThAbWFpbGRyb3AuY2MiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE2MTA5NzA3ODcsImV4cCI6MTYxMDk3MjU4NywidXNlcl9maXJzdE5hbWUiOiJWbGFkaW1pciIsInVzZXJfbGFzdE5hbWUiOiJWdWtvdmljIiwidXNlcl9pZCI6IjEiLCJhdXRob3JpdHkiOlt7ImlkIjoxLCJuYW1lIjoiUk9MRV9VU0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.XDy0eWTBv-RDcC7mc7rBpxePMx0kZQr8kPyvBn6y2_72mQ2igaRYLjOgqjGozcD0VVfEfdauB6XjjKqxrq2joA");

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule, MatDialogModule],
      declarations: [ CommentItemComponent ],
      providers:    [ {provide: CulturalOfferService, useValue: offerServiceMock },
        { provide: Router, useValue: routerMock } , AuthService, HttpClient, MatDialog]
    });

    fixture = TestBed.createComponent(CulturalOffersComponent);
    component    = fixture.componentInstance;
    offerService = TestBed.inject(CulturalOfferService);
    router = TestBed.inject(Router);
    httpClient = TestBed.inject(HttpClient);

    fixture.detectChanges();
  });


  it('should create', () => {
    expect(component).toBeTruthy();

    component.ngOnInit();

    fixture.detectChanges();

    expect(component.getAllOffers()).toBeTruthy();
    expect(component.getAllOffers()).toHaveSize(8);
  });
});
