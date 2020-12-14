import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribedItemsComponent } from './subscribed-items.component';

describe('SubscribedItemsComponent', () => {
  let component: SubscribedItemsComponent;
  let fixture: ComponentFixture<SubscribedItemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubscribedItemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscribedItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
