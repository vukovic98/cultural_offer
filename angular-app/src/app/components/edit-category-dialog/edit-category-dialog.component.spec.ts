import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { CategoryModel } from '../../model/category-model';
import { EditCategoryDialogComponent } from './edit-category-dialog.component';
import { of } from 'rxjs';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';

describe('EditCategoryDialogComponent', () => {
  let component: EditCategoryDialogComponent;
  let fixture: ComponentFixture<EditCategoryDialogComponent>;
  let matDialogRef: MatDialogRef<EditCategoryDialogComponent>;

  let categoryService: CategoryService;
  
  beforeEach(() => {
    let matDialogRefMock = {
      close: jasmine.createSpy('close')
    };

    let cateogyrServiceMock = {
      updateCategory: jasmine.createSpy('updateCategory')
        .and.returnValue(of({})),
    }

    let modelMock = {
      'id': 5,
      'name': "asdasd",
      'types': []
    }

    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [EditCategoryDialogComponent],
      providers: [
        { provide: CategoryService, useValue: cateogyrServiceMock },
        { provide: MAT_DIALOG_DATA, useValue: modelMock },
        { provide: MatDialogRef, useValue: matDialogRefMock }
      ]
    });
    fixture = TestBed.createComponent(EditCategoryDialogComponent);
    component = fixture.componentInstance;

    categoryService = TestBed.inject(CategoryService);
    matDialogRef = TestBed.inject(MatDialogRef);
    fixture.detectChanges();
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it('should close dialog on click', () => {
    matDialogRef.close();
    component.onNoClick();
    expect(matDialogRef.close).toHaveBeenCalled();
  });

  it('should update category', () => {
    component.myForm.value.name = "adadasdsa";
    component.save();
    expect(categoryService.updateCategory).toHaveBeenCalled();
    expect(matDialogRef.close).toHaveBeenCalled();
  });

  it('should close dialog', () => {
    component.close();
    expect(matDialogRef.close).toHaveBeenCalled();
  });
});
