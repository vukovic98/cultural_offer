import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { CulturalOffer } from '../../model/offer-mode';
import { CulturalOfferService } from '../../services/culturalOffer.service';
import { FormsModule } from '@angular/forms';
import { EditOfferComponent } from './edit-offer.component';
import { of } from 'rxjs';
import {MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import { ReactiveFormsModule } from '@angular/forms';

describe('EditOfferComponent', () => {
  let component: EditOfferComponent;
  let fixture: ComponentFixture<EditOfferComponent>;
  let matDialogRef: MatDialogRef<EditOfferComponent>;

  let offerService: CulturalOfferService;

  beforeEach(() => {

    let matDialogRefMock = {
      close: jasmine.createSpy('close')
    };

    let modelMock = {
      "id": 10,
      "name": "adasdaas",
      "images": [],
      "location": {
        "locationId": 1,
        "latitude": 1,
        "longitude": 1,
        "place": "adadas"
      },
      "description": "adasdas",
      "avgGrade": 4,
      "subscribersCount": 10
    }

    let offerServiceMock = {
      getLocationName: jasmine.createSpy('getLocationName')
        .and.returnValue(of()),
    };
    
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      imports: [ ReactiveFormsModule, FormsModule ],
      declarations: [EditOfferComponent],
      providers: [
        { provide: MatDialogRef, useValue: matDialogRefMock },
        { provide: MAT_DIALOG_DATA, useValue: modelMock },
        { provide: CulturalOfferService, useValue: offerServiceMock },
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(EditOfferComponent);
    component = fixture.componentInstance;

    offerService = TestBed.inject(CulturalOfferService);
    matDialogRef = TestBed.inject(MatDialogRef);
    fixture.detectChanges();
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it('ngOnInit should init map', () => {
    component.ngOnInit();
    expect(component.initMap).toHaveBeenCalled();
  });

  it('should close dialog on save', () => {
    component.myForm.value.name = "newname";
    component.myForm.value.description = "newdescription";
    component.placeName = "newplacename";
    component.save();
    expect(matDialogRef.close).toHaveBeenCalled();
  });

  it('should close dialog on click', () => {
    component.close();
    expect(matDialogRef.close).toHaveBeenCalled();
  });

  it('makes expected calls', () => {
    component.initMap();
    expect(offerService.getLocationName).toHaveBeenCalled();
  });

});
