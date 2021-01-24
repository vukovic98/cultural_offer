  
import { ComponentFixture, TestBed } from '@angular/core/testing';

//ng test --karma-config src/karma.conf.js

import { AddPostComponent } from './add-post.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {CommentItemComponent} from '../comment-item/comment-item.component';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {HttpClient} from '@angular/common/http';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {By} from '@angular/platform-browser';
import {of} from 'rxjs';
import Spy = jasmine.Spy;

describe('AddPostComponent', () => {
  let component: AddPostComponent;
  let fixture: ComponentFixture<AddPostComponent>;
  let matDialogRef: MatDialogRef<AddPostComponent>;

  beforeEach( () => {

    let matDialogRefMock = {
      close: jasmine.createSpy('close')
    };

    let dialogDataMock = {
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