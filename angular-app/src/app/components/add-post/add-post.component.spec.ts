import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPostComponent } from './add-post.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {CommentItemComponent} from '../comment-item/comment-item.component';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {HttpClient} from '@angular/common/http';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import { of } from 'rxjs';
describe('AddPostComponent', () => {
  let component: AddPostComponent;
  let fixture: ComponentFixture<AddPostComponent>;
  beforeEach(async () => {
  const culturalOfferServiceStub = () => ({
    addPost: jasmine.createSpy('addPost')
    .and.returnValue(of({

      body:{
        "id": 1,
        "content": "TestContent",
        "offer": {

          "id": 1,
          "name": "TestOffer",
          "images": null,
          "location": null,
          "description": "TestDescription",
          "avgGrade": 0.0,
          "subscribersCount": 0.0

        },
        "postTime": "12:50",
        "title": "TestTitle"
      }

    }))
  });
  
  let matDialogRefMock = {
    close: jasmine.createSpy('close')
  };

  let dialogDataMock = {
    id: 0,
    title: "New post",
    content: "Brand new post",
    culturalOfferId: 0
  };

  await TestBed.configureTestingModule({
    declarations: [AddPostComponent],
    providers: [
      { provide: CulturalOfferService, useFactory: culturalOfferServiceStub }, AuthService,  {provide: MatDialogRef, useValue: matDialogRefMock}, {provide: MAT_DIALOG_DATA, useValue: dialogDataMock}
    ],
    imports:[RouterTestingModule, HttpClientTestingModule, MatDialogModule]
  })
  .compileComponents();
});


beforeEach(() => {
  
  fixture = TestBed.createComponent(AddPostComponent);
  component = fixture.componentInstance;
  fixture.detectChanges();
});

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  describe('submit', () => {
    it('makes expected calls', () => {
      const culturalOfferServiceStub: CulturalOfferService = fixture.debugElement.injector.get(
        CulturalOfferService
      );
      //const routerStub: Router = fixture.debugElement.injector.get(Router);
      //spyOn(culturalOfferServiceStub, 'createOffer').and.callThrough();
      //spyOn(routerStub, 'navigate').and.callThrough();
      component.submitPost();
      expect(culturalOfferServiceStub.addPost).toHaveBeenCalled();
      //expect(routerStub.navigate).toHaveBeenCalled();
    });
  });


});
