import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AddPostComponent } from './add-post.component';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';

describe('AddPostComponent', () => {
  let component: AddPostComponent;
  let fixture: ComponentFixture<AddPostComponent>;
  let matDialogRef: MatDialogRef<AddPostComponent>;

  beforeEach( () => {

    const matDialogRefMock = {
      close: jasmine.createSpy('close')
    };

    const dialogDataMock = {
      id: 0,
      title: "New post",
      content: "Brand new post",
      culturalOfferId: 0
    };

    TestBed.configureTestingModule({
      imports: [MatDialogModule],
      declarations: [ AddPostComponent ],
      providers: [{provide: MatDialogRef, useValue: matDialogRefMock}, {provide: MAT_DIALOG_DATA, useValue: dialogDataMock}]
    }).compileComponents();

    fixture = TestBed.createComponent(AddPostComponent);
    matDialogRef = TestBed.inject(MatDialogRef);
    component = fixture.componentInstance;



    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should properly close dialog', () => {
    component.addPostForm.controls['title'].setValue("New post");
    component.addPostForm.controls['content'].setValue("Brand new post");

    fixture.detectChanges();

    component.submitPost();

    expect(matDialogRef.close).toHaveBeenCalled();
    expect(matDialogRef.close).toHaveBeenCalledWith({
      data: {
        id: 0,
        title: "New post",
        content: "Brand new post",
        culturalOfferId: 0
      }
    });
  });
});
