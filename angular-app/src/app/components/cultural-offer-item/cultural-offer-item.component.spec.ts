import {ComponentFixture, fakeAsync, TestBed} from '@angular/core/testing';
import { CulturalOfferItemComponent } from './cultural-offer-item.component';
import {of} from 'rxjs';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {MatSlideToggle, MatSlideToggleChange, MatSlideToggleModule} from '@angular/material/slide-toggle';
import {By} from '@angular/platform-browser';
import {HarnessLoader} from '@angular/cdk/testing';
import {TestbedHarnessEnvironment} from '@angular/cdk/testing/testbed';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatSlideToggleHarness} from '@angular/material/slide-toggle/testing';

describe('CulturalOfferItemComponent', () => {
  let component: CulturalOfferItemComponent;
  let fixture: ComponentFixture<CulturalOfferItemComponent>;
  let authService: AuthService;
  let offerService: CulturalOfferService;
  let router: Router;
  let loader: HarnessLoader;


  beforeEach( () => {
    const offerServiceMock = {
      subscribeUser: jasmine.createSpy('subscribeUser')
        .and.returnValue(of({})),
      unsubscribeUser: jasmine.createSpy('unsubscribeUser')
        .and.returnValue(of({}))
    }

    const routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    const authMock = {
      isUser: jasmine.createSpy('isUser')
        .and.returnValue(true),
      isAdmin: jasmine.createSpy('isAdmin')
        .and.returnValue(true)
    };

    TestBed.configureTestingModule({
      imports: [
        MatSlideToggleModule,
        BrowserAnimationsModule
      ],
      declarations: [ CulturalOfferItemComponent ],
      providers:    [
          { provide: CulturalOfferService, useValue: offerServiceMock },
          { provide: Router, useValue: routerMock },
          { provide: AuthService, useValue: authMock }
        ]
    }).compileComponents();

    fixture = TestBed.createComponent(CulturalOfferItemComponent);
    component    = fixture.componentInstance;
    offerService = TestBed.inject(CulturalOfferService);
    router = TestBed.inject(Router);
    authService = TestBed.inject(AuthService);
    loader = TestbedHarnessEnvironment.loader(fixture);

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
      "description": "Lorem Ipsum is simply dummy text of the printing " +
        "and typesetting industry. Lorem Ipsum has been the industry's standard dummy " +
        "text ever since the 1500s, when an unknown printer took a galley of type and " +
        "scrambled it to make a type specimen book. It has survived not only five centuries," +
        " but also the leap into electronic typesetting, remaining essentially unchanged. " +
        "It was popularised in the 1960s with the release of Letraset sheets containing Lorem" +
        " Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker" +
        " including versions of Lorem Ipsum.",
      "avgGrade": 5,
      "subscribersCount": 5
    };

    spyOn(component.removeOffer, 'emit');
    spyOn(component.editOffer, 'emit');

    component.isSubscribed = false;

    fixture.detectChanges();

  });


  it('should bre created', () => {
    expect(component).toBeTruthy();
    expect(component.offer).toBeTruthy();
    expect(component.offer.id).toBe(26);
  });

  it('should emit delete offer event', () => {
    component.deleteOffer(26);

    expect(component.removeOffer.emit).toHaveBeenCalledWith(26);
  });

  it('should emit edit offer event', () => {
    component.edit(component.offer);

    expect(component.editOffer.emit).toHaveBeenCalledWith(component.offer);
  });

  it('should subscribe user to existing offer', async () => {

    const sliderHarness = await loader.getHarness(MatSlideToggleHarness);

    expect(sliderHarness).toBeTruthy();

    component.isSubscribed = false;

    await sliderHarness.check();

    const slider = fixture.debugElement.query(By.directive(MatSlideToggle)).nativeElement;

    expect(slider).toBeTruthy();

    component.subscribeToggle(new MatSlideToggleChange(slider, true), 26 );

    expect(offerService.subscribeUser).toHaveBeenCalledWith(26);
  });

  it('should unsubscribe user from existing offer', async () => {

    const sliderHarness = await loader.getHarness(MatSlideToggleHarness);

    expect(sliderHarness).toBeTruthy();

    component.isSubscribed = true;

    await sliderHarness.uncheck();

    const slider = fixture.debugElement.query(By.directive(MatSlideToggle)).nativeElement;

    expect(slider).toBeTruthy();

    component.subscribeToggle(new MatSlideToggleChange(slider, false), 26 );

    expect(offerService.unsubscribeUser).toHaveBeenCalledWith(26);
  });

  it('should determine if user is registered user', () => {

    const isUser: boolean = component.isUser();

    expect(isUser).toBeTrue();
  });

  it('should determine if user is admin', () => {
    const isUser: boolean = component.isAdmin();

    expect(isUser).toBeTrue();
  });

  it('should navigate to details page', () => {
    component.showDetails();
    expect(router.navigate).toHaveBeenCalledWith(
      ['/cultural-offer/offer-details/26']);
  });

});
