import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribedItemsListComponent } from './subscribed-items-list.component';

describe('SubscribedItemsListComponent', () => {
  let component: SubscribedItemsListComponent;
  let fixture: ComponentFixture<SubscribedItemsListComponent>;

  beforeEach( () => {
    TestBed.configureTestingModule({
      declarations: [ SubscribedItemsListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubscribedItemsListComponent);
    component = fixture.componentInstance;

    component.offers = [ {
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
      } ];

    fixture.detectChanges();

    spyOn(component.removeOffer, 'emit');
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should unsubscribe user from existing offer', () => {
    component.unsubscribe(3);

    expect(component.removeOffer.emit).toHaveBeenCalled();
    expect(component.removeOffer.emit).toHaveBeenCalledWith(3);
  });
});
