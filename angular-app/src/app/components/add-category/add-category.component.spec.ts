import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CategoryService } from '../../services/category.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddCategoryComponent } from './add-category.component';
import { of } from 'rxjs';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';

describe('AddCategoryComponent', () => {
  let component: AddCategoryComponent;
  let fixture: ComponentFixture<AddCategoryComponent>;

  let categoryService: CategoryService;
  let matDialog: MatDialog;

  beforeEach(() => {

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
            "pageSize": 3
          }
        })),
        deleteCategory: jasmine.createSpy('deleteCategory')
        .and.returnValue(of({})),
        addCategory: jasmine.createSpy('addCategory')
        .and.returnValue(of({})),
        getByName: jasmine.createSpy('getByName')
        .and.returnValue(of(
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
              }
            ]
          }
        )),

    };

    let matDialogMock = {
      open: jasmine.createSpy('open')
    };

    TestBed.configureTestingModule({
      imports: [
        MatDialogModule,
        BrowserAnimationsModule,
        ReactiveFormsModule, FormsModule
      ],
      declarations: [AddCategoryComponent],
      providers: [
        { provide: CategoryService, useValue: categoryServiceMock },
        { provide: MatDialog, useValue: matDialogMock },
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(AddCategoryComponent);
    component = fixture.componentInstance;
    categoryService = TestBed.inject(CategoryService);
    matDialog = TestBed.inject(MatDialog);
    fixture.detectChanges();
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
    expect(categoryService.getCategoriesByPage).toHaveBeenCalled();
  });

  it('pageNum has default value', () => {
    expect(component.pageNum).toEqual(1);
  });

  it('should remove category', () => {
    component.deleteCategory(5);
    expect(categoryService.deleteCategory).toHaveBeenCalled();
    expect(categoryService.deleteCategory).toHaveBeenCalledWith(5);
  })

  it('should retrieve categories', () => {
    component.pageNum = 1;
    component.getCategories();
    expect(categoryService.getCategoriesByPage).toHaveBeenCalled();
    expect(categoryService.getCategoriesByPage).toHaveBeenCalledWith(1);
  });

  it('should retrieve categories for next page', () => {
    let oldPage = component.pageNum;
    component.nextPage();
    expect(component.pageNum).toBe(oldPage + 1);
    expect(categoryService.getCategoriesByPage).toHaveBeenCalled();
    expect(categoryService.getCategoriesByPage).toHaveBeenCalledWith(oldPage+1);
  });

  it('should retrieve categories for previous page', () => {
    component.pageNum = 10;
    let oldPage = component.pageNum;
    component.previousPage();
    expect(component.pageNum).toBe(oldPage - 1);
    expect(categoryService.getCategoriesByPage).toHaveBeenCalled();
    expect(categoryService.getCategoriesByPage).toHaveBeenCalledWith(oldPage-1);
  });

  it('should create category', () => {
    component.categoryForm.value.name = "adadadas";
    component.onSubmit();
    expect(categoryService.addCategory).toHaveBeenCalled();
  });

  it('should get category by name', () => {
    component.categoryFormByName.value.byname = "Institution";
    component.onSubmitByName();
    expect(categoryService.getByName).toHaveBeenCalled();
  });
});
