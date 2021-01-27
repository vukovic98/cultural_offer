import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { TypeService } from '../../services/type.service';
import { AllTypesModel } from '../../model/type-model';
import { EditTypeDialogComponent } from './edit-type-dialog.component';
import { of } from 'rxjs';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';

describe('EditTypeDialogComponent', () => {
  let component: EditTypeDialogComponent;
  let fixture: ComponentFixture<EditTypeDialogComponent>;
  let matDialogRef: MatDialogRef<EditTypeDialogComponent>;

  let typeService: TypeService;

  beforeEach(() => {

    const matDialogRefMock = {
      close: jasmine.createSpy('close')
    };

    const typeServiceMock = {
      updateType: jasmine.createSpy('updateType')
        .and.returnValue(of({})),
    };

    const modelMock = {
      'id': 10,
      'name': "asdf",
      'categoryId': 15,
      'categoryName': "Category"
    };

    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [EditTypeDialogComponent],
      providers: [
        { provide: TypeService, useValue: typeServiceMock },
        { provide: MAT_DIALOG_DATA, useValue: modelMock },
        { provide: MatDialogRef, useValue: matDialogRefMock }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(EditTypeDialogComponent);
    component = fixture.componentInstance;

    typeService = TestBed.inject(TypeService);
    matDialogRef = TestBed.inject(MatDialogRef);
    fixture.detectChanges();
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it('onNoClick should close dialog on button click', () => {
    matDialogRef.close();
    component.onNoClick();
    expect(matDialogRef.close).toHaveBeenCalled();
  });

  it('should save changes', () => {
    component.myForm.value.name = "asdasd";
    component.save();
    expect(typeService.updateType).toHaveBeenCalled();
    expect(matDialogRef.close).toHaveBeenCalled();
  });

  it('should close dialog', () => {
    component.close();
    expect(matDialogRef.close).toHaveBeenCalled();
  });
});
