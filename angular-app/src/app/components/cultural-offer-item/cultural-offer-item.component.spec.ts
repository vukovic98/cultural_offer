import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalOfferItemComponent } from './cultural-offer-item.component';

describe('CulturalOfferItemComponent', () => {
  let component: CulturalOfferItemComponent;
  let fixture: ComponentFixture<CulturalOfferItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOfferItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOfferItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
