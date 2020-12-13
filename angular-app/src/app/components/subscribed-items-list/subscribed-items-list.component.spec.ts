import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscribedItemsListComponent } from './subscribed-items-list.component';

describe('SubscribedItemsListComponent', () => {
  let component: SubscribedItemsListComponent;
  let fixture: ComponentFixture<SubscribedItemsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubscribedItemsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscribedItemsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
