import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { CategoryService } from '../../services/category.service';
import { CategoryModel } from '../../model/category-model';
import { MatDialog } from '@angular/material/dialog';
import { AddCategoryComponent } from './add-category.component';
import { of } from 'rxjs';
import { fakeAsync, tick } from '@angular/core/testing';

describe('AddCategoryComponent', () => {
  let component: AddCategoryComponent;
  let fixture: ComponentFixture<AddCategoryComponent>;
  let categoryService: CategoryService;

  beforeEach(() => {
    //@ts-ignore
    const formBuilderStub = () => ({ group: object => ({}) });

    const matDialogStub = () => ({
      //@ts-ignore
      open: (editCategoryDialogComponent, object) => ({
        //@ts-ignore
        afterClosed: () => ({ subscribe: f => f({}) })
      })
    });

    let categoryServiceMock = {
      getCategoriesByPage: jasmine.createSpy('getCategoriesByPage')
        .and.returnValue(of({
          body: {
            "content": [
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
              }
            ],
            "totalElements": 3,
            "last": true,
            "totalPages": 1,
            "size": 10,
            "number": 0,
            "numberOfElements": 3,
            "first": true,
            "empty": false,
            "pageNumber": 0,
            "pageSize": 10
          }
        }))
    };
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [AddCategoryComponent],
      providers: [
        { provide: FormBuilder, useFactory: formBuilderStub },
        { provide: CategoryService, useValue: categoryServiceMock },
        { provide: MatDialog, useFactory: matDialogStub }
      ]
    });
    fixture = TestBed.createComponent(AddCategoryComponent);
    component = fixture.componentInstance;
    categoryService = TestBed.inject(CategoryService);
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it(`pageNum has default value`, () => {
    expect(component.pageNum).toEqual(1);
  });

  it(`nextBtn has default value`, () => {
    expect(component.nextBtn).toEqual(false);
  });


  it('makes expected calls', fakeAsync(() => {
    component.getCategories();
    expect(categoryService.getCategoriesByPage).toHaveBeenCalledWith(1);
    tick(); tick();
    expect(component.categories.length).toBe(3);
  }));

});
