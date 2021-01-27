import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribedItemsComponent } from './subscribed-items.component';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {of} from 'rxjs';

describe('SubscribedItemsComponent', () => {
  let component: SubscribedItemsComponent;
  let fixture: ComponentFixture<SubscribedItemsComponent>;
  let offerService: CulturalOfferService;

  let offerServiceMock = {
    getSubscribedItems: jasmine.createSpy('getSubscribedItems')
      .and.returnValue(of([
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
        },
        {
          "id": 5,
          "name": "Andalax",
          "images": [],
          "location": {
            "locationId": 147,
            "latitude": 45.25,
            "longitude": 19.8667,
            "place": "Petrovaradin"
          },
          "description": "pretium nisl ut volutpat sapien arcu sed augue aliquam erat volutpat in congue etiam",
          "avgGrade": 4.0,
          "subscribersCount": 1.0
        }
      ])),
    unsubscribeUser: jasmine.createSpy('unsubscribeUser')
      .and.returnValue(of({}))
  };

  beforeEach( () => {
    TestBed.configureTestingModule({
      imports: [
        BrowserAnimationsModule
      ],
      declarations: [ SubscribedItemsComponent ],
      providers: [
        { provide: CulturalOfferService, useValue: offerServiceMock}
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubscribedItemsComponent);
    component = fixture.componentInstance;
    offerService = TestBed.inject(CulturalOfferService);

    fixture.detectChanges();
  });

  it('should create and call initial service methods', () => {
    expect(component).toBeTruthy();
    expect(offerService.getSubscribedItems).toHaveBeenCalled();
  });

  it('should remove offer from list of users subscribed offers', () => {
    component.removeOffer(26);

    expect(offerService.unsubscribeUser).toHaveBeenCalled();
    expect(offerService.unsubscribeUser).toHaveBeenCalledWith(26);
  });

});

