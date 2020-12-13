import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CulturalOffersComponent } from './cultural-offers.component';

describe('CulturalOffersComponent', () => {
  let component: CulturalOffersComponent;
  let fixture: ComponentFixture<CulturalOffersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CulturalOffersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CulturalOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
