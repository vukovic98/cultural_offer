import {ComponentFixture, fakeAsync, TestBed, tick} from '@angular/core/testing';

import { CommentItemComponent } from './comment-item.component';
import {of} from 'rxjs';
import {Router} from '@angular/router';
import {CulturalOfferService} from '../../services/culturalOffer.service';
import {AuthService} from '../../services/auth.service';
import {HttpClient} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {RouterTestingModule} from '@angular/router/testing';

describe('CommentItemComponent', () => {
  let component: CommentItemComponent;
  let fixture: ComponentFixture<CommentItemComponent>;
  let offerService: any;
  let router: any;
  let httpClient: any;

  beforeEach(async () => {
    let offerServiceMock = {
      deleteComment: jasmine.createSpy('deleteComment')
        .and.returnValue(of({body: {statusCode: 200}}))
    }

    let routerMock = {
      navigate: jasmine.createSpy('navigate')
    };

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      declarations: [ CommentItemComponent ],
      providers:    [ {provide: CulturalOfferService, useValue: offerServiceMock },
        { provide: Router, useValue: routerMock } , AuthService, HttpClient]
    });

    fixture = TestBed.createComponent(CommentItemComponent);
    component    = fixture.componentInstance;
    offerService = TestBed.inject(CulturalOfferService);
    router = TestBed.inject(Router);
    httpClient = TestBed.inject(HttpClient);

    component.comment = {
      commenterEmail: "vladimirvukovic@maildrop.cc",
      commenterName: "Vladimir",
      content: "This is my comment",
      id: 55,
      image:  null,
      offer:  "26"
    };

    fixture.detectChanges();
  });


  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should delete comment from table for existing id', fakeAsync(() => {
    component.onClickDeleteComment();

    expect(offerService.deleteComment).toHaveBeenCalledWith(55);

  }));
});
