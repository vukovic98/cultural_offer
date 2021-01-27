import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocationDialogComponent } from './location-dialog.component';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {AddPostComponent} from '../add-post/add-post.component';
import {By} from '@angular/platform-browser';
import {MatSlideToggle} from '@angular/material/slide-toggle';

describe('LocationDialogComponent', () => {
  let component: LocationDialogComponent;
  let fixture: ComponentFixture<LocationDialogComponent>;
  let matDialogRef: MatDialogRef<AddPostComponent>;

  beforeEach( () => {

    const matDialogRefMock = {
      close: jasmine.createSpy('close')
    };

    const dialogDataMock = {
      "locationId": 0,
      "latitude": 33.33,
      "longitude": 33.33,
      "place": "Beograd"
    };

    TestBed.configureTestingModule({
      imports: [MatDialogModule],
      declarations: [ LocationDialogComponent ],
      providers: [{provide: MatDialogRef, useValue: matDialogRefMock}, {provide: MAT_DIALOG_DATA, useValue: dialogDataMock}]
    }).compileComponents();

    fixture = TestBed.createComponent(LocationDialogComponent);
    matDialogRef = TestBed.inject(MatDialogRef);
    component = fixture.componentInstance;

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize html', () => {
    const h1 = fixture.debugElement.query(By.css("#placeH")).nativeElement;

    expect(h1).toBeTruthy();
    expect(h1.innerHTML).toBe("Beograd");
  });

  it('should properly close dialog', () => {
    component.close();

    expect(component.dialogRef.close).toHaveBeenCalled();
  });

});
