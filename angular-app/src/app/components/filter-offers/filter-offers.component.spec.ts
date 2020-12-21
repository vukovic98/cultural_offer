import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterOffersComponent } from './filter-offers.component';

describe('FilterOffersComponent', () => {
  let component: FilterOffersComponent;
  let fixture: ComponentFixture<FilterOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilterOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FilterOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
