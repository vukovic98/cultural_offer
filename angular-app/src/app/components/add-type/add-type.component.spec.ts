import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TypeService } from '../../services/type.service';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { CategoryService } from '../../services/category.service';
import { CategoryModel } from '../../model/category-model';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { Overlay } from '@angular/cdk/overlay';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { of } from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { AddTypeComponent } from './add-type.component';
import { AllTypesModel } from '../../model/type-model';

describe('AddTypeComponent', () => {
  let component: AddTypeComponent;
  let fixture: ComponentFixture<AddTypeComponent>;

  let categoryService: CategoryService;
  let typeService: TypeService;
  let matDialog: MatDialog;

  beforeEach(() => {

    const categoryServiceMock = {
      getCategories: jasmine.createSpy('getCategories')
        .and.returnValue(of(
          [
            {
              "id": 17,
              "name": "Institution",
              "types": [
                {
                  "id": 9,
                  "name": "Parliament",
                  "categoryId": 17,
                  "categoryName": "Institution"
                },
                {
                  "id": 10,
                  "name": "Museum",
                  "categoryId": 17,
                  "categoryName": "Institution"
                },
                {
                  "id": 47,
                  "name": "asdasdsa",
                  "categoryId": 17,
                  "categoryName": "Institution"
                },
                {
                  "id": 48,
                  "name": "asdasdasdssda",
                  "categoryId": 17,
                  "categoryName": "Institution"
                }
              ]
            },
            {
              "id": 18,
              "name": "Manifestacion",
              "types": []
            },
            {
              "id": 19,
              "name": "Landmark",
              "types": [
                {
                  "id": 11,
                  "name": "Tower",
                  "categoryId": 19,
                  "categoryName": "Landmark"
                },
                {
                  "id": 12,
                  "name": "Stadium",
                  "categoryId": 19,
                  "categoryName": "Landmark"
                }
              ]
            },
            {
              "id": 32,
              "name": "asdadadadsaedqwasa",
              "types": []
            },
            {
              "id": 33,
              "name": "asdfasdas",
              "types": []
            },
            {
              "id": 36,
              "name": "asdsasddsa",
              "types": []
            },
            {
              "id": 37,
              "name": "asdasadssad",
              "types": []
            }
          ]
        )),
    };

    const typeServiceMock = {
      getByPage: jasmine.createSpy('getByPage')
        .and.returnValue(of({
            "content": [
              {
                "id": 9,
                "name": "Parliament",
                "categoryId": 17,
                "categoryName": "Institution"
              },
              {
                "id": 10,
                "name": "Museum",
                "categoryId": 17,
                "categoryName": "Institution"
              },
              {
                "id": 11,
                "name": "Tower",
                "categoryId": 19,
                "categoryName": "Landmark"
              },
              {
                "id": 12,
                "name": "Stadium",
                "categoryId": 19,
                "categoryName": "Landmark"
              },
              {
                "id": 47,
                "name": "asdasdsa",
                "categoryId": 17,
                "categoryName": "Institution"
              }
            ],
            "totalElements": 5,
            "last": true,
            "totalPages": 1,
            "size": 10,
            "number": 0,
            "numberOfElements": 5,
            "first": true,
            "empty": false,
            "pageNumber": 0,
            "pageSize": 10
          }
        )),
      save: jasmine.createSpy('save')
        .and.returnValue(of(
          {
            "id": 48,
            "name": "asdasdasdssda",
            "categoryId": 17,
            "categoryName": "Institution"
          }
        )),
      getByName: jasmine.createSpy('getByName')
        .and.returnValue(of(
          {
            "id": 10,
            "name": "Museum",
            "categoryId": 17,
            "categoryName": "Institution"
          }
        )),
      deleteType: jasmine.createSpy('deleteType')
        .and.returnValue(of({})),
    };

    const matDialogMock = {
      open: jasmine.createSpy('open')
    };

    const dialogRefMock = {
      afterClosed: jasmine.createSpy('afterClosed')
    };

    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      imports: [
        MatDialogModule, BrowserAnimationsModule, ReactiveFormsModule, FormsModule
      ],
      declarations: [AddTypeComponent],
      providers: [
        { provide: CategoryService, useValue: categoryServiceMock },
        { provide: TypeService, useValue: typeServiceMock },
        { provide: MatDialog, useValue: matDialogMock },
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(AddTypeComponent);
    component = fixture.componentInstance;
    categoryService = TestBed.inject(CategoryService);
    typeService = TestBed.inject(TypeService);
    matDialog = TestBed.inject(MatDialog);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
    expect(categoryService.getCategories).toHaveBeenCalled();
    expect(typeService.getByPage).toHaveBeenCalled();
    fixture.detectChanges();
    fixture.whenStable()
      .then(() => {
        expect(component.types.length).toBe(5); // mock studentService returned 3 empty objects
        fixture.detectChanges(); // synchronize HTML with component data
      });
  });

  it('should remove type', () => {
    const obj: AllTypesModel = {
      'id': 5,
      'name': "Empty",
      'categoryId': -1,
      'categoryName': "Empty",
    };

    component.deleteType(obj);
    expect(typeService.deleteType).toHaveBeenCalled();
  });

  it('should retrieve types for next page', () => {
    const oldPage = component.pageNum;
    component.nextPage();
    expect(component.pageNum).toBe(oldPage + 1);
    expect(typeService.getByPage).toHaveBeenCalled();
  });

  it('should retrieve types for previous page', () => {
    component.pageNum = 10;
    const oldPage = component.pageNum;
    component.previousPage();
    expect(component.pageNum).toBe(oldPage - 1);
    expect(typeService.getByPage).toHaveBeenCalled();
  });

  it('should create type', () => {
    component.typeForm.value.name = "adadadas";
    component.typeForm.value.category = "adhashgdakjshdnla";
    component.onSubmit();
    expect(typeService.save).toHaveBeenCalled();
  });

  it('should get type by name', () => {
    component.typeFormByName.value.byname = "adadadas";
    component.onSubmitByName();
    expect(typeService.getByName).toHaveBeenCalled();
  });
});
