import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPostComponent } from './add-post.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {CommentItemComponent} from '../comment-item/comment-item.component';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {HttpClient} from '@angular/common/http';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';

describe('AddPostComponent', () => {
  let component: AddPostComponent;
  let fixture: ComponentFixture<AddPostComponent>;

  beforeEach(async () => {
    TestBed.configureTestingModule({
      imports: [MatDialogModule],
      declarations: [ CommentItemComponent ],
      providers:    [MatDialogRef]
    });

    fixture = TestBed.createComponent(AddPostComponent);
    component    = fixture.componentInstance;
    component.data = {
      id: 45,
      title: "New post",
      content: "Brand new post",
      culturalOfferId: 26
    };

    //component.dialogRef = new MatDialogRef<AddPostComponent>(data);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
