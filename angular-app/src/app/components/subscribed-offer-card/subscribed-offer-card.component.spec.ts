import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribedOfferCardComponent } from './subscribed-offer-card.component';

describe('SubscribedOfferCardComponent', () => {
  let component: SubscribedOfferCardComponent;
  let fixture: ComponentFixture<SubscribedOfferCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubscribedOfferCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscribedOfferCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
