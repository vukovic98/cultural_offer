import {ComponentFixture, fakeAsync, TestBed} from '@angular/core/testing';

import { CulturalOfferItemComponent } from './cultural-offer-item.component';
import {of} from 'rxjs';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {CommentItemComponent} from '../comment-item/comment-item.component';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {HttpClient} from '@angular/common/http';
import {Location} from '../../model/offer-mode';
import {MatSlideToggle, MatSlideToggleChange, MatSlideToggleModule} from '@angular/material/slide-toggle';
import {By} from '@angular/platform-browser';
import {by} from 'protractor';

describe('CulturalOfferItemComponent', () => {
  let component: CulturalOfferItemComponent;
  let fixture: ComponentFixture<CulturalOfferItemComponent>;
  let offerService: any;
  let router: any;
  let httpClient: any;

  beforeEach(async () => {
    let offerServiceMock = {
      subscribeUser: jasmine.createSpy('subscribeUser')
        .and.returnValue(of({body: {statusCode: 200}}))
    }

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule, MatSlideToggleModule],
      declarations: [ CommentItemComponent ],
      providers:    [ {provide: CulturalOfferService, useValue: offerServiceMock },
        { provide: Router, useValue: routerMock } , AuthService, HttpClient, MatSlideToggle]
    });

    fixture = TestBed.createComponent(CulturalOfferItemComponent);
    component    = fixture.componentInstance;
    offerService = TestBed.inject(CulturalOfferService);
    router = TestBed.inject(Router);
    httpClient = TestBed.inject(HttpClient);

    component.offer = {
      "id": 26,
      "name": "Sonsing",
      "images": [],
      "location": {
        "locationId": 2,
        "latitude": 33.33,
        "longitude": 33.33,
        "place": "Beograd"
      },
      "description": "This is offer",
      "avgGrade": 5,
      "subscribersCount": 5
    };

    component.isSubscribed = false;

    fixture.detectChanges();

  });


  it('should create', () => {
    expect(component).toBeTruthy();
    expect(component.offer).toBeTruthy();
    expect(component.offer.id).toBe(26);
  });

  it('should subscribe user to existing offer', fakeAsync(() => {
    localStorage.setItem("accessToken", "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZGltaXJ2dWtvdmljOThAbWFpbGRyb3AuY2MiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE2MTA5NzA3ODcsImV4cCI6MTYxMDk3MjU4NywidXNlcl9maXJzdE5hbWUiOiJWbGFkaW1pciIsInVzZXJfbGFzdE5hbWUiOiJWdWtvdmljIiwidXNlcl9pZCI6IjEiLCJhdXRob3JpdHkiOlt7ImlkIjoxLCJuYW1lIjoiUk9MRV9VU0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.XDy0eWTBv-RDcC7mc7rBpxePMx0kZQr8kPyvBn6y2_72mQ2igaRYLjOgqjGozcD0VVfEfdauB6XjjKqxrq2joA");

    fixture.detectChanges();

    expect(component.isUser()).toBeTrue();

    const slider = fixture.debugElement.query(By.css('#toggleId')).nativeElement;

    fixture.detectChanges();
    console.log(slider);

    expect(slider).toBeTruthy();
    //component.subscribeToggle()
  }));

  it('should determine if user is registered user', () => {
    localStorage.setItem("accessToken", "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZGltaXJ2dWtvdmljOThAbWFpbGRyb3AuY2MiLCJhdWQiOiJ3ZWIiLCJpYXQiOjE2MTA5NzA3ODcsImV4cCI6MTYxMDk3MjU4NywidXNlcl9maXJzdE5hbWUiOiJWbGFkaW1pciIsInVzZXJfbGFzdE5hbWUiOiJWdWtvdmljIiwidXNlcl9pZCI6IjEiLCJhdXRob3JpdHkiOlt7ImlkIjoxLCJuYW1lIjoiUk9MRV9VU0VSIiwiYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dfQ.XDy0eWTBv-RDcC7mc7rBpxePMx0kZQr8kPyvBn6y2_72mQ2igaRYLjOgqjGozcD0VVfEfdauB6XjjKqxrq2joA");

    let isUser: boolean = component.isUser();

    expect(isUser).toBeTrue();
  });

  it('should determine if user is registered user (fail)', () => {
    localStorage.removeItem("accessToken");

    let isUser: boolean = component.isUser();

    expect(isUser).toBeFalse();
  });

  it('should determine if user is admin', () => {
    localStorage.setItem("accessToken", "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJLdWx0dXJhIG5hIERsYW51Iiwic3ViIjoidmxhZG9AZ21haWwuY29tIiwiYXVkIjoid2ViIiwiaWF0IjoxNjEwOTcxMzI4LCJleHAiOjE2MTA5NzMxMjgsInVzZXJfZmlyc3ROYW1lIjoiVmxhZGltaXIiLCJ1c2VyX2xhc3ROYW1lIjoiVnVrb3ZpYyIsInVzZXJfaWQiOiIyIiwiYXV0aG9yaXR5IjpbeyJpZCI6MiwibmFtZSI6IlJPTEVfQURNSU4iLCJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dfQ.NP-mNaoVyNoKCYTDt6yIWbyPN9v3W1y2aKw4HRSu_84tR2lTyuQZWALRnMyHs4vefT7V6CBUvbcrcDiZ5Wdsew");

    let isUser: boolean = component.isAdmin();

    expect(isUser).toBeTrue();
  });

  it('should determine if user is admin (fail)', () => {
    localStorage.removeItem("accessToken");

    let isUser: boolean = component.isAdmin();

    expect(isUser).toBeFalse();
  });

  it('should navigate to details page', () => {
    component.showDetails();
    expect(router.navigate).toHaveBeenCalledWith(
      ['/cultural-offer/offer-details/26']);
  });

});
